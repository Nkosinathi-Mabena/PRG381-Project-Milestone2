/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


/**
 *
 * @author User
 */
public class Database {
        protected Connection con;

    public Database() {
        try {
            connect();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
        protected void connect() throws ClassNotFoundException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection("jdbc:derby:libraryDB;create=true");
            System.out.println("Connected to database");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
       
        protected void executeUpdate(String query) {
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
        
    
    
    
}
