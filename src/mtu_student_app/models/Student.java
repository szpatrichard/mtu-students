package mtu_student_app.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Schema for student.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class Student {
    /* Attributes */
    private String name,
            studentId;
    private LocalDate dob;
    /* HashMap with module as key and the grade as value */
    private final HashMap<Module, Integer> results;

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
        this.results = new HashMap<>();
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

    /**
     * Gets the results of the modules the student is enrolled in.
     */
    public HashMap<Module, Integer> getResults() {
        return this.results;
    }

    /**
     * Adds the result of a module the student is enrolled in.
     * 
     * @param module A Module object
     * @param grade  The grade the student achieved in the module
     */
    public void addResults(Module module, int grade) {
        getResults().put(module, grade);
    }

    /**
     * TODO: Sorts the results by the module's title.
     */
    public void sortModules() {
        List<Module> gradesByKey = new ArrayList<>(results.keySet());
        // Collections.sort(gradesByKey);
    }

    /**
     * TODO: Sorts the results by the achieved grade.
     */
    public void sortGrades() {
        List<Integer> gradesByValue = new ArrayList<>(results.values());
        Collections.sort(gradesByValue);
    }

    /**
     * Converts Student class into string.
     * 
     * @return Details of a student as a formatted string
     */
    public String toString() {
        return String.format("Student %s with ID %S", this.name, this.studentId);
    }
}
