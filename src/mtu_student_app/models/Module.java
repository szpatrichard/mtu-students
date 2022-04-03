package mtu_student_app.models;

import java.io.Serializable;

import college.Department;

/**
 * Schema representing a module.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class Module implements Serializable {
    /* Attributes */
    private String code;
    private String title;
    private Department department;

    /**
     * Module Constructor.
     * 
     * @param code  Module's code
     * @param title Module's Title
     */
    public Module(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public Module(String code, String title, Department department) {
        this.code = code;
        this.title = title;
        this.department = department;
    }

    /**
     * Gets the code of the module.
     * 
     * @return Module's code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code of the module
     * 
     * @param code Module's code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the title of the module
     * 
     * @return Module's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the module
     * 
     * @param title Module's title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Converts Module class into a string.
     */
    @Override
    public String toString() {
        return "{" +
                " code='" + getCode() + "'" +
                ", title='" + getTitle() + "'" +
                "}";
    }
}
