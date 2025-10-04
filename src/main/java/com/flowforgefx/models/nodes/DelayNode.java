package com.flowforgefx.models.nodes;

import com.flowforgefx.controller.EditorController;
import com.flowforgefx.models.nodes.variables.FloatNode;
import com.flowforgefx.models.nodes.variables.IntegerNode;
import javafx.scene.control.Spinner;

public class DelayNode extends FlowNode {

    public Spinner<Integer> spinner;
    private int delayValue;

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
        delayValue = spinner.getValue();

        for (FlowNode node : inputXNodes) {
            if (node instanceof IntegerNode integerNode) {
                delayValue = integerNode.getValue();
            } else if (node instanceof FloatNode floatNode) {
                delayValue = floatNode.getValue().intValue();
            } else if (node instanceof InputNode inputNode) {
                delayValue = Integer.parseInt(inputNode.input);
            }
        }

        synchronized (controller.getExecutor()) {
            try {
                controller.getExecutor().wait(delayValue);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for (FlowNode node : outputNodes) {
            node.execute(isStepExecution);
        }
    }
}
