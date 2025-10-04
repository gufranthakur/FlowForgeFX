package com.flowforgefx.models.nodes;

import com.flowforgefx.controller.EditorController;
import com.flowforgefx.models.nodes.variables.BooleanNode;
import com.flowforgefx.models.nodes.variables.IntegerNode;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class ConditionalLoop extends FlowNode {

    public boolean condition = false;


    public ConditionalLoop(EditorController controller) {
        super(controller);
        setTitle("Conditional Loop");

        configUI();
    }

    @Override
    protected void configUI() {
        outputXButton.setVisible(false);
    }

    @Override
    public void execute(boolean isStepExecution) {
        controller.currentNodeAtExecution = this;

        for (FlowNode node : inputXNodes) {
            switch (node) {
                case BooleanNode booleanNode -> condition = booleanNode.getValue();
                default -> System.out.println("bweh");
            }
        }

        while (condition) {

            for (FlowNode node : outputNodes) {
                node.execute(isStepExecution);
            }

        }
    }

}
