/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;


/**
 *
 * @author User
 */
public class Books extends Database{
    
    public Books(){
        super();
    }
        public boolean bookExists(int bookID) {
        String query = "SELECT COUNT(*) FROM Books WHERE BookID = ?"; //SQL statemnt to select all data from books table
        
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, bookID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; //this will return 1 / true to indicate book exists
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
        
    public void deleteBook(int bookID) {
        String query = "DELETE FROM Books WHERE BookID = ?";
        
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, bookID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book deleted successfully");
            } else {
                System.out.println("Book not found");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void addBook(String title, String author, String yearPublished, String genre, String price) {
    String query = "INSERT INTO Books (Title, Author, YearPublished, Genre, Price) VALUES ('"
            + title + "', '" + author + "', '" + yearPublished + "', '"
            + genre + "', '" + price + "')";
    executeUpdate(query);
    System.out.println("Book added to database");
}
    
    public DefaultTableModel getBooksTableModel() { //return a tableModel that will be used when viewing datata
        DefaultTableModel model = new DefaultTableModel();
        String[] columns = {"Book ID", "Title", "Author", "Year Published", "Genre", "Price"};
        model.setColumnIdentifiers(columns);

        try {
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM Books";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Object[] row = new Object[columns.length];
                row[0] = rs.getInt("BookID");
                row[1] = rs.getString("Title");
                row[2] = rs.getString("Author");
                row[3] = rs.getString("YearPublished");
                row[4] = rs.getString("Genre");
                row[5] = rs.getString("Price");
                model.addRow(row);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return model;
    }
    
        public void updateBook(int bookID, String title, String author, String yearPublished, String genre, String price) {
        String query = "UPDATE Books SET Title = ?, Author = ?, YearPublished = ?, Genre = ?, Price = ? WHERE BookID = ?";
        //Query to update books
        
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, yearPublished);
            pstmt.setString(4, genre);
            pstmt.setString(5, price);
            pstmt.setInt(6, bookID);
            pstmt.executeUpdate();
            System.out.println("Book updated successfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    


    
}
