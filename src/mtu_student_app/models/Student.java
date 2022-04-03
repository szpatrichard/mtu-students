package mtu_student_app.models;

import java.io.Serializable;
import java.time.LocalDate;

import college.College;

/**
 * Schema for student.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class Student implements Serializable {
    /* Attributes */
    private String name,
            studentId;
    private LocalDate dob;
    private College college;

    /**
     * Student Constructor.
     * 
     * @param name      Student's name
     * @param studentId Student's ID number
     * @param dob       Student's date of birth
     */
    public Student(String name, String studentId, LocalDate dob) {
        this.name = name;
        this.studentId = studentId;
        this.dob = dob;
    }

    public Student(String name, String studentId, LocalDate dob, College college) {
        this.name = name;
        this.studentId = studentId;
        this.dob = dob;
        this.college = college;
    }

    /**
     * Gets the name of the student.
     * 
     * @return Student's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the student.
     * 
     * @param name Student's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the student ID of the student.
     * 
     * @return Student's ID number
     */
    public String getStudentId() {
        return this.studentId;
    }

    /**
     * Sets the student ID of the student.
     * 
     * @param studentId Student's ID number
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Gets the date of birth of the student.
     * 
     * @return Student's date of birth
     */
    public LocalDate getDob() {
        return this.dob;
    }

    /**
     * Sets the date of birth of the student.
     * 
     * @param dob Student's date of birth
     */
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public College getCollege() {
        return this.college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    /**
     * Converts Student class into string.
     * 
     * @return Details of a student as a formatted string
     */
    @Override
    public String toString() {
        return "{" +
                " name='" + getName() + "'" +
                ", studentId='" + getStudentId() + "'" +
                ", dob='" + getDob() + "'" +
                "}";
    }
}
