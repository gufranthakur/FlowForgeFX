package com.flowforgefx.core;

import com.flowforgefx.FlowForge;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.fxmisc.richtext.InlineCssTextArea;

public class Console extends VBox {

    private FlowForge flowForge;

    private Label titleLabel;
    private Button closeButton, clearButton;
    private HBox toolbar;
    private InlineCssTextArea consoleArea;
    private TextField inputField;

    private boolean isMinimized = false;

    public Console(FlowForge flowforge) {
        this.flowForge = flowforge;

        setupUI();

        this.setPadding(new Insets(3));
        this.setSpacing(3);

        this.getChildren().add(toolbar);
        this.getChildren().add(consoleArea);
        //this.getChildren().add(inputField);

        print("Welcome to FlowForge!", "intro");
    }

    private void setupUI() {
        titleLabel = new Label("Console");
        titleLabel.setStyle("-fx-font-size: 18;");

        closeButton = new Button("▼");
        closeButton.setStyle("-fx-font-size: 12;");
        closeButton.setOnMouseClicked(e -> resizeConsole());

        clearButton = new Button("Clear");
        clearButton.setStyle("-fx-font-size: 12;");
        clearButton.setOnMouseClicked(e -> consoleArea.clear());

        toolbar = new HBox();
        toolbar.setSpacing(5);
        toolbar.setPadding(new Insets(3));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        toolbar.setAlignment(Pos.CENTER);
        toolbar.getChildren().addAll(titleLabel, spacer, clearButton, closeButton);

        consoleArea = new InlineCssTextArea();
        consoleArea.setEditable(false);
        consoleArea.setWrapText(true);
        consoleArea.setBackground(new Background(
                new BackgroundFill(Color.rgb(20, 20, 20), new CornerRadii(0), Insets.EMPTY)
        ));
        VBox.setVgrow(consoleArea, Priority.ALWAYS);

        inputField = new TextField();
    }

    public void print(String value, String type) {

        String color;

        switch (type) {
            case "ERR" -> color = "red";
            case "WARN" -> color = "orange";
            case "SUCCESS" -> color = "limegreen";
            case "Intro" -> color = "blue";
            default -> color = "white";
        }

        int start = consoleArea.getLength();
        consoleArea.appendText(value + "\n");
        int end = consoleArea.getLength();

        consoleArea.setStyle(start, end, "-fx-fill: " + color + ";");
        consoleArea.moveTo(consoleArea.getLength());
    }

    public void clearConsole() {
        consoleArea.clear();
    }

    private void resizeConsole() {
        isMinimized = !isMinimized;

        if (isMinimized) {
            flowForge.splitPane.setDividerPosition(0, 0.7);
            closeButton.setText("▼");
        } else {
            flowForge.splitPane.setDividerPosition(0, 1.0);
            closeButton.setText("▲");
        }
    }

}
