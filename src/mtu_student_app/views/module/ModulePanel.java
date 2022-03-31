package mtu_student_app.views.module;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import mtu_student_app.App;
import mtu_student_app.views.banner.AppBanner;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Application's Module Panel tab.
 * 
 * @author Patrik Richard Szilagyi R00198735
 */
public class ModulePanel extends Tab {
    /* Attributes */
    private final VBox panel = new VBox();
    private final ListView<String> modulesListView;

    /**
     * ModulePanel Constructor.
     * 
     * @param title Tab's title
     */
    public ModulePanel(String title) {
        /* Tab Methods */
        setText(title);
        setClosable(false);
        setContent(panel);
        setStyle("-fx-background-color: #F1AF01; -fx-focus-color: transparent; -fx-text-base-color: #fff;");

        /* AppBanner Component */
        AppBanner banner = new AppBanner();

        /* Text Components */
        Text panelTitle = new Text(title), panelPar1 = new Text(
                "Create a module, register a student for a module or add a student's result to a module they have completed.");

        /* TextFlow Component */
        TextFlow panelHeader = new TextFlow(
                panelTitle,
                new Text(System.lineSeparator()),
                panelPar1);

        /* Button Components */
        ArrayList<Button> buttons = new ArrayList<>(Arrays.asList(
                new Button("Create Module"),
                new Button("Register for Module"),
                new Button("Add Grade")));
        Button addModuleBtn = buttons.get(0),
                registerModuleBtn = buttons.get(1),
                addGradeBtn = buttons.get(2);

        /* ListView Component */
        modulesListView = new ListView<>();
        modulesListView.setItems(App.modulesList);
        modulesListView.setMinHeight(80);

        /* Scene Layout Components */
        HBox btnContainer = new HBox(2, registerModuleBtn, addGradeBtn, new Pane(), addModuleBtn);
        Pane btnWhitespacePane = (Pane) btnContainer.getChildren().get(2);
        VBox modulesListViewContainer = new VBox(modulesListView);
        panel.getChildren().addAll(banner, panelHeader, btnContainer, modulesListViewContainer);

        /* Styling */
        banner.setAlignment(Pos.CENTER);
        panelTitle.setStyle("-fx-font-size: 18;");
        for (Node layoutNode : panel.getChildren()) {
            Pane pane = (Pane) layoutNode;
            pane.setPadding(new Insets(2, 6, 2, 6));
        }
        for (Button btn : buttons) {
            btn.setCursor(Cursor.HAND);
            btn.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
            btn.setStyle("-fx-background-color: #e3e1e1; -fx-text-fill: #043043;");
        }
        btnWhitespacePane.setMinSize(6, 1);
        HBox.setHgrow(btnWhitespacePane, Priority.ALWAYS);
        modulesListView.setStyle("-fx-background-color: #e3e1e1; -fx-text-fill: #043043;");
        panel.setPadding(new Insets(8, 4, 8, 4));
        panel.setStyle("-fx-background-color: #fff;");

        /* Handling Events */
        addModuleBtn.setOnAction(e -> addModule());
        registerModuleBtn.setOnAction(e -> registerModule());
        addGradeBtn.setOnAction(e -> addGrade());
    }

    /**
     * Displays a form to add a new module's details into the college's record.
     */
    private void addModule() {
        App.moduleControl.showCreateModuleForm();
    }

    /**
     * Displays a form to a register a student's for a module.
     */
    private void registerModule() {
        App.studentControl.showRegisterForModuleForm();
    }

    /**
     * Displays a form to add a student's grade to their enrolled module.
     */
    private void addGrade() {
        App.studentControl.showAddGradeForm();
    }
}
