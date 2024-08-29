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
import javax.swing.JOptionPane;
import javax.swing.JTable;


/**
 *
 * @author User
 */
public class Borrowers extends Database{
    
    public Borrowers(){
        super();
    }
    public boolean studentExists(int studentID){
        String query = "SELECT COUNT(*) FROM Borrowers WHERE StudentID = ?";
        
        try(PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setInt(1, studentID);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1)>0;
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
    
 
    
    
    
        public DefaultTableModel getBorrowersTableModel() {
        String[] columnNames = {"Student ID", "Book ID", "Student Name", "Student Surname", "Student Course", "Late Fee"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        String query = "SELECT * FROM Borrowers";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int studentID = rs.getInt("StudentID");
                int bookID = rs.getInt("BookID");
                String studentName = rs.getString("StudentName");
                String studentSurname = rs.getString("StudentSurname");
                String studentCourse = rs.getString("StudentCourse");
                String rentalPrice = rs.getString("RentalPrice");

                Object[] row = {studentID, bookID, studentName, studentSurname, studentCourse, rentalPrice};
                model.addRow(row);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return model;
    }
    
public void addBorrower(int studentID, int bookID, String studentName, String studentSurname, String studentCourse, String rentalPrice) {
    Books books = new Books();
    
    // Check if the BookID exists in the Books table
    if (books.bookExists(bookID)) {
        String query = "INSERT INTO Borrowers (StudentID, BookID, StudentName, StudentSurname, StudentCourse, RentalPrice) "
                     + "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, studentID);
            pstmt.setInt(2, bookID);
            pstmt.setString(3, studentName);
            pstmt.setString(4, studentSurname);
            pstmt.setString(5, studentCourse);
            pstmt.setString(6, rentalPrice);
            
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Borrower added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to add borrower", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Book ID does not exist", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    public void viewBorrower(int studentID, JTable jTable2) {
    String query = "SELECT b.StudentName, b.StudentSurname, b.StudentCourse, bo.Title, bo.Genre "
                 + "FROM Borrowers b "
                 + "INNER JOIN Books bo ON b.BookID = bo.BookID "
                 + "WHERE b.StudentID = ?";

    try (PreparedStatement pstmt = con.prepareStatement(query)) {
        pstmt.setInt(1, studentID); // Set the studentID parameter

        ResultSet rs = pstmt.executeQuery();

        // Get the table model
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();

        // Clear existing rows in the table
        model.setRowCount(0);

        // Add rows to the table model
        while (rs.next()) {
            String studentName = rs.getString("StudentName");
            String studentSurname = rs.getString("StudentSurname");
            String studentCourse = rs.getString("StudentCourse");
            String bookTitle = rs.getString("Title");
            String bookGenre = rs.getString("Genre");

            // Add row to table
            model.addRow(new Object[]{studentName, studentSurname, studentCourse, bookTitle, bookGenre});
        }

        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No data found for the given Student ID", "No Data", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

    
    public void deleteBorrower(int studentID) {
        String queryDelete = "DELETE FROM Borrowers WHERE StudentID = ?";

        try (PreparedStatement pstmtDelete = con.prepareStatement(queryDelete)) {
            pstmtDelete.setInt(1, studentID);
            int rowsAffected = pstmtDelete.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Borrower record deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No such borrower exists with the given Student ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
   
public void updateBorrower(int studentID, int bookID, String studentName, String studentSurname, String studentCourse, String rentalPrice) {
    Books books = new Books();

    if (books.bookExists(bookID)) {
        String query = "UPDATE Borrowers SET StudentName = ?, StudentSurname = ?, StudentCourse = ?, RentalPrice = ? WHERE StudentID = ? AND BookID = ?";
        //Here we had to use StudentID or BookID to search for student in borrowers table because a student cant rent two books but what we only want to update one row

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, studentName);
            pstmt.setString(2, studentSurname);
            pstmt.setString(3, studentCourse);
            pstmt.setString(4, rentalPrice);
            pstmt.setInt(5, studentID);
            pstmt.setInt(6, bookID);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Borrower record updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No such borrower exists with the given Student ID and Book ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(null, "Book ID does not exist. Cannot update borrower.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    
    
    
}











    
    

    
  
    
     
    

