package mtu_student_app;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mtu_student_app.controllers.ModuleController;
import mtu_student_app.controllers.RecordController;
import mtu_student_app.controllers.StudentController;
import mtu_student_app.views.modal.MessageModal;
import mtu_student_app.views.module.ModulePanel;
import mtu_student_app.views.popup.About;
import mtu_student_app.views.popup.SaveWork;
import mtu_student_app.views.record.RecordPanel;
import mtu_student_app.views.student.StudentPanel;

/**
 * MTU Student App - JavaFX application for storing and managing student
 * records.
 * 
 * @author Patrik Richard Szilagyi R00198735
 * @version 3.0.0
 */
public class App extends Application {
    /* Attributes */
    public static ObservableList<String> studentsList = FXCollections.observableArrayList(),
            modulesList = FXCollections.observableArrayList();
    public static final StudentController studentControl = new StudentController();
    public static final ModuleController moduleControl = new ModuleController();
    public static final RecordController recordControl = new RecordController();
    public static boolean changesMade = false;

    private final String title = "MTU Student App";
    private Pane root;

    @Override
    public void start(Stage stage) {
        try {
            /* Menu */
            Menu fileMenu = new Menu("File"),
                    optionsMenu = new Menu("Options");
            MenuItem load = new MenuItem("Load"),
                    save = new MenuItem("Save"),
                    exit = new MenuItem("Exit"),
                    about = new MenuItem("About");
            fileMenu.getItems().addAll(load, save, exit);
            optionsMenu.getItems().add(about);
            MenuBar menu = new MenuBar(fileMenu, optionsMenu);

            /* Tabs */
            TabPane tabPane = new TabPane(
                    new StudentPanel("Students"),
                    new ModulePanel("Modules"),
                    new RecordPanel("Records"));
            tabPane.setSide(Side.LEFT);

            /* Layout Components */
            root = new VBox(menu, tabPane);

            /* Handling Events */
            load.setOnAction(e -> load());
            save.setOnAction(e -> save());
            exit.setOnAction(e -> saveAndExit());
            about.setOnAction(e -> showAbout());
            stage.setOnCloseRequest(e -> saveAndExit());
            load();

            /* Scene Component */
            Scene scene = new Scene(root, 420, 420);

            /* Stage Config */
            stage.getIcons().add(
                    new Image("file:resources/mtu_icon.jpg"));
            stage.setTitle(title);
            stage.setScene(scene);
            stage.setMinWidth(420);
            stage.setMinHeight(420);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Load students, modules and results into application.
     * 
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void load() {
        String studentLoadMsg = studentControl
                .loadList(
                        studentControl.getList().getStudents());
        String moduleLoadMsg = moduleControl
                .loadList(
                        moduleControl.getList().getModules());
        String resultLoadMsg = recordControl
                .loadList(
                        recordControl.getList().getRecords());

        MessageModal.display(studentLoadMsg, root);
        MessageModal.display(moduleLoadMsg, root);
        MessageModal.display(resultLoadMsg, root);
    }

    /**
     * Save students, modules and results.
     * 
     * @throws IOException
     */
    private void save() {
        studentControl.saveList();
        moduleControl.saveList();
        recordControl.saveList();
        changesMade = false;
    }

    /**
     * Save work if changes are made then exits the application.
     */
    private void saveAndExit() {
        if (changesMade) {
            SaveWork.display("Exit", "Would you like to save changes to your work?");
            changesMade = false;
        }
        Platform.exit();
    }

    /**
     * Show application's about details.
     */
    private void showAbout() {
        About.display(title);
    }

    public static void main(String[] args) {
        launch();
    }
}
