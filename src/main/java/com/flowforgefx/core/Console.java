package com.flowforgefx.core;

import com.flowforgefx.FlowForge;
import com.flowforgefx.models.nodes.InputNode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.fxmisc.richtext.InlineCssTextArea;


public class Console extends VBox {

    private FlowForge flowForge;
    private Font jetbrainsMono;

    private Label titleLabel;
    private Button closeButton, clearButton;
    private HBox toolbar;
    private InlineCssTextArea consoleArea;
    public TextField inputField;


    private boolean isMinimized = false;

    public Console(FlowForge flowforge) {
        this.flowForge = flowforge;

        setupFont();
        setupUI();

        this.setPadding(new Insets(3));
        this.setSpacing(3);

        this.getChildren().add(toolbar);
        this.getChildren().add(consoleArea);
        this.getChildren().add(inputField);

        print("Welcome to FlowForge!", "intro");
    }

    private void setupFont() {
        jetbrainsMono = Font.loadFont(getClass().getResourceAsStream("/JetBrainsMono-Regular.ttf"), 16);
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
        consoleArea.setStyle("-fx-font-family: 'JetBrains Mono'; -fx-font-size: 14px;");
        VBox.setVgrow(consoleArea, Priority.ALWAYS);

        inputField = new TextField();
        inputField.setStyle("-fx-font-family : 'Jetbrains Mono'; -fx-font-size: 14px;");
        inputField.setPromptText("Type \"help\" for usage");
        inputField.setOnAction(e -> {
            executeCommand(inputField.getText());
        });
    }

    public void print(String value, String type) {

        String color;

        switch (type) {
            case "ERR" -> color = "red";
            case "WARN" -> color = "orange";
            case "SUCCESS" -> color = "limegreen";
            case "Intro" -> color = "#1f75ff";
            default -> color = "white";
        }

        int start = consoleArea.getLength();
        consoleArea.appendText(value + "\n");
        int end = consoleArea.getLength();

        consoleArea.setStyle(start, end, "-fx-fill: " + color + ";");
        consoleArea.moveTo(consoleArea.getLength());
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

    private void executeCommand(String command) {
        command = command.trim();

        if (flowForge.forgeExecutor.isExecuting) {
            if (flowForge.editorController.currentNodeAtExecution instanceof InputNode node) {
                node.input = inputField.getText();
                inputField.clear();
                inputField.setDisable(true);

                synchronized (flowForge.editorController.getExecutor()) {
                    flowForge.editorController.getExecutor().notify();
                }

            }
            return;
        }

        switch (command) {
            case "help" : help(); break;
            case "" : print("\n", "NORMAL"); break;
            case "flowforge" : print("FlowForge is a visual programming tool", "Intro"); break;
            case "run" : flowForge.forgeExecutor.executeProgram(); break;
            case "clear" : consoleArea.clear(); break;
            case "sep" : print("====================================", "NORMAL");break;

            default:  {
                print("Invalid command \"" + command + "\".", "ERR");
                print("Type \"help\" to see commands usage", "WARN");
            }

        }

        inputField.clear();
    }

    private void help() {
        print("FlowForge commands :", "Intro");
        print("     clear: clear terminal", "NORMAL");
        print("     run: Run program", "NORMAL");
        print("     run -s: Run with steps", "NORMAL");
        print("     sep : Add a seperator", "NORMAL");
    }

}
