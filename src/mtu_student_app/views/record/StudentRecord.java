package mtu_student_app.views.record;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mtu_student_app.App;
import mtu_student_app.models.Module;
import mtu_student_app.models.Student;

/**
 * Student Record showing records of a student.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class StudentRecord {
    /* Attributes */
    private static Stage stage;

    /**
     * Display records of a student.
     * 
     * @param studentName Student's name
     */
    public static void display(String studentName) {
        /* Student object */
        Student student = getStudentRecords(studentName);

        /* TextFlow Components */
        TextFlow studentHeader = new TextFlow(new Text("Student Info"));
        TextFlow moduleHeader = new TextFlow(new Text("Enrolled Modules"));

        /* Grid showing student's info */
        GridPane studentInfo = new GridPane();
        studentInfo.addRow(0, new Text("Name:"), new Text(student.getName()));
        studentInfo.addRow(1, new Text("ID:"), new Text(student.getStudentId()));
        studentInfo.addRow(2, new Text("Date of Birth:"), new Text(String.format("%s", student.getDob())));

        /* Grid showing module's info taken by student */
        GridPane moduleInfo = new GridPane();
        /* Grid header */
        moduleInfo.addRow(0, new Text("Code"), new Text("Title"), new Text("Grade"));
        /* Keep track of rows */
        int modulesInfoRow = 1;
        for (Module module : student.getResults().keySet()) {
            /* Student has been graded */
            if (student.getResults().get(module) != -1) {
                /* Add each module taken by student with their grade */
                moduleInfo.addRow(
                        modulesInfoRow,
                        new Text(module.getCode()),
                        new Text(module.getTitle()),
                        new Text(String.valueOf(student.getResults().get(module))));
            } else {
                /* Add each module taken by student without their grade */
                moduleInfo.addRow(
                        modulesInfoRow,
                        new Text(module.getCode()),
                        new Text(module.getTitle()));
            }
            modulesInfoRow++;
        }

        /* Layout Components */
        VBox content = new VBox(6, studentHeader, studentInfo, moduleHeader, moduleInfo);
        ScrollPane root = new ScrollPane(content);
        Scene layout = new Scene(root);
        content.setPadding(new Insets(8, 4, 8, 4));

        /* Styling */
        studentHeader.setStyle("-fx-font-size: 16;");
        studentInfo.setHgap(10);
        studentInfo.setVgap(2);
        moduleHeader.setStyle("-fx-font-size: 16;");
        moduleInfo.setHgap(10);
        moduleInfo.setVgap(2);
        root.setStyle("-fx-background-color:#fff");

        /* Stage Configuration */
        stage = new Stage();
        stage.getIcons().add(new Image("file:resources/mtu_icon.jpg"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(layout);
        stage.setTitle(String.format("%s's Info", student.getName()));
        stage.setMinWidth(380);
        stage.setHeight(240);
        stage.setResizable(false);
        stage.showAndWait();
    }

    /**
     * Get a student based with their name as argument.
     * 
     * @param studentName Student's name
     * @return Student
     */
    private static Student getStudentRecords(String studentName) {
        return App.studentControl
                .getList()
                .getStudentByName(studentName);
    }
}
