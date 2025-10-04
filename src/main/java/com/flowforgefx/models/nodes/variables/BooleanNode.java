package com.flowforgefx.models.nodes.variables;

import com.flowforgefx.controller.EditorController;
import com.flowforgefx.models.nodes.FlowNode;
import com.flowforgefx.models.project.ForgeProject;
import javafx.scene.control.CheckBox;

public class BooleanNode extends FlowNode {

    private EditorController controller;
    private CheckBox valueBox;
    private String variableName;

    public BooleanNode(EditorController controller, String variableName) {
        super(controller, variableName);
        this.controller = controller;
        this.variableName = variableName;

        configUI();
    }


    @Override
    protected void configUI() {
        valueBox = new CheckBox("Boolean Value");

        this.placeComponent(valueBox);
    }

    @Override
    public void execute(boolean isStepExecution) {

        setValue(valueBox.isSelected());

    }

    public Boolean getValue() {
        return ForgeProject.booleans.get(variableName);
    }

    public void setValue(Boolean bool) {
        ForgeProject.booleans.put(variableName, bool);
    }
}
