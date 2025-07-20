package com.flowforgefx.views;

import com.flowforgefx.controller.StartController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import static com.flowforgefx.FlowForge.*;

public class StartView extends VBox {

    private StartController startController;

    private Label titleLabel;
    private Button newProjectButton, openProjectButton;

    private VBox buttonBox;

    public StartView(StartController startController) {
        this.startController = startController;
    }

    public void createComponents() {
        titleLabel = new Label("FlowForge");
        titleLabel.setStyle("-fx-font-size: 72px;");

        newProjectButton = new Button("New Project");
        newProjectButton.setPrefWidth(150);
        newProjectButton.setStyle(String.format(
                "-fx-background-color: %s; -fx-font-size: %d;",
                primaryTheme, largeFont
        ));
        newProjectButton.setOnMouseClicked(e -> startController.switchToProgramPane());

        openProjectButton = new Button("Open Project");
        openProjectButton.setPrefWidth(150);
        openProjectButton.setStyle(String.format(
                "-fx-background-color: %s; -fx-font-size: %d",
                secondaryTheme, largeFont
        ));

        buttonBox = new VBox();
        buttonBox.setSpacing(12);
        buttonBox.setAlignment(Pos.CENTER);
    }

    public void addComponent() {
        buttonBox.getChildren().add(titleLabel);
        buttonBox.getChildren().add(newProjectButton);
        buttonBox.getChildren().add(openProjectButton);

        this.getChildren().add(buttonBox);
        this.setAlignment(Pos.CENTER);
    }

}
