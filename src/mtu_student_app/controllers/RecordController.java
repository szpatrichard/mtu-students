package mtu_student_app.controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import mtu_student_app.App;
import mtu_student_app.models.Module;
import mtu_student_app.models.RecordList;
import mtu_student_app.models.Record;
import mtu_student_app.models.Student;
import mtu_student_app.views.module.form.AddGradeForm;
import mtu_student_app.views.module.form.RegisterForModuleForm;

/**
 * Class to control the data of the Record model.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class RecordController {
    /* Attributes */
    private final RecordList recordList;

    /**
     * RecordController Constructor.
     */
    public RecordController() {
        this.recordList = new RecordList();
    }

    /**
     * Gets the list of the records.
     * 
     * @return RecordList containing records
     */
    public RecordList getList() {
        return this.recordList;
    }

    /**
     * Registers a student for a module, adding it to a map which contains their
     * results.
     * 
     * @param name  Student's name
     * @param title Module's title
     * @return An error message
     */
    public String registerModule(String name, String title) {
        if (name.isBlank())
            return "No student selected";

        if (title.isBlank())
            return "No module selected";

        Student student = App.studentControl.getList().getStudentByName(name);
        Module module = App.moduleControl.getList().getModuleByTitle(title);

        for (Record record : getList().getRecords()) {
            if (record.getStudent().equals(student) && record.getModule().equals(module))
                return String.format("Already registered for module %s.", module.getTitle());
        }

        Record record = new Record(student, module);
        getList().addRecord(record);

        App.changesMade = true;
        return null;
    }

    /**
     * Adds the grade a student achieved in a module to a map containing their
     * results.
     * 
     * @param name  Student's name
     * @param title Module's title
     * @return An error message
     */
    public String addGrade(String name, String title, int grade) {
        if (name.isBlank())
            return "No student selected";

        if (title.isBlank())
            return "No module selected";

        Student student = App.studentControl.getList().getStudentByName(name);
        Module module = App.moduleControl.getList().getModuleByTitle(title);

        ArrayList<Record> studentRecords = getList().getRecords(student);

        /* Check that grade is valid */
        if (0 > grade || 100 < grade)
            return "Grade must have a value between 0 and 100.";

        /* Add student's grade to module */
        for (Record record : studentRecords)
            if (record.getModule().equals(module))
                record.setGrade(grade);

        App.changesMade = true;
        return null;
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
     * Saves the list containing the records to a text file.
     */
    public void saveList() {
        try {
            /* Write to file */
            PrintWriter output = new PrintWriter("data/results.txt");
            ArrayList<Record> list = getList().getRecords();
            /* Write each student's details to the file */
            for (Record record : list)
                output.printf("%s,%s,%s%n",
                        record.getStudent().getStudentId(),
                        record.getModule().getCode(),
                        record.getGrade());
            /* Close the file */
            output.close();
        } catch (FileNotFoundException e) {
        }
    }

    /**
     * Loads the text file containing the records.
     * 
     * @param recordList List containing the records
     * @return An error message
     */
    public String loadList(ArrayList<Record> recordList) {
        try {
            /* Clear lists */
            recordList.clear();
            // App.recordsList.clear();
            /* Read from file */
            FileReader fileReader = new FileReader("data/results.txt");
            BufferedReader inputFile = new BufferedReader(fileReader);
            String line = inputFile.readLine();
            while (line != null) {
                /* Get the data from each line in the file */
                String[] data = line.split(",");
                /* Create a new student object */
                Student student = App.studentControl.getList().getStudentById(data[0]);
                Module module = App.moduleControl.getList().getModuleByCode(data[1]);
                Record record = new Record(student, module, Integer.parseInt(data[2]));
                /* Add student to the lists */
                recordList.add(record);
                // App.recordsList.add(record);
                /* Read next line */
                line = inputFile.readLine();
            }
            /* Close the file */
            inputFile.close();
            return "Loaded records from file successfully.";
        } catch (FileNotFoundException e) {
            return "File with the records cannot be found. Missing \"results.txt\" in data folder.";
        } catch (IOException e) {
            e.printStackTrace();
            return "IO Exception error.";
        }
    }
}
