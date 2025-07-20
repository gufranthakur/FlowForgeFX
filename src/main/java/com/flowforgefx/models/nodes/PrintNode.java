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

    }

    public void print(String text) {
        System.out.println(text);
    }

    @Override
    public void execute(boolean isStepExecution) {
        if (!inputXNodes.isEmpty()) {
            for (FlowNode inputXNode : inputXNodes) {
                switch (inputXNode) {
//                    case IntegerNode integerNode -> print(textField.getText() + integerNode.getValue());
//                    case StringNode stringNode -> print(textField.getText() + stringNode.getValue());
//                    case BooleanNode booleanNode -> print(textField.getText() + booleanNode.getValue());
//                    case FloatNode floatNode -> print(textField.getText() + floatNode.getValue());
//                    case EqualToNode equalToNode -> print(textField.getText() + equalToNode.getIsEqual());
//                    case GreaterThanNode greaterThanNode -> print(textField.getText() + greaterThanNode.getIsGreater());
//                    case LessThanNode lessThanNode -> print(textField.getText() + lessThanNode.getIsLess());
//                    case GreaterThanOrEqualNode greaterThanOrEqualNode -> print(textField.getText() + greaterThanOrEqualNode.getIsGreaterOrEqual());
//                    case LessThanOrEqualNode lessThanOrEqualNode -> print(textField.getText() + lessThanOrEqualNode.getIsLessOrEqual());
//                    case NotEqualToNode notEqualToNode -> print(textField.getText() + notEqualToNode.getIsNotEqual());
//                    case LogicGateNode logicGateNode -> print(textField.getText() + logicGateNode.getResult());
//                    case InputNode inputNode -> print(textField.getText() + inputNode.inputValue);
//                    case LoopNode loopNode -> print(textField.getText() + loopNode.getIterationValue());
//                    case AddNode addNode -> print(textField.getText() + addNode.getResult());
//                    case SubtractNode subtractNode -> print(textField.getText() + subtractNode.getResult());
//                    case MultiplyNode multiplyNode -> print(textField.getText() + multiplyNode.getResult());
//                    case DivideNode divideNode -> print(textField.getText() + divideNode.getResult());
//                    case ModulusNode modulusNode -> print(textField.getText() + modulusNode.getResult());
//                    case RandomNode randomNode -> print(textField.getText() + randomNode.getResult());
//                    case EvalNode evalNode -> print(textField.getText() + evalNode.getResult());
                    default -> print("ERROR");
                }
            }
        } else {
            print(textField.getText());
        }

        for (FlowNode nodes : outputNodes) {
            if (nodes != null) nodes.execute(false);
        }
    }
}
