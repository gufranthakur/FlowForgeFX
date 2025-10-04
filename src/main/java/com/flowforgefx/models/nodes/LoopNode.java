package com.flowforgefx.models.nodes;

import com.flowforgefx.controller.EditorController;
import com.flowforgefx.models.nodes.variables.IntegerNode;
import com.flowforgefx.utils.DialogUtility;
import javafx.application.Platform;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class LoopNode extends FlowNode {

    public int iterationValue;
    public TextField iterationField;

    public LoopNode(EditorController controller) {
        super(controller);
        setTitle("Loop");

        configUI();
    }
    @Override
    protected void configUI() {
        inputXButton.setText("times");
        outputXButton.setText("iteration");

        iterationField = new TextField();
        iterationField.setPromptText("iteration amount");

        placeComponent(iterationField);
    }

    @Override
    public void execute(boolean isStepExecution) {
        controller.currentNodeAtExecution = this;
        iterationValue = Integer.parseInt(iterationField.getText());

        for (FlowNode node : inputXNodes) {
            if (node instanceof InputNode inputNode) {
                try {
                    iterationValue = Integer.parseInt(inputNode.input);
                    Platform.runLater(() -> iterationField.setDisable(true));
                } catch (NumberFormatException e) {
                    Platform.runLater(() -> controller.getConsole().print("Error : Provided String value instead of int", "ERR"));
                    Platform.runLater(() -> controller.getConsole().print("Defaulting to default iteration value", "WARN"));
                }
            } else if (node instanceof IntegerNode integerNode) {
                iterationValue = integerNode.getValue();
            }
        }

        if (iterationValue > 1000) {
            Platform.runLater(() -> controller.getConsole().print("Warning : Iteration number is large", "WARN"));
        }

        for (int i = 0; i <= iterationValue; i++) {

            for (FlowNode node : outputNodes) {
                node.execute(isStepExecution);
            }

        }
    }
}

