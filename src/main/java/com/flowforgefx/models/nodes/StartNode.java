package com.flowforgefx.models.nodes;

import com.flowforgefx.controller.EditorController;
import com.flowforgefx.models.FlowNode;
import javafx.geometry.Point2D;

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
        for (FlowNode outputNode : outputNodes) {
            outputNode.execute(isStepExecution);
        }

        for (FlowNode outputXNode : outputXNodes) {
            outputXNode.execute(isStepExecution);
        }

    }


}
