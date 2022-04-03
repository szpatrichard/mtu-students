package mtu_student_app.controllers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mtu_student_app.App;
import mtu_student_app.models.Module;
import mtu_student_app.models.ModuleList;
import mtu_student_app.views.module.form.CreateModuleForm;

/**
 * Class to control the data of the Module model.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class ModuleController {
    /* Attributes */
    private final ModuleList moduleList;

    /**
     * ModuleController Constructor.
     */
    public ModuleController() {
        moduleList = new ModuleList();
    }

    /**
     * Gets the list of modules.
     * 
     * @return ModuleList containing details of students
     */
    public ModuleList getList() {
        return moduleList;
    }

    /**
     * Adds a module to the list which contains the details of modules.
     * 
     * @param code  Module's code
     * @param title Module's title
     * @return An error message
     */
    public String addToList(String code, String title) {
        String codeFormat = "^([\\w]{4}[\\d]{4})+$";
        Matcher codePatternMatch = Pattern.compile(codeFormat).matcher((code.trim()));
        if ((!code.isBlank() && !title.isBlank())) {
            /* Check the code format */
            if (!codePatternMatch.matches())
                return "Invalid module code.";
            /* Check if code exists */
            for (Module module : getList().getModules()) {
                if (module.getCode().equals(code.trim())) {
                    return "Duplicate module code.";
                }
            }
            /* Add new module */
            Module module = new Module(code.trim(), title.trim());
            getList().addModule(module);
            App.modulesList.add(module.getTitle());
            App.changesMade = true;
            /* No error message */
            return null;
        }
        return "Fields must not be empty.";
    }

    /**
     * Show the form for creating a module.
     */
    public void showCreateModuleForm() {
        CreateModuleForm.display();
    }

    /**
     * Saves the list containing the details of student to a text file.
     * 
     * @return An error message
     */
    public String saveList() {
        String err = getList().writeObject();
        if (err == null) {
            return "Modules have been saved to file successfully.";
        } else
            return "An error occurred while saving modules.";
    }

    /**
     * Loads the text file containing the details of modules.
     * 
     * @param moduleList List containing the details of modules
     * @return An error message
     */
    public String loadList(ArrayList<Module> moduleList) {
        String err = getList().readObject();
        if (err == null) {
            App.modulesList.clear();
            for (Module module : getList().getModules()) {
                App.modulesList.add(module.getTitle());
            }
            return "Loaded modules from file successfully.";
        } else
            return err;
    }
}
