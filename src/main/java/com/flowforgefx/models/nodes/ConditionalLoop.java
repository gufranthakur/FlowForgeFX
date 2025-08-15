package com.flowforgefx.models.nodes;

import com.flowforgefx.controller.EditorController;

public class ConditionalLoop extends FlowNode {

    public boolean condition = false;

    public ConditionalLoop(EditorController controller) {
        super(controller);
        setTitle("Conditional Loop");


    }

    @Override
    public void execute(boolean isStepExecution) {

    }

}
