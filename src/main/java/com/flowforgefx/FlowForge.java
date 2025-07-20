package com.flowforgefx;

import atlantafx.base.theme.CupertinoDark;
import com.flowforgefx.controller.EditorController;
import com.flowforgefx.controller.SidebarController;
import com.flowforgefx.controller.StartController;
import com.flowforgefx.views.EditorView;
import com.flowforgefx.views.SidebarView;
import com.flowforgefx.views.StartView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class FlowForge extends Application {

    public BorderPane root;

    public StartController startController;
    public SidebarController sidebarController;
    public EditorController editorController;

    public StartView startView;
    public EditorView editorView;
    public SidebarView sidebarView;

    public static final String primaryTheme = "#257df7";
    public static final String secondaryTheme = "#e68312";
    public static final String greenTheme = "#11c232";

    public static final int largeFont = 20;
    public static final int mediumFont = 16;
    public static final int smallFont = 12;

    @Override
    public void start(Stage stage) {
        create(stage);
        createControllers();
        createViews();
        addComponents();
        stage.show();
    }

    public void create(Stage stage) {
        Application.setUserAgentStylesheet(new CupertinoDark().getUserAgentStylesheet());
        root = new BorderPane();
        Scene scene = new Scene(root, 1200, 640);
        stage.setScene(scene);
        stage.setTitle("FlowForge");
    }

    private void createControllers() {
        startController = new StartController(this);
        sidebarController = new SidebarController(this);
        editorController = new EditorController(this);
    }

    private void createViews() {
        startView = new StartView(startController);
        startView.createComponents();

        editorView = new EditorView(editorController);
        sidebarView = new SidebarView(sidebarController);
    }

    public void addComponents() {
        startView.addComponent();
        sidebarView.addComponents();

        root.setCenter(startView);
    }


    public static void main(String[] args) {
        Application.launch();
    }
}