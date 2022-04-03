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
 * Class used to store the records of modules.
 * 
 * @author Patrik Richard Szilagyi R00198735S
 */
public class ModuleList implements Serializable {
    /* Attributes */
    private ArrayList<Module> modules;

    /* ModuleList Constructor */
    public ModuleList() {
        modules = new ArrayList<>();
    }

    /**
     * Gets the modules ArrayList
     *
     * @return An ArrayList with the details of the modules.
     */
    public ArrayList<Module> getModules() {
        return this.modules;
    }

    /**
     * Gets a particular module.
     * 
     * @param module A Module object
     * @return A Module object
     */
    public Module getModule(Module module) {
        return getModules().get(this.modules.indexOf(module));
    }

    /**
     * Gets a particular module by its code.
     * 
     * @param code Module's code
     * @return A Module object
     */
    public Module getModuleByCode(String code) {
        for (Module module : getModules())
            if (module.getCode().equals(code))
                return module;
        return null;
    }

    /**
     * Gets a particular module by its title.
     * 
     * @param title Module's title
     * @return A Module object
     */
    public Module getModuleByTitle(String title) {
        for (Module module : getModules())
            if (module.getTitle().equals(title))
                return module;
        return null;
    }

    /**
     * Adds a module to the modules ArrayList.
     * 
     * @param module A Module object
     */
    public void addModule(Module module) {
        boolean isModuleCodeAvailable = true;
        for (Module m : modules) {
            if (m.getTitle().equals(module.getTitle()))
                isModuleCodeAvailable = false;
        }
        if (isModuleCodeAvailable) {
            getModules().add(module);
        }
    }

    /**
     * Removes a particular student from the modules ArrayList based on its title.
     * 
     * @param title Module's title
     */
    public void removeModuleByTitle(String title) {
        getModules().removeIf(module -> module.getTitle().equals(title));
    }

    /**
     * Gets the number of modules stored.
     * 
     * @return Length of the modules ArrayList
     */
    public int length() {
        return getModules().size();
    }

    public String writeObject() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("data/modules");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(modules);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
            return null;
        } catch (IOException ie) {
            return "Can't write modules to file.";
        }
    }

    public String readObject() {
        try {
            FileInputStream fileInputStream = new FileInputStream("data/modules");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            modules = (ArrayList<Module>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return null;
        } catch (FileNotFoundException e) {
            return "File cannot be found. Missing \"modules\" in data folder.";
        } catch (ClassNotFoundException | IOException e) {
            return "Can't read modules from file.";
        }
    }
}
