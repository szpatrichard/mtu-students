package mtu_student_app.views.modal;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

/**
 * A box displaying a message modal attached to a parent node.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class MessageModal {
    private static HBox modal;

    /**
     * Display the message modal.
     * 
     * @param message Message to display
     * @param parent  Parent node
     */
    public static void display(String message, Node parent) {
        /* TextFlow Component */
        TextFlow messageContainer = new TextFlow(new Text(message));

        /* Message modal */
        modal = new HBox(messageContainer);

        /* Parent node to attach message modal to */
        Pane pane = (Pane) parent;
        pane.getChildren().add(modal);

        /* Create the animation effect */
        FadeTransition fader = createFader();
        SequentialTransition fade = new SequentialTransition(modal, fader);
        fade.play();

        /* Event Handler */
        fader.setOnFinished(e -> pane.getChildren().remove(pane.getChildren().size() - 1));

        /* Styling */
        modal.setAlignment(Pos.CENTER);
        modal.setPadding(new Insets(4, 6, 4, 6));
        modal.setStyle("-fx-background-color: #aaa; -fx-text-size: 8;");
    }

    /**
     * Fading Animation
     * 
     * @return Fade Transition Effect
     */
    private static FadeTransition createFader() {
        FadeTransition fade = new FadeTransition(Duration.seconds(5), modal);
        fade.setFromValue(1);
        fade.setToValue(0);
        return fade;
    }
}
