package mtu_student_app.views.popup;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mtu_student_app.App;

/**
 * JavaFX GUI dialog box prompting to save student records.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class SaveWork {
    /**
     * Display the dialog box.
     * 
     * @param title   Window title
     * @param message Message to display
     */
    public static void display(String title, String message) {
        Stage stage = new Stage();
        /* Control Components */
        Label label = new Label(message);
        Button saveBtn = new Button("Save"),
                discardBtn = new Button("Discard");

        /* Handling Events */
        saveBtn.setOnAction(e -> {
            App.studentControl.saveList();
            App.studentControl.saveResults();
            App.moduleControl.saveList();
            stage.close();
        });
        discardBtn.setOnAction(e -> System.exit(0));

        /* Scene Layout Components */
        HBox labelRow = new HBox(10, label),
                btnRow = new HBox(10, saveBtn, discardBtn);
        VBox root = new VBox(10, labelRow, btnRow);

        /* Styling */
        saveBtn.setCursor(Cursor.HAND);
        saveBtn.setStyle("-fx-background-color: #e3e1e1; -fx-text-fill: #043043;");
        discardBtn.setCursor(Cursor.HAND);
        discardBtn.setStyle("-fx-background-color: #e3e1e1; -fx-text-fill: #043043;");
        labelRow.setAlignment(Pos.CENTER);
        btnRow.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));

        /* Scene Component */
        Scene scene = new Scene(root, 280, 80);

        /* Stage Configuration */
        stage.getIcons().add(new Image("file:resources/mtu_icon.jpg"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
