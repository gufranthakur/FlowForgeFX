package com.flowforgefx.models.nodes;

import com.flowforgefx.controller.EditorController;

public class StartNode extends FlowNode {

    public StartNode(EditorController controller) {
        super(controller);
        this.controller = controller;
        setTitle("Start");

        inputButton.setVisible(false);
        inputXButton.setVisible(false);

    }

    @Override
    public void execute(boolean isStepExecution) {
        controller.currentNodeAtExecution = this;

        for (FlowNode outputNode : outputNodes) {
            outputNode.execute(isStepExecution);
        }

        for (FlowNode outputXNode : outputXNodes) {
            outputXNode.execute(isStepExecution);
        }

    }


}
