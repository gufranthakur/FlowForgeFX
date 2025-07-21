package com.flowforgefx.models.nodes;

import com.flowforgefx.controller.EditorController;
import com.flowforgefx.models.FlowNode;

public class StartNode extends FlowNode {

    private EditorController controller;

    public StartNode(String title, EditorController controller) {
        super(title, controller);
        this.controller = controller;

        inputButton.setVisible(false);
        inputXButton.setVisible(false);

    }

    @Override
    public void execute(boolean isStepExecution) {
        for (FlowNode node : outputXNodes) {
            node.execute(isStepExecution);
        }

        for (FlowNode node : outputXNodes) {
            node.execute(isStepExecution);
        }
    }
}
