package com.flowforgefx;


import atlantafx.base.theme.CupertinoDark;
import com.flowforgefx.controller.EditorController;
import com.flowforgefx.controller.SidebarController;
import com.flowforgefx.controller.StartController;
import com.flowforgefx.core.Console;
import com.flowforgefx.core.ForgeExecutor;
import com.flowforgefx.views.EditorView;
import com.flowforgefx.views.SidebarView;
import com.flowforgefx.views.StartView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FlowForge extends Application {

    public Scene rootScene;
    public BorderPane root;
    public Pane editorPane;
    public SplitPane splitPane;
    public Console console;

    public ForgeExecutor forgeExecutor;

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


    @Override
    public void start(Stage stage) {
        create(stage);
        createUI();
        createControllers();
        createViews();
        addComponents();

        stage.show();
        Platform.runLater(() -> stage.setMaximized(true));
        editorView.render();
    }

    public void create(Stage stage) {
        Application.setUserAgentStylesheet(new CupertinoDark().getUserAgentStylesheet());
        root = new BorderPane();
        rootScene = new Scene(root, 1200, 640);
        stage.setScene(rootScene);
        stage.setTitle("FlowForge");
    }

    private void createUI() {
        editorPane = new Pane();

        splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.VERTICAL);
        splitPane.setDividerPosition(0, 0.7);

        console = new Console(this);

        forgeExecutor = new ForgeExecutor(this);
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

        startController.switchToProgramPane();

    }


    public static void main(String[] args) {
        Application.launch();
    }
}