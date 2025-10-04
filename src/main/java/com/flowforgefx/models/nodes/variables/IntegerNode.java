package com.flowforgefx.models.nodes.variables;

import com.flowforgefx.controller.EditorController;
import com.flowforgefx.models.nodes.FlowNode;
import com.flowforgefx.models.nodes.InputNode;
import com.flowforgefx.models.project.ForgeProject;
import com.flowforgefx.utils.DialogUtility;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class IntegerNode extends FlowNode {

    private EditorController controller;
    private TextField valueField;
    private String variableName;

    public IntegerNode(EditorController controller, String variableName) {
        super(controller, variableName);
        this.controller = controller;
        this.variableName = variableName;

        configUI();
    }


    @Override
    protected void configUI() {
        inputButton.setVisible(false);
        outputButton.setVisible(false);

        valueField = new TextField();
        valueField.setPromptText("Integer Value...");

        this.placeComponent(valueField);
    }

    @Override
    public void execute(boolean isStepExecution) {
        for (FlowNode node : inputXNodes) {

            try {
                if (node instanceof InputNode inputNode) {
                    setValue(Integer.valueOf(inputNode.input));
                } else {
                    setValue(Integer.valueOf(valueField.getText()));
                }
            } catch (NumberFormatException e) {

                if (valueField.getText().isEmpty()) {
                    setValue(0);
                    return;
                }

                Platform.runLater(() -> controller.getConsole().print("Error : Non-Integer value in Integer", "ERR"));
            }

        }

    }

    public Integer getValue() {
        return ForgeProject.integers.get(variableName);
    }

    public void setValue(Integer integer) {
        ForgeProject.integers.put(variableName, integer);
    }
}
