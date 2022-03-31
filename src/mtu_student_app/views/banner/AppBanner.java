package mtu_student_app.views.banner;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

/**
 * The application's banner.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class AppBanner extends FlowPane {
    /* Image file */
    private final Image img = new Image("file:resources/mtu_banner.jpg");

    /* ImageView Component */
    private final ImageView appBanner = new ImageView(img);

    /* AppBanner Default Constructor */
    public AppBanner() {
        /* Set width of the banner */
        appBanner.setFitWidth(200);
        /* Set height of the banner */
        appBanner.setFitHeight(80);

        /* Add the banner to component */
        getChildren().add(appBanner);
    }
}
