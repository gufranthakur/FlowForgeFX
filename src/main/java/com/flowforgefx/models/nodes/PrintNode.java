package com.flowforgefx.models.nodes;


import com.flowforgefx.controller.EditorController;
import com.flowforgefx.models.nodes.variables.StringNode;
import javafx.application.Platform;
import javafx.scene.control.TextField;

public class PrintNode extends FlowNode {

    public TextField textField;

    public PrintNode(EditorController controller) {
        super(controller);
        setTitle("Print");

        configUI();
    }

    @Override
    protected void configUI() {
        outputXButton.setVisible(false);

        textField = new TextField();
        placeComponent(textField);
    }


    public void print(String text) {
        controller.getFlowForge().console.print(text, "normal");
    }

    @Override
    public void execute(boolean isStepExecution) {
        controller.currentNodeAtExecution = this;

        StringBuilder outputStringBuilder = new StringBuilder();
        outputStringBuilder.append(textField.getText());

        for (FlowNode nodes : outputNodes) {
            if (nodes != null) nodes.execute(false);
        }

        for (FlowNode node : inputXNodes) {
            if (node instanceof InputNode inputNode) {
                outputStringBuilder.append(inputNode.input);
            } else if (node instanceof StringNode stringNode) {
                outputStringBuilder.append(stringNode.getValue());
            }
        }

        String outputString = outputStringBuilder.toString();
        Platform.runLater(() -> print(outputString));
    }
}
