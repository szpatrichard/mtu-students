package mtu_student_app.views.module.form;

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

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Form for registering a student for a module.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class RegisterForModuleForm {
    private static Stage stage;
    private static ComboBox<String> studentsBox, modulesBox;
    private static Text messageTxt;

    /**
     * Display the Register for Module form.
     */
    public static void display() {
        /* Label Components */
        Label studentLabel = new Label("Student Name:"),
                moduleLabel = new Label("Module Title:");

        /* ComboBox Components */
        studentsBox = new ComboBox<>();
        modulesBox = new ComboBox<>();
        studentsBox.setItems(App.studentsList);
        studentsBox.setValue("Student name");
        modulesBox.setItems(App.modulesList);
        modulesBox.setValue("Module title");

        /* Button Components */
        Button registerBtn = new Button("Register");

        /* Text and TextFlow Component */
        messageTxt = new Text();
        TextFlow message = new TextFlow(messageTxt);

        /* Layout Components */
        GridPane moduleFormGrid = new GridPane();
        moduleFormGrid.addRow(0, studentLabel, studentsBox);
        moduleFormGrid.addRow(1, moduleLabel, modulesBox);
        ArrayList<ColumnConstraints> cols = new ArrayList<>(
                Arrays.asList(new ColumnConstraints(), new ColumnConstraints()));
        cols.get(0).setPercentWidth(30);
        cols.get(1).setPercentWidth(70);
        moduleFormGrid.getColumnConstraints().addAll(cols.get(0), cols.get(1));
        VBox layout = new VBox(10, moduleFormGrid, registerBtn, message);

        /* Styling */
        moduleFormGrid.setHgap(10);
        moduleFormGrid.setVgap(4);
        moduleFormGrid.setAlignment(Pos.CENTER_LEFT);
        moduleFormGrid.setPadding(new Insets(2, 6, 2, 6));
        studentsBox.setStyle("-fx-background-color: #e3e1e1; -fx-text-fill: #043043;");
        modulesBox.setStyle("-fx-background-color: #e3e1e1; -fx-text-fill: #043043;");
        registerBtn.setCursor(Cursor.HAND);
        registerBtn.setStyle("-fx-background-color: #e3e1e1; -fx-text-fill: #043043;");
        message.setTextAlignment(TextAlignment.CENTER);
        messageTxt.setStyle("-fx-fill: #D90626;");
        layout.setAlignment(Pos.CENTER);

        /* Handling Events */
        registerBtn.setOnAction(e -> registerStudentForModule());

        /* Scene Component */
        Scene scene = new Scene(layout);

        /* Styling */
        studentsBox.setPrefWidth(260);
        modulesBox.setPrefWidth(260);

        /* Stage Configuration */
        stage = new Stage();
        stage.getIcons().add(new Image("file:resources/mtu_icon.jpg"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Register student for a module");
        stage.setWidth(360);
        stage.setHeight(180);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();
    }

    /**
     * Register student for a module through the controller.
     */
    private static void registerStudentForModule() {
        String errorMsg;
        if (studentsBox.getSelectionModel().getSelectedIndex() >= 0 ||
                modulesBox.getSelectionModel().getSelectedIndex() >= 0) {
            errorMsg = App.studentControl.registerModule(
                    studentsBox.getValue(),
                    modulesBox.getValue());
            if (errorMsg == null)
                stage.close();
        }
        errorMsg = "Select a valid option.";
        messageTxt.setText(errorMsg);
    }
}
