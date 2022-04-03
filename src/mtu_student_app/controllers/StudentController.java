package mtu_student_app.controllers;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mtu_student_app.App;
import mtu_student_app.models.Student;
import mtu_student_app.models.StudentList;
import mtu_student_app.views.student.form.NewStudentForm;

/**
 * Class to control the data of the Student model.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class StudentController {
    /* List containing details of students */
    private final StudentList studentList;

    /**
     * StudentController Constructor.
     */
    public StudentController() {
        this.studentList = new StudentList();
    }

    /**
     * Gets the list of students.
     * 
     * @return StudentList containing details of students
     */
    public StudentList getList() {
        return this.studentList;
    }

    /**
     * Adds a student to the list which contains the details of students.
     * 
     * @param studentName Student's name
     * @param studentId   Student's ID number
     * @param studentDob  Student's date of birth
     * @return An error message
     * @throws IOException
     */
    public String addToList(String studentName, String studentId, LocalDate studentDob) {
        String idFormat = "^(R[\\d]{8,})+$";
        Matcher idPatternMatch = Pattern.compile(idFormat).matcher((studentId.trim()));
        if (!studentName.isBlank() && !studentId.isBlank() && !studentDob.toString().isBlank()) {
            /* Check the ID format */
            if (!idPatternMatch.matches())
                return "Invalid Student ID.";
            /* Check if ID exists already */
            for (Student student : getList().getStudents()) {
                if (student.getStudentId().equals(studentId.trim()))
                    return "Duplicate Student ID.";
            }
            /* Add new student */
            Student student = new Student(studentName.trim(), studentId.trim().toUpperCase(), studentDob);
            getList().addStudent(student);
            App.studentsList.add(student.getName());
            App.changesMade = true;
            /* No error message */
            return null;
        }
        return "Fields must not be empty.";
    }

    /**
     * Remove a particular student from the list based on their name.
     */
    public void removeFromList(String studentName) {
        /* Check if student name is valid */
        if (studentName != null) {
            /* Remove student */
            getList().removeStudentByName(studentName);
            App.studentsList.remove(studentName);
            App.changesMade = true;
        }
    }

    /**
     * Show the form for adding a new student.
     */
    public void showNewStudentForm() {
        NewStudentForm.display();
    }

    /**
     * Saves the list containing the details of student to a text file.
     * 
     * @return An error message
     */
    public String saveList() {
        String err = getList().writeObject();
        if (err == null) {
            return "Students have been saved to file successfully.";
        } else
            return "An error occurred while saving students.";
    }

    /**
     * Loads the text file containing the details of students.
     * 
     * @param studentList List containing the details of students
     * @return An error message
     */
    public String loadList(ArrayList<Student> studentList) {
        String err = getList().readObject();
        if (err == null) {
            App.studentsList.clear();
            for (Student student : getList().getStudents()) {
                App.studentsList.add(student.getName());
            }
            return "Loaded students from file successfully.";
        } else
            return err;
    }
}
