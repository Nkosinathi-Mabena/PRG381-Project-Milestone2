/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

/**
 *
 * @author User
 */
public class Borrower extends LibraryStakeHolder{
  private String bookID;
    private String studentCourse;
    private String rentalPrice;

    public Borrower(String bookID, String studentID, String name, String surname, String studentCourse, String rentalPrice) {
        super(studentID, name, surname);
        this.bookID = bookID;
        this.studentCourse = studentCourse;
        this.rentalPrice = rentalPrice;
    }

    // Getters and Setters
    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getStudentCourse() {
        return studentCourse;
    }

    public void setStudentCourse(String studentCourse) {
        this.studentCourse = studentCourse;
    }

    public String getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(String rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
    
}
