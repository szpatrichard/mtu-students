package mtu_student_app.views.student;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import mtu_student_app.App;
import mtu_student_app.views.banner.AppBanner;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Application's Student Panel tab.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class StudentPanel extends Tab {
    /* Attributes */
    private final VBox panel = new VBox();
    private final Button editBtn, removeBtn;
    private final ListView<String> studentsListView;

    /**
     * StudentPanel Constructor
     * 
     * @param title Tab's title
     */
    public StudentPanel(String title) {
        /* Tab Methods */
        setText(title);
        setContent(panel);
        setClosable(false);
        setStyle("-fx-background-color: #D90626; -fx-focus-color: transparent; -fx-text-base-color: #fff");

        /* AppBanner Component */
        AppBanner banner = new AppBanner();

        /* Text Components */
        Text panelTitle = new Text(title),
                panelPar1 = new Text(
                        "Register a new student with their details or edit, remove registered students in the record.");

        /* TextFlow Component */
        TextFlow panelHeader = new TextFlow(
                panelTitle,
                new Text(System.lineSeparator()),
                panelPar1);

        /* Button Components */
        ArrayList<Button> buttons = new ArrayList<>(Arrays.asList(
                new Button("New Student"),
                new Button("Edit"),
                new Button("Remove")));
        Button newStudentBtn = buttons.get(0);
        editBtn = buttons.get(1);
        removeBtn = buttons.get(2);
        editBtn.setDisable(true);
        removeBtn.setDisable(true);

        /* ListView Component */
        studentsListView = new ListView<>();
        studentsListView.setItems(App.studentsList);
        studentsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        /* Layout Components */
        HBox btnContainer = new HBox(2, newStudentBtn, new Pane(), editBtn, removeBtn);
        Pane btnWhitespacePane = (Pane) btnContainer.getChildren().get(1);
        VBox studentsListViewContainer = new VBox(studentsListView);
        panel.getChildren().addAll(banner, panelHeader, btnContainer,
                studentsListViewContainer);

        /* Styling */
        banner.setAlignment(Pos.CENTER);
        panelTitle.setStyle("-fx-font-size: 18");
        for (Node layoutNode : panel.getChildren()) {
            Pane pane = (Pane) layoutNode;
            pane.setPadding(new Insets(2, 6, 2, 6));
        }
        for (Button btn : buttons) {
            btn.setCursor(Cursor.HAND);
            btn.setStyle("-fx-background-color: #e3e1e1; -fx-text-fill: #043043;");
            btn.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
        }
        btnWhitespacePane.setMinSize(6, 1);
        HBox.setHgrow(btnWhitespacePane, Priority.ALWAYS);
        studentsListView.setMinHeight(80);
        studentsListView.setStyle("-fx-background-color: #e3e1e1; -fx-text-fill: #043043;");
        panel.setPadding(new Insets(8, 4, 8, 4));
        panel.setStyle("-fx-background-color: #fff");

        /* Handling Events */
        newStudentBtn.setOnAction(e -> newStudent());
        editBtn.setOnAction(e -> editStudent());
        removeBtn.setOnAction(e -> removeStudent());
        studentsListView.setOnMouseClicked(e -> selectStudentName());
        studentsListView.setOnKeyReleased(e -> selectStudentName());
    }

    /**
     * Displays a form to add a new student's details into the college's record.
     */
    private void newStudent() {
        App.studentControl.showNewStudentForm();
    }

    /**
     * Displays a form to edit the details of a student in the college's record.
     */
    private void editStudent() {
        /* TODO: Implement editStudent() */
        String selectedStudentName = selectStudentName();
        System.out.println("Edit " + selectedStudentName);
    }

    /**
     * Removes a student from the list and the college's record.
     */
    private void removeStudent() {
        String selectedStudentName = selectStudentName();
        removeBtn.setDisable(false);
        App.studentControl.removeFromList(selectedStudentName);
        editBtn.setDisable(true);
        removeBtn.setDisable(true);
    }

    /**
     * Selects a student for removal from the studentsListView.
     * 
     * @return Name of the student.
     */
    private String selectStudentName() {
        editBtn.setDisable(false);
        removeBtn.setDisable(false);
        return studentsListView.getSelectionModel().getSelectedItem();
    }
}
