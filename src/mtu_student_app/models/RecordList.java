package mtu_student_app.models;

import java.util.ArrayList;
import java.util.Collections;

import mtu_student_app.models.Comparators.GradeComparator;
import mtu_student_app.models.Comparators.ModuleTitleComparator;

/**
 * Class used to store the records.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class RecordList {
    /* Attributes */
    private ArrayList<Record> records;

    /**
     * RecordList Constructor.
     */
    public RecordList() {
        this.records = new ArrayList<>();
    }

    /**
     * Gets the records ArrayList.
     * 
     * @return An ArrayList with the records.
     */
    public ArrayList<Record> getRecords() {
        return this.records;
    }

    /**
     * Gets the records of a particular student.
     * 
     * @param student Student object
     * @return An ArrayList with the student's records.
     */
    public ArrayList<Record> getRecords(Student student) {
        ArrayList<Record> recordsOfStudent = new ArrayList<>();
        for (Record record : getRecords()) {
            if (record.getStudent().equals(student)) {
                recordsOfStudent.add(record);
            }
        }
        return recordsOfStudent;
    }

    /**
     * Adds a record to the records ArrayList.
     * 
     * @param record Record object
     */
    public void addRecord(Record record) {
        getRecords().add(record);
    }

    /**
     * Removes the record of a student in a module.
     * 
     * @param student A Student object
     * @param module  A Module object
     * @return The removed the record
     */
    public Record removeRecord(Student student, Module module) {
        Record toRemove = null;
        for (Record record : getRecords()) {
            if (record.getStudent().equals(student) && record.getModule().equals(module)) {
                this.records.remove(record);
                toRemove = record;
            }
        }
        return toRemove;
    }

    /**
     * Sorts the record by grade.
     */
    public void sortByGrade() {
        Collections.sort(records, new GradeComparator().reversed());
    }

    /**
     * Sorts the record by module title.
     */
    public void sortByModuleTitle() {
        Collections.sort(records, new ModuleTitleComparator());
    }

    /**
     * Gets the number of records stored.
     * 
     * @return Length of the records ArrayList
     */
    public int length() {
        return getRecords().size();
    }
}
