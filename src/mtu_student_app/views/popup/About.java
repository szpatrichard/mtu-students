package mtu_student_app.views.popup;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * JavaFX GUI popup displaying information of the application.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class About {
    /**
     * Display the About popup.
     * 
     * @param title Application's title
     */
    public static void display(String title) {
        /* Text */
        Text titleText = new Text("MTU Student App"),
                authorText = new Text("Patrik Richard Szilagyi"),
                copyrightInfoText = new Text("\u00a9 2022");

        VBox layout = new VBox(10, titleText, authorText, copyrightInfoText);
        layout.setAlignment(Pos.CENTER);

        /* Component Styling */
        titleText.setStyle("-fx-font-size: 14");

        /* Stage Configuration */
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:resources/mtu_icon.jpg"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(new BorderPane(layout)));
        stage.setTitle("About");
        stage.setResizable(false);
        stage.setWidth(260);
        stage.setHeight(120);
        stage.show();
    }
}
