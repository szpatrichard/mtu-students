package mtu_student_app.models;

/**
 * Schema representing a module.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class Module {
    /* Attributes */
    private String code;
    private String title;

    /**
     * Module Constructor
     * 
     * @param code  Module's code
     * @param title Module's Title
     */
    public Module(String code, String title) {
        this.code = code;
        this.title = title;
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

    /**
     * Converts Module class into a string.
     */
    public String toString() {
        return String.format("%S - %s", getCode(), getTitle());
    }
}
