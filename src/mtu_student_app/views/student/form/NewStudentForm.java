
package mtu_student_app.views.student.form;

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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Form for adding new student.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class NewStudentForm {
    /* Attributes */
    private static Stage stage;
    private static VBox layout;
    private static TextField nameInput, idInput;
    private static DatePicker dobInput;
    private static Text messageTxt;

    /**
     * Displays the New Student form.
     */
    public static void display() {
        /* Label and TextField Components */
        ArrayList<Control> controls = new ArrayList<>(Arrays.asList(
                new Label("Student Name:"),
                new TextField(),
                new Label("Student ID:"),
                new TextField(),
                new Label("Date of Birth:"),
                new DatePicker(LocalDate.now().minusYears(18))));
        Label nameLabel = (Label) controls.get(0),
                idLabel = (Label) controls.get(2),
                dobLabel = (Label) controls.get(4);
        idLabel.setTooltip(new Tooltip("Format: R12345678\nMinimum length: 9"));
        nameInput = (TextField) controls.get(1);
        idInput = (TextField) controls.get(3);
        dobInput = (DatePicker) controls.get(5);

        /* Button Component */
        Button addBtn = new Button("Add Student");

        /* Text and TextFlow Component */
        messageTxt = new Text();
        TextFlow message = new TextFlow(messageTxt);

        /* Layout Components */
        GridPane studentFormGrid = new GridPane();
        studentFormGrid.addRow(0, nameLabel, nameInput);
        studentFormGrid.addRow(1, idLabel, idInput);
        studentFormGrid.addRow(2, dobLabel, dobInput);
        studentFormGrid.getColumnConstraints().addAll(new ColumnConstraints(), new ColumnConstraints());
        studentFormGrid.getColumnConstraints().get(0).setPercentWidth(35);
        studentFormGrid.getColumnConstraints().get(1).setPercentWidth(65);
        layout = new VBox(10, studentFormGrid, addBtn, message);

        /* Styling */
        studentFormGrid.setHgap(10);
        studentFormGrid.setVgap(4);
        studentFormGrid.setAlignment(Pos.CENTER_LEFT);
        studentFormGrid.setPadding(new Insets(2, 6, 2, 6));
        nameInput.setStyle("-fx-background-color: #e3e1e1; -fx-text-fill: #043043;");
        idInput.setStyle("-fx-background-color: #e3e1e1; -fx-text-fill: #043043;");
        dobInput.setStyle("-fx-background-color: #e3e1e1; -fx-text-fill: #043043;");
        addBtn.setCursor(Cursor.HAND);
        addBtn.setStyle("-fx-background-color: #e3e1e1; -fx-text-fill: #043043;");
        message.setTextAlignment(TextAlignment.CENTER);
        messageTxt.setStyle("-fx-fill: #D90626;");
        layout.setAlignment(Pos.CENTER);

        /* Handling Events */
        addBtn.setOnAction(e -> addStudent());

        /* Scene Component */
        Scene scene = new Scene(layout);

        /* Stage Configuration */
        stage = new Stage();
        stage.getIcons().add(new Image("file:resources/mtu_icon.jpg"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add a new student");
        stage.setWidth(360);
        stage.setHeight(220);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();
    }

    /**
     * Add a new a student to StudentList through the controller.
     */
    private static void addStudent() {
        String errorMsg = App.studentControl.addToList(
                nameInput.getText(),
                idInput.getText(),
                dobInput.getValue());
        if (errorMsg == null)
            stage.close();
        messageTxt.setText(errorMsg);
    }
}
