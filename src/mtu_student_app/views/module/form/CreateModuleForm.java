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
 * Form for creating a new module.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class CreateModuleForm {
    /* Attributes */
    private static Stage stage;
    private static VBox layout;
    private static TextField codeInput, titleInput;
    private static Text messageTxt;

    /**
     * Display the Create Module form.
     */
    public static void display() {
        /* Label and TextField Components */
        ArrayList<Control> controls = new ArrayList<>(Arrays.asList(
                new Label("Module Code:"),
                new TextField(),
                new Label("Module Title:"),
                new TextField()));
        Label codeLabel = (Label) controls.get(0),
                titleLabel = (Label) controls.get(2);
        codeLabel.setTooltip(new Tooltip("Format: ABCD1234"));
        codeInput = (TextField) controls.get(1);
        titleInput = (TextField) controls.get(3);

        /* Button Components */
        Button addBtn = new Button("Add Module");

        /* Text and TextFlow Component */
        messageTxt = new Text();
        TextFlow message = new TextFlow(messageTxt);

        /* Layout Components */
        GridPane moduleFormGrid = new GridPane();
        moduleFormGrid.addRow(0, codeLabel, codeInput);
        moduleFormGrid.addRow(1, titleLabel, titleInput);
        ArrayList<ColumnConstraints> cols = new ArrayList<>(
                Arrays.asList(new ColumnConstraints(), new ColumnConstraints()));
        cols.get(0).setPercentWidth(30);
        cols.get(1).setPercentWidth(70);
        moduleFormGrid.getColumnConstraints().addAll(cols.get(0), cols.get(1));
        layout = new VBox(10, moduleFormGrid, addBtn, message);

        /* Styling */
        moduleFormGrid.setHgap(10);
        moduleFormGrid.setVgap(4);
        moduleFormGrid.setAlignment(Pos.CENTER_LEFT);
        moduleFormGrid.setPadding(new Insets(2, 6, 2, 6));
        codeInput.setStyle("-fx-background-color: #e3e1e1; -fx-text-fill: #043043;");
        titleInput.setStyle("-fx-background-color: #e3e1e1; -fx-text-fill: #043043;");
        addBtn.setCursor(Cursor.HAND);
        addBtn.setStyle("-fx-background-color: #e3e1e1; -fx-text-fill: #043043;");
        message.setTextAlignment(TextAlignment.CENTER);
        messageTxt.setStyle("-fx-fill: #D90626;");
        layout.setAlignment(Pos.CENTER);

        /* Handling Events */
        addBtn.setOnAction(e -> addModule());

        /* Scene Component */
        Scene scene = new Scene(layout);

        /* Stage Configuration */
        stage = new Stage();
        stage.getIcons().add(new Image("file:resources/mtu_icon.jpg"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Create a module");
        stage.setWidth(360);
        stage.setHeight(180);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();
    }

    /**
     * Add a new module through the controller.
     */
    private static void addModule() {
        String errorMsg = App.moduleControl.addToList(
                codeInput.getText(),
                titleInput.getText());
        if (errorMsg == null) {
            stage.close();
        }
        messageTxt.setText(errorMsg);
    }
}
