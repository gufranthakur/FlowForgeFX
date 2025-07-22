package com.flowforgefx.models.nodes;

//import com.flowforgefx.nodes.Node;
//import com.flowforgefx.nodes.flownodes.arithmetic.*;
//import com.flowforgefx.nodes.flownodes.comparators.*;
//import com.flowforgefx.nodes.flownodes.logicgates.LogicGateNode;
//import com.flowforgefx.nodes.variables.*;
//import com.flowforgefx.view.EditorView;
import com.flowforgefx.controller.EditorController;
import com.flowforgefx.models.FlowNode;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PrintNode extends FlowNode {

    public TextField textField;
    private final EditorController controller;

    public PrintNode(String title, EditorController controller) {
        super(title, controller);
        this.controller = controller;

        textField = new TextField();
        textField.setPromptText("Print...");
        textField.setLayoutX(10);
        textField.setLayoutY(35);

        this.getChildren().add(textField);
    }

    public void print(String text) {
        controller.getFlowForge().console.print(text + "\n", "normal");
    }

    @Override
    public void execute(boolean isStepExecution) {

        print(textField.getText());

        for (FlowNode nodes : outputNodes) {
            if (nodes != null) nodes.execute(false);
        }
    }
}
