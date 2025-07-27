package com.flowforgefx.models.nodes;

import com.flowforgefx.controller.EditorController;
import com.flowforgefx.models.FlowNode;
import javafx.geometry.Point2D;

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
        for (FlowNode outputNode : outputNodes) {
            outputNode.execute(isStepExecution);
        }

        for (FlowNode outputXNode : outputXNodes) {
            outputXNode.execute(isStepExecution);
        }

    }


}
