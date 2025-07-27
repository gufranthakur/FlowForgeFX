package com.flowforgefx.models.nodes;

import com.flowforgefx.controller.EditorController;
import com.flowforgefx.models.FlowNode;

public class DelayNode extends FlowNode {

    public DelayNode(String title, EditorController controller) {
        super(title, controller);
    }

    @Override
    public void execute(boolean isStepExecution) {

    }
}
