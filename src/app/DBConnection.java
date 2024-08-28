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
    public void createTable() {
        try (Statement stmt = con.createStatement()) {
            String query = "CREATE TABLE Books(StudentID VARCHAR(20),"
                    + " name VARCHAR(20), surname VARCHAR(20))";
            stmt.executeUpdate(query);
            System.out.println("Table created");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Table creation failed");
        }
    }
    
    
}
