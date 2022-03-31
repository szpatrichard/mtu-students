package mtu_student_app.models;

import java.util.ArrayList;

/**
 * Class used to store the records of students, serving as the database of the
 * application.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class StudentList {
    /* Attributes */
    // ArrayList for storing the details of students
    private final ArrayList<Student> students;

    /**
     * StudentList Constructor.
     */
    public StudentList() {
        students = new ArrayList<>();
    }

    /**
     * Gets the students ArrayList.
     * 
     * @return An ArrayList with the details of the students.
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * Gets a particular student.
     * 
     * @param student A Student object
     * @return A Student object
     */
    public Student getStudent(Student student) {
        return getStudents().get(students.indexOf(student));
    }

    /**
     * Gets a particular students based on their student ID.
     * 
     * @param id Student's ID
     * @return A Student
     *         bject
     */
    public Student getStudentById(String id) {
        for (Student student : getStudents()) {
            if (student.getStudentId().equals(id))
                return student;
        }
        return null;
    }

    /**
     * Gets a particular student based on their name.
     * 
     * @param name Student's name
     * @return A Student object
     */
    public Student getStudentByName(String name) {
        for (Student student : getStudents())
            if (student.getName().equals(name))
                return student;
        return null;
    }

    /**
     * Adds a student to the students ArrayList
     * 
     * @param student A Student object
     */
    public void addStudent(Student student) {
        getStudents().add(student);
    }

    /**
     * Removes a particular student from the students ArrayList based on their name.
     * 
     * @param name Student's name
     */
    public void removeStudentByName(String name) {
        getStudents().removeIf(student -> student.getName().equals(name));
    }

    /**
     * Gets the number of students stored.
     * 
     * @return Length of the students ArrayList
     */
    public int length() {
        return getStudents().size();
    }
}
