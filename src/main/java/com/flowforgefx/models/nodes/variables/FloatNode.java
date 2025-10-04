package com.flowforgefx.models.nodes.variables;

import com.flowforgefx.controller.EditorController;
import com.flowforgefx.models.nodes.FlowNode;
import com.flowforgefx.models.nodes.InputNode;
import com.flowforgefx.models.project.ForgeProject;
import com.flowforgefx.utils.DialogUtility;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class FloatNode extends FlowNode {

    private EditorController controller;
    private TextField valueField;
    private String variableName;

    public FloatNode(EditorController controller, String variableName) {
        super(controller, variableName);
        this.controller = controller;
        this.variableName = variableName;

        configUI();
    }


    @Override
    protected void configUI() {
        valueField = new TextField();
        valueField.setPromptText("Float Value...");

        this.placeComponent(valueField);
    }

    @Override
    public void execute(boolean isStepExecution) {
        for (FlowNode node : inputXNodes) {

            try {
                if (node instanceof InputNode inputNode) {
                    setValue(Float.valueOf(inputNode.input));
                } else {
                    setValue(Float.valueOf(valueField.getText()));
                }
            } catch (NumberFormatException e) {
                DialogUtility.showError("Non-integer value in Integer");
            }

        }

    }

    public Integer getValue() {
        return ForgeProject.integers.get(variableName);
    }

    public void setValue(Float floatValue) {
        ForgeProject.floats.put(variableName, floatValue);
    }
}
