package com.flowforgefx.models.nodes.variables;

import com.flowforgefx.controller.EditorController;
import com.flowforgefx.models.nodes.FlowNode;
import com.flowforgefx.models.nodes.InputNode;
import com.flowforgefx.models.project.ForgeProject;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class StringNode extends FlowNode {

    private final String variableName;
    private String value;

    private TextField stringField;

    public StringNode(EditorController controller, String variableName) {
        super(controller, variableName);
        this.variableName = variableName;

        configUI();
    }
    @Override
    protected void configUI() {
        inputButton.setVisible(false);
        outputButton.setVisible(false);

        stringField = new TextField();
        stringField.setPromptText("String value...");

        placeComponent(stringField);
    }

    @Override
    public void execute(boolean isStepExecution) {

        for (FlowNode node : inputXNodes) {
            if (node instanceof InputNode inputNode) {
                setValue(inputNode.input);
            } else {
                setValue(stringField.getText());
            }
        }

    }

    public String getValue() {
        return ForgeProject.strings.get(variableName);
    }

    public void setValue(String value) {
        ForgeProject.strings.put(variableName, value);
    }

}
