package com.flowforgefx.models.nodes;

import com.flowforgefx.controller.EditorController;
import javafx.scene.control.Spinner;

public class DelayNode extends FlowNode {

    public Spinner<Integer> spinner;

    public DelayNode(EditorController controller) {
        super(controller);
        setTitle("Delay");

        configUI();
    }

    @Override
    protected void configUI() {
        spinner = new Spinner<>(0, 100000, 1000);
        spinner.setEditable(true);

        this.placeComponent(spinner);
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
