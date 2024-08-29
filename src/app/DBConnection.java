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
    
    private static final String JDBC_URL = "jdbc:derby:libraryDB; create =true";
    
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
            String query = "CREATE TABLE Books(BookID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,"
                    + " Title VARCHAR(20), Author VARCHAR(20), YearPublished VARCHAR(20), Genre VARCHAR(20), Price VARCHAR(20))";
            stmt.executeUpdate(query);
            System.out.println("Table created");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Table creation failed");
        }
    } 
        
public void dropBorrowersTable() {
    String query = "DROP TABLE Borrowers";

    try (Statement stmt = con.createStatement()) {
        stmt.executeUpdate(query);
        System.out.println("Borrowers table deleted successfully");
    } catch (SQLException ex) {
        ex.printStackTrace();
        System.out.println("Failed to delete Borrowers table");
    }
}

    public void createTableBorrowers() {
    try (Statement stmt = con.createStatement()) {
        String query = "CREATE TABLE Borrowers ("
                + "StudentID INT, "
                + "BookID INT, "
                + "StudentName VARCHAR(20), "
                + "StudentSurname VARCHAR(20), "
                + "StudentCourse VARCHAR(20), "
                + "RentalPrice VARCHAR(20), "
                + "FOREIGN KEY (BookID) REFERENCES Books(BookID), "
                + "PRIMARY KEY (StudentID, BookID))"; // Composite primary key to ensure unique student-book pairs
        stmt.executeUpdate(query);
        System.out.println("Table created");
    } catch (SQLException ex) {
        ex.printStackTrace();
        System.out.println("Table creation failed");
    }
}



 /*  public void createTableBorrowers(){
    try (Statement stmt = con.createStatement()) {
        String query = "CREATE TABLE Borrowers ("
                + "StudentID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, "
                + "BookID INT, "
                + "StudentName VARCHAR(20), "
                + "StudentSurname VARCHAR(20), "
                + "StudentCourse VARCHAR(20), "
                + "RentalPrice VARCHAR(20), "
                + "FOREIGN KEY (BookID) REFERENCES Books(BookID))";
        stmt.executeUpdate(query);
        System.out.println("Table created");
    } catch (SQLException ex) {
        ex.printStackTrace();
        System.out.println("Table creation failed");
    }
} */

      
    
    
}
