package mtu_student_app.views.record;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mtu_student_app.App;
import mtu_student_app.models.Record;
import mtu_student_app.models.Student;

/**
 * Student Record showing records of a student.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class StudentRecord {
    /* Attributes */
    private static Stage stage;
    private static Student student;
    private static GridPane modulesInfo;

    /**
     * Display records of a student.
     * 
     * @param studentName Student's name
     */
    public static void display(String studentName) {
        student = getStudentRecords(studentName);

        /* TextFlow Components */
        TextFlow studentHeader = new TextFlow(new Text("Student Info"));
        TextFlow moduleHeader = new TextFlow(new Text("Enrolled Modules"));

        /* Button Components */
        Button sortGradeBtn = new Button("Grade");
        Button sortModuleTitleBtn = new Button("Module Title");

        /* Layout Components */
        HBox btnContainer = new HBox(10, new Text("Sort by:"), sortGradeBtn, sortModuleTitleBtn);

        /* Grid showing student's info */
        GridPane studentInfo = new GridPane();
        studentInfo.addRow(0, new Text("Name:"), new Text(student.getName()));
        studentInfo.addRow(1, new Text("ID:"), new Text(student.getStudentId()));
        studentInfo.addRow(2, new Text("Date of Birth:"), new Text(String.format("%s", student.getDob())));

        /* Grid showing module's info taken by student */
        modulesInfo = new GridPane();
        addRecordRowsToGrid();

        /* Layout Components */
        VBox content = new VBox(6, studentHeader, studentInfo, moduleHeader, btnContainer, modulesInfo);
        ScrollPane root = new ScrollPane(content);
        Scene layout = new Scene(root);
        content.setPadding(new Insets(8, 4, 8, 4));

        /* Styling */
        studentHeader.setStyle("-fx-font-size: 16;");
        studentInfo.setHgap(10);
        studentInfo.setVgap(2);
        moduleHeader.setStyle("-fx-font-size: 16;");
        btnContainer.setAlignment(Pos.BASELINE_LEFT);
        for (Node node : btnContainer.getChildren()) {
            node.setStyle("-fx-font-size: 10;");
            if (node.getClass().equals(Button.class)) {
                node.setCursor(Cursor.HAND);
                node.setStyle("-fx-font-size: 10; -fx-background-color: #e3e1e1; -fx-text-fill: #043043;");
            }
        }
        modulesInfo.setHgap(10);
        modulesInfo.setVgap(2);
        root.setStyle("-fx-background-color:#fff");

        /* Handling Events */
        sortGradeBtn.setOnAction(e -> {
            App.recordControl.getList().sortByGrade();
            addRecordRowsToGrid();
        });
        sortModuleTitleBtn.setOnAction(e -> {
            App.recordControl.getList().sortByModuleTitle();
            addRecordRowsToGrid();
        });

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

    private static void addRecordRowsToGrid() {
        modulesInfo.getChildren().clear();
        /* Grid header */
        modulesInfo.addRow(0, new Text("Code"), new Text("Title"), new Text("Grade"));
        /* Keep track of rows */
        int modulesInfoRow = 1;
        for (Record record : App.recordControl.getList().getRecords(student)) {
            /* Student has been graded */
            if (record.getGrade() != -1) {
                /* Add a row of each module taken by student with their grade */
                modulesInfo.addRow(
                        modulesInfoRow,
                        new Text(record.getModule().getCode()),
                        new Text(record.getModule().getTitle()),
                        new Text(String.valueOf(record.getGrade())));
            } else {
                /* Add each module taken by student without their grade */
                modulesInfo.addRow(
                        modulesInfoRow,
                        new Text(record.getModule().getCode()),
                        new Text(record.getModule().getTitle()));
            }
            modulesInfoRow++;
        }
    }
}
