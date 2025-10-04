package com.flowforgefx.models.nodes;

import com.flowforgefx.controller.EditorController;
import javafx.application.Platform;
import javafx.scene.control.Spinner;

public class LoopNode extends FlowNode {

    public int iterationValue;
    public Spinner<Integer> iterationSpinner;

    public LoopNode(EditorController controller) {
        super(controller);
        setTitle("Loop");
    }
    @Override
    protected void configUI() {
        iterationSpinner = new Spinner<>(0, 1000, 10);
        iterationSpinner.setLayoutX(componentX);
        iterationSpinner.setLayoutY(componentY);
        iterationSpinner.setPrefWidth(componentWidth);
        iterationSpinner.setPrefHeight(componentHeight);

        this.getChildren().add(iterationSpinner);
    }

    @Override
    public void execute(boolean isStepExecution) {
        controller.currentNodeAtExecution = this;
        iterationValue = iterationSpinner.getValue();

        for (FlowNode node : inputXNodes) {
            if (node instanceof InputNode) {
                try {
                    iterationValue = Integer.parseInt(((InputNode) node).input);
                    Platform.runLater(() -> iterationSpinner.setDisable(true));
                } catch (NumberFormatException e) {
                    Platform.runLater(() -> controller.getConsole().print("Error : Provided String value instead of int", "ERR"));
                    Platform.runLater(() -> controller.getConsole().print("Defaulting to default iteration value", "WARN"));
                }
            }
        }

        for (int i = 0; i <= iterationValue; i++) {

            for (FlowNode node : outputNodes) {
                node.execute(isStepExecution);
            }

        }
    }
}

