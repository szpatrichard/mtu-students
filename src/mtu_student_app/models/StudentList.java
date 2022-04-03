package mtu_student_app.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class used to store the records of students.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class StudentList implements Serializable {
    /* Attributes */
    // ArrayList for storing the details of students
    private ArrayList<Student> students;

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
        return this.students;
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
     * @return A Student object
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
     * Adds a student to the students ArrayList.
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

    public String writeObject() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("data/students");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(students);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
            return null;
        } catch (IOException ie) {
            return "Can't write students to file.";
        }
    }

    public String readObject() {
        try {
            FileInputStream fileInputStream = new FileInputStream("data/students");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            students = (ArrayList<Student>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return null;
        } catch (FileNotFoundException e) {
            return "File cannot be found. Missing \"students\" in data folder.";
        } catch (ClassNotFoundException | IOException e) {
            return "Can't read students from file.";
        }
    }
}
