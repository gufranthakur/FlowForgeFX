package com.flowforgefx.models.nodes;

import com.flowforgefx.controller.EditorController;
import javafx.application.Platform;
import javafx.scene.control.TextField;



public class InputNode extends FlowNode {

    private TextField promptField;
    public String input;

    public InputNode(EditorController controller) {
        super(controller);
        setTitle("Input");

        inputXButton.setVisible(false);

        promptField = new TextField();
        promptField.setPromptText("Prompt");
        promptField.setLayoutX(componentX);
        promptField.setLayoutY(componentY);
        promptField.setPrefWidth(componentWidth);
        promptField.setPrefHeight(componentHeight);

        this.getChildren().add(promptField);
    }

    @Override
    public void execute(boolean isStepExecution) {
        controller.currentNodeAtExecution = this;

        Platform.runLater(() -> {
            controller.getFlowForge().console.print(promptField.getText(), "NORMAL");
            TextField field = controller.getFlowForge().console.inputField;
            field.setDisable(false);
        });

        synchronized (controller.getExecutor()) {
            try {
                controller.getExecutor().wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for (FlowNode node : outputNodes) {
            node.execute(isStepExecution);
        }

    }
}
