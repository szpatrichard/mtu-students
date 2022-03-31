package mtu_student_app.views.record;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import mtu_student_app.App;
import mtu_student_app.views.banner.AppBanner;

/**
 * Application's Record Panel tab.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class RecordPanel extends Tab {
    /* Attributes */
    private static ListView<String> studentsListView;
    private final VBox panel = new VBox();

    /**
     * RecordPanel Constructor.
     * 
     * @param title Tab's title
     */
    public RecordPanel(String title) {
        /* Tab Methods */
        setText(title);
        setContent(panel);
        setClosable(false);
        setStyle("-fx-background-color: #00A2E1; -fx-focus-color: transparent; -fx-text-base-color: #fff");

        /* AppBanner Component */
        AppBanner banner = new AppBanner();

        /* Text Components */
        Text panelTitle = new Text(title), panelPar1 = new Text(
                "View records of each student. Click on a student in the list to see more of their information.");

        /* TextFlow Component */
        TextFlow panelHeader = new TextFlow(
                panelTitle,
                new Text(System.lineSeparator()),
                panelPar1);

        /* ListView Component */
        studentsListView = new ListView<>();
        studentsListView.setItems(App.studentsList);
        studentsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        /* Layout Components */
        VBox studentListViewContainer = new VBox(studentsListView);
        panel.getChildren().addAll(banner, panelHeader, studentListViewContainer);

        /* Styling */
        banner.setAlignment(Pos.CENTER);
        panelTitle.setStyle("-fx-font-size: 18");
        for (Node layoutNode : panel.getChildren()) {
            Pane pane = (Pane) layoutNode;
            pane.setPadding(new Insets(2, 6, 2, 6));
        }
        studentsListView.setMinHeight(80);
        studentsListView.setStyle("-fx-background-color: #e3e1e1; -fx-text-fill: #043043;");
        panel.setPadding(new Insets(8, 4, 8, 4));
        panel.setStyle("-fx-background-color: #fff");

        /* Handling Events */
        studentsListView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                showStudentInfo();
            }
        });
        studentsListView.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                showStudentInfo();
            }
        });
    }

    /**
     * Show the window displaying the information of selected student.
     */
    public void showStudentInfo() {
        StudentRecord.display(studentsListView.getSelectionModel().getSelectedItem());
    }
}
