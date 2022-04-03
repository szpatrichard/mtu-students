package mtu_student_app.views.module.form;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mtu_student_app.App;
import mtu_student_app.models.Record;
import mtu_student_app.models.Student;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Form for adding a student's grade in their enrolled module.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class AddGradeForm {
    /* Attributes */
    private static Stage stage;
    private static ObservableList<String> studentModulesList;
    private static ComboBox<String> studentsBox, studentModulesBox;
    private static TextField gradeInput;
    private static Text messageTxt;

    /**
     * Display the Add Grade form.
     */
    public static void display() {
        /* Label Components */
        Label studentLabel = new Label("Student Name:"),
                moduleLabel = new Label("Module Title:"),
                gradeLabel = new Label("Grade:");

        /* ComboBox Components */
        studentsBox = new ComboBox<String>();
        studentsBox.setItems(App.studentsList);
        studentsBox.setValue("Student name");
        studentModulesBox = new ComboBox<String>();
        studentModulesBox.setValue("Title of module taken by student");
        gradeInput = new TextField();

        /* Text and TextFlow Component */
        messageTxt = new Text();
        TextFlow message = new TextFlow(messageTxt);

        /* Button Component */
        Button addBtn = new Button("Add Result");

        /* Scene Layout Components */
        GridPane resultFormGrid = new GridPane();
        resultFormGrid.addRow(0, studentLabel, studentsBox);
        resultFormGrid.addRow(1, moduleLabel, studentModulesBox);
        resultFormGrid.addRow(2, gradeLabel, gradeInput);
        ArrayList<ColumnConstraints> cols = new ArrayList<>(
                Arrays.asList(new ColumnConstraints(), new ColumnConstraints()));
        cols.get(0).setPercentWidth(30);
        cols.get(1).setPercentWidth(70);
        resultFormGrid.getColumnConstraints().addAll(cols.get(0), cols.get(1));
        VBox layout = new VBox(10, resultFormGrid, addBtn, message);

        /* Layout Styles */
        resultFormGrid.setHgap(10);
        resultFormGrid.setVgap(4);
        resultFormGrid.setAlignment(Pos.CENTER_LEFT);
        resultFormGrid.setPadding(new Insets(2, 6, 2, 6));
        studentsBox.setStyle("-fx-background-color: #e3e1e1; -fx-text-fill: #043043;");
        studentsBox.setPrefWidth(260);
        studentModulesBox.setStyle("-fx-background-color: #e3e1e1; -fx-text-fill: #043043;");
        studentModulesBox.setPrefWidth(260);
        gradeInput.setStyle("-fx-background-color: #e3e1e1; -fx-text-fill: #043043;");
        addBtn.setCursor(Cursor.HAND);
        addBtn.setStyle("-fx-background-color: #e3e1e1; -fx-text-fill: #043043;");
        message.setTextAlignment(TextAlignment.CENTER);
        messageTxt.setStyle("-fx-fill: #D90626;");
        layout.setAlignment(Pos.CENTER);

        /* Handling Events */
        studentsBox.setOnAction(e -> getStudentModules(studentsBox.getValue()));
        addBtn.setOnAction(e -> addGrade());

        /* Scene Component */
        Scene scene = new Scene(layout);

        /* Stage Configuration */
        stage = new Stage();
        stage.getIcons().add(new Image("file:resources/mtu_icon.jpg"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add student's result for a module");
        stage.setMinWidth(360);
        stage.setMinHeight(210);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();
    }

    /**
     * Add a student's grade in their enrolled module through the controller.
     */
    private static void addGrade() {
        String errorMsg;
        if (studentsBox.getSelectionModel().getSelectedIndex() != -1 &&
                studentModulesBox.getSelectionModel().getSelectedIndex() != -1) {
            try {
                errorMsg = App.recordControl.addGrade(
                        studentsBox.getValue(),
                        studentModulesBox.getValue(),
                        Integer.parseInt(gradeInput.getText()));
            } catch (NumberFormatException nfe) {
                errorMsg = "Invalid grade format.";
            }
            if (errorMsg == null)
                stage.close();
        } else {
            errorMsg = "Select a valid option.";
        }
        messageTxt.setText(errorMsg);
    }

    /**
     * Gets the student's enrolled modules and adds them to an observable list.
     * 
     * @param studentName Student's name
     */
    private static void getStudentModules(String studentName) {
        if (!studentName.isBlank()) {
            Student student = App.studentControl.getList().getStudentByName(studentName);
            studentModulesList = FXCollections.observableArrayList();
            studentModulesBox.setItems(studentModulesList);
            for (Record record : App.recordControl.getList().getRecords(student)) {
                studentModulesList.add(record.getModule().getTitle());
            }
        }
    }
}
