/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

/**
 *
 * @author felix
 */
import java.sql.*;


public class DBConnection {
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    
    private static final String JDBC_URL = "jdbc:derby:libraryDB; creacan te =true";
    
    Connection con;
    public DBConnection(){}
    
    public void connect() throws ClassNotFoundException {
        try {
            Class.forName(DRIVER);
            this.con = DriverManager.getConnection(JDBC_URL);
            if (this.con != null) {
                System.out.println("Connected to database");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void createTableBooks() {
        try (Statement stmt = con.createStatement()) {
            String query = "CREATE TABLE Books(BookID VARCHAR(20) IDENTITY(1,1) PRIMARY KEY,"
                    + " Title VARCHAR(20), Author VARCHAR(20), YearPublished VARCHAR(20), Genre VARCHAR(20), Price VARCHAR(20))";
            stmt.executeUpdate(query);
            System.out.println("Table created");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Table creation failed");
        }
    }
    
    public void createTableBorrowers(){
        try(Statement stmt = con.createStatement()){
            String query = "CREATE TABLE Borrowers (BookID VARCHAR(20)," + 
                    "StudentID VARCHAR(20) IDENTITY(1,1) PRIMARY KEY, StudentName VARCHAR(20), StudentSurname VARCHAR(20), StudentCourse VARCHAR(20), RentalPrice VARCHAR(20))";
            stmt.executeUpdate(query);
            System.out.println("Table created");
        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("Table creation failed");
        }
    }
    
     public void addBorrower(Borrower borrower) {
        try {
            String query = "INSERT INTO Borrowers (BookID, StudentID, StudentName, StudentSurname, StudentCourse, RentalPrice) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, borrower.getBookID());
            pstmt.setString(2, borrower.getId());
            pstmt.setString(3, borrower.getName());
            pstmt.setString(4, borrower.getSurname());
            pstmt.setString(5, borrower.getStudentCourse());
            pstmt.setString(6, borrower.getRentalPrice());
            pstmt.executeUpdate();
            System.out.println("Data Added");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Data not added");
        }
    }
     
      public void addBook(Book book) {
        try {
            String query = "INSERT INTO Books (BookID, Title, Author, YearPublished, Genre, Price) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, book.getBookID());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getAuthor());
            pstmt.setString(4, book.getYearPublished());
            pstmt.setString(5, book.getGenre());
            pstmt.setString(6, book.getPrice());
            pstmt.executeUpdate();
            System.out.println("Book Added");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Book not added");
        }
    }
    
    
}
