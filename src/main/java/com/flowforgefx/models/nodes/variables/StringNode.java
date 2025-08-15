package com.flowforgefx.models.nodes.variables;

import com.flowforgefx.controller.EditorController;
import com.flowforgefx.models.nodes.FlowNode;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class StringNode extends FlowNode {

    private String value;

    public StringNode(EditorController controller) {
        super(controller);

    }

    @Override
    public void createNodeUI() {
        background = new Rectangle(variableWidth, variableWidth, nodeTheme);
        background.setArcWidth(10);
        background.setArcHeight(10);
        background.setStroke(Color.GRAY);
        background.setStrokeWidth(1);

        titleLabel = new Label("Node");
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setLayoutX(10);
        titleLabel.setLayoutY(10);
        titleLabel.setStyle("-fx-font-size: 12px");

        inputButton = createRadio("Input", 10, 80);
        inputXButton = createRadio("InputX", 10, 110);
        outputButton = createRadio("Output", 100, 80);
        outputXButton = createRadio("OutputX", 100, 110);

        getChildren().addAll(background, titleLabel, inputButton, inputXButton, outputButton, outputXButton);
    }

    @Override
    public void execute(boolean isStepExecution) {

    }

}
