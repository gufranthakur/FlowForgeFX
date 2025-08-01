package com.flowforgefx.models.nodes;

import com.flowforgefx.controller.EditorController;
import javafx.scene.control.Spinner;

public class DelayNode extends FlowNode {

    public Spinner<Integer> spinner;

    public DelayNode(EditorController controller) {
        super(controller);
        setTitle("Delay");

        spinner = new Spinner<>(0, 100000, 1000);
        spinner.setLayoutX(componentX);
        spinner.setLayoutY(componentY);
        spinner.setPrefWidth(componentWidth);
        spinner.setPrefHeight(componentHeight);
        spinner.setEditable(true);

        this.getChildren().add(spinner);
    }

    @Override
    public void execute(boolean isStepExecution) {
        controller.currentNodeAtExecution = this;

        synchronized (controller.getExecutor()) {
            try {
                controller.getExecutor().wait(spinner.getValue());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        
        for (FlowNode node : outputNodes) {
            node.execute(isStepExecution);
        }
    }
}
