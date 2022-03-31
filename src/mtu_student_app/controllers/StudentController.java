package mtu_student_app.controllers;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mtu_student_app.App;
import mtu_student_app.models.Module;
import mtu_student_app.models.Student;
import mtu_student_app.models.StudentList;
import mtu_student_app.views.module.form.AddGradeForm;
import mtu_student_app.views.module.form.RegisterForModuleForm;
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
        studentList = new StudentList();
    }

    /**
     * Gets the entire record of students in a list.
     * 
     * @return StudentList containing details of students
     */
    public StudentList getList() {
        return studentList;
    }

    /**
     * Adds a student to the list which contains the details of students.
     * 
     * @param studentName Student's name
     * @param studentId   Student's ID number
     * @param studentDob  Student's date of birth
     * @return An error message
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
     * Registers a student for a module, adding it to a map which contains their
     * results.
     * 
     * @param studentName Student's name
     * @param moduleTitle Module's title
     * @return An error message
     */
    public String registerModule(String studentName, String moduleTitle) {
        if (!studentName.isBlank() && !moduleTitle.isBlank()) {
            Student student = getList().getStudentByName(studentName);
            Module module = App.moduleControl.getList().getModuleByTitle(moduleTitle);
            student.addResults(module, -1);
            App.changesMade = true;
            /* No error message */
            return null;
        }
        return "Fields must not be empty.";
    }

    /**
     * Adds the grade a student achieved in a module to a map containing their
     * results.
     * 
     * @param studentName Student's name
     * @param moduleTitle Module title
     * @param gradeStr    Student's achieved grade in a module
     * @return An error message
     */
    public String addResult(String studentName, String moduleTitle, String gradeStr) {
        /* Check that fields are valid */
        if (!studentName.isBlank() && !moduleTitle.isBlank() && !gradeStr.isBlank()) {
            Student student = getList().getStudentByName(studentName);
            Module module = App.moduleControl.getList().getModuleByTitle(moduleTitle);
            try {
                /* Convert string to integers */
                int grade = Integer.parseInt(gradeStr);
                /* Check that grade is valid */
                if (0 > grade || 100 < grade)
                    return "Grade must have a value between 0 and 100.";
                /* Add student's grade to module */
                student.addResults(module, grade);
            } catch (NumberFormatException nfe) {
                return "Enter a valid grade.";
            }
            App.changesMade = true;
            /* No error message */
            return null;
        }
        return "Fields must not be empty.";
    }

    /**
     * Show the form for adding a new student.
     */
    public void showNewStudentForm() {
        NewStudentForm.display();
    }

    /**
     * Show the form for registering a student's module.
     */
    public void showRegisterForModuleForm() {
        RegisterForModuleForm.display();
    }

    /**
     * Show the form for adding a grade to a student's module.
     */
    public void showAddGradeForm() {
        AddGradeForm.display();
    }

    /**
     * Saves the list containing the details of student to a text file.
     */
    public void saveList() {
        try {
            /* Write to file */
            PrintWriter output = new PrintWriter("data/students.txt");
            ArrayList<Student> list = getList().getStudents();
            /* Write each student's details to the file */
            for (Student student : list)
                output.printf("%s,%s,%s%n", student.getName(), student.getStudentId(), student.getDob());
            /* Close the file */
            output.close();
            System.out.println("Students have been saved to file successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found.");
        }
    }

    /**
     * Saves the list containing the results of student to a text file.
     */
    public void saveResults() {
        try {
            /* Write to file */
            PrintWriter output = new PrintWriter("data/results.txt");
            ArrayList<Student> studentList = getList().getStudents();
            /* Write each student's details to the file */
            for (Student student : studentList) {
                for (HashMap.Entry<Module, Integer> result : student.getResults().entrySet())
                    output.printf(
                            "%s,%s,%d%n",
                            student.getStudentId(),
                            result.getKey().getCode(),
                            result.getValue());
            }
            /* Close the file */
            output.close();
            System.out.println("Results have been saved to file successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found.");
        }
    }

    /**
     * Loads the text file containing the details of students.
     * 
     * @param studentList List containing the details of students
     * @return An error message
     */
    public String loadList(ArrayList<Student> studentList) {
        try {
            /* Clear lists */
            studentList.clear();
            App.studentsList.clear();
            /* Read from file */
            FileReader fileReader = new FileReader("data/students.txt");
            BufferedReader inputFile = new BufferedReader(fileReader);
            String line = inputFile.readLine();
            while (line != null) {
                /* Get the data from each line in the file */
                String[] data = line.split(",");
                /* Create a new student object */
                Student student = new Student(data[0], data[1], LocalDate.parse(data[2]));
                /* Add student to the lists */
                studentList.add(student);
                App.studentsList.add(student.getName());
                /* Read next line */
                line = inputFile.readLine();
            }
            /* Close the file */
            inputFile.close();
            return "Loaded students from file successfully.";
        } catch (FileNotFoundException e) {
            return "File with the students cannot be found. Missing \"students.txt\" in data folder.";
        } catch (IOException e) {
            e.printStackTrace();
            return "IO Exception error.";
        }
    }

    /**
     * Loads the text file containing the results of students.
     * 
     * @param studentList List containing the details of students
     * @return An error message
     */
    public String loadResults(ArrayList<Student> studentList) {
        try {
            /* Read from file */
            FileReader fileReader = new FileReader("data/results.txt");
            BufferedReader inputFile = new BufferedReader(fileReader);
            String line = inputFile.readLine();
            while (line != null) {
                /* Get the data from each line in the file */
                String[] data = line.split(",");
                /* Create a new student object */
                Student student = getList().getStudentById(data[0]);
                /* Create a new module object */
                Module module = App.moduleControl.getList().getModuleByCode(data[1]);
                /* Add module to student */
                /* Add grade to module taken by student */
                int grade = Integer.parseInt(data[2]);
                if (grade >= 0 && grade <= 100)
                    student.addResults(module, grade);
                /* Read next line */
                line = inputFile.readLine();
            }
            /* Close the file */
            inputFile.close();
            return "Loaded results from file successfully.";
        } catch (FileNotFoundException e) {
            return "File with the students cannot be found. Missing \"results.txt\" in data folder.";
        } catch (IOException e) {
            e.printStackTrace();
            return "IO Exception error.";
        }
    }
}
