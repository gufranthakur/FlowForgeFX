package com.flowforgefx.models.nodes;


import com.flowforgefx.controller.EditorController;
import com.flowforgefx.models.nodes.variables.BooleanNode;
import com.flowforgefx.models.nodes.variables.FloatNode;
import com.flowforgefx.models.nodes.variables.IntegerNode;
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
            switch (node) {
                case InputNode inputNode -> outputStringBuilder.append(inputNode.input);

                case StringNode stringNode -> outputStringBuilder.append(stringNode.getValue());
                case IntegerNode integerNode -> outputStringBuilder.append(integerNode.getValue());
                case BooleanNode booleanNode -> outputStringBuilder.append(booleanNode.getValue().toString());
                case FloatNode floatNode -> outputStringBuilder.append(floatNode.getValue().toString());

                default -> System.out.println("Error at print node line 50 ");
            }
        }

        String outputString = outputStringBuilder.toString();
        Platform.runLater(() -> print(outputString));
    }
}
