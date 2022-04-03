package mtu_student_app.models;

/**
 * Schema representing a record.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class Record {
    /* Attributes */
    private Student student;
    private Module module;
    private int grade;

    /**
     * Record Constructor.
     * 
     * @param student Student object
     * @param module  Module object
     */
    public Record(Student student, Module module) {
        this.student = student;
        this.module = module;
        this.grade = -1;
    }

    /**
     * Record Constructor.
     * 
     * @param student Student object
     * @param module  Module object
     * @param grade   Grade of student in the module
     */
    public Record(Student student, Module module, int grade) {
        this.student = student;
        this.module = module;
        this.grade = grade;
    }

    /**
     * Gets the student in the record.
     * 
     * @return Student object
     */
    public Student getStudent() {
        return this.student;
    }

    /**
     * Sets the student in the record.
     * 
     * @param student Student object
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Gets the module in the record.
     * 
     * @return Module object
     */
    public Module getModule() {
        return this.module;
    }

    /**
     * Sets the module in the record.
     * 
     * @param module Module object
     */
    public void setModule(Module module) {
        this.module = module;
    }

    /**
     * Gets the grade in the record.
     * 
     * @return Grade of student in the module
     */
    public int getGrade() {
        return this.grade;
    }

    /**
     * Sets the grade in the record.
     * 
     * @param grade Grade of student in the module.
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }

    /**
     * Converts Record class into a string.
     */
    @Override
    public String toString() {
        return "{" +
                " student='" + getStudent() + "'" +
                ", module='" + getModule() + "'" +
                ", grade='" + getGrade() + "'" +
                "}";
    }
}
