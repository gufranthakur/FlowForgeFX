package com.flowforgefx.models.nodes;

import com.flowforgefx.controller.EditorController;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public abstract class FlowNode extends Group {

    protected EditorController controller;

    public ArrayList<FlowNode> inputNodes = new ArrayList<>();
    public ArrayList<FlowNode> outputNodes = new ArrayList<>();
    public ArrayList<FlowNode> inputXNodes = new ArrayList<>();
    public ArrayList<FlowNode> outputXNodes = new ArrayList<>();

    public RadioButton inputButton, outputButton, inputXButton, outputXButton;

    protected Rectangle background;
    protected Label titleLabel;

    protected boolean isDragging = false;
    protected double dragOffsetX, dragOffsetY;
    protected final int width = 180;
    protected final int height = 140;

    protected final int variableWidth = 180;
    protected final int variableHeight = 80;

    protected final int componentX = 10;
    protected final int componentY = 35;
    protected final int componentWidth = 160;
    protected final int componentHeight = 30;

    public boolean isBeingConnected = false;
    public boolean isBeingXConnected = false;
    public boolean isMinimized = false;
    public boolean isHighlighted = false;
    public boolean isCommented = false;
    public boolean isNodeDuringStepExecution;
    public String comment = "";

    public String nodeType;
    public String title;

    protected Color nodeTheme = Color.rgb(20, 20, 20);
    protected Color stepExecutionNodeTheme = Color.ORANGE;
    protected Color connectionColor = Color.WHITE;
    protected Color connectionXColor = Color.ORANGERED;

    public FlowNode(EditorController controller) {
        this.controller = controller;

        createNodeUI();
        createListeners();
        initDrag();

    }

    public void createNodeUI() {
        background = new Rectangle(width, height, nodeTheme);
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

    protected RadioButton createRadio(String text, double x, double y) {
        RadioButton button = new RadioButton(text);
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-font-size: 12px");
        button.setBackground(Background.EMPTY);
        button.setLayoutX(x);
        button.setLayoutY(y);
        return button;
    }

    private void createListeners() {
        inputButton.setOnMouseClicked(e -> {
            controller.selectedNode = this;
            for (FlowNode node : controller.nodes) {
                node.isBeingConnected = false;
                node.inputXButton.setDisable(false);
                node.outputXButton.setDisable(false);
            }
            controller.finishConnection(this);
            controller.getEditorView().render();
        });

        outputButton.setOnMouseClicked(e -> {
            isBeingConnected = true;
            isBeingXConnected = false;
            controller.selectedNode = this;
            for (FlowNode node : controller.nodes) {
                node.inputXButton.setDisable(true);
                node.outputXButton.setDisable(true);
            }
            controller.startConnection(this);
            controller.getEditorView().render();
        });

        inputXButton.setOnMouseClicked(e -> {
            controller.selectedNode = this;
            for (FlowNode node : controller.nodes) {
                node.isBeingXConnected = false;
                node.inputButton.setDisable(false);
                node.outputButton.setDisable(false);
            }
            controller.finishXConnection(this);
            controller.getEditorView().render();
        });

        outputXButton.setOnMouseClicked(e -> {
            isBeingConnected = false;
            isBeingXConnected = true;
            controller.selectedNode = this;
            for (FlowNode node : controller.nodes) {
                node.inputButton.setDisable(true);
                node.outputButton.setDisable(true);
            }
            controller.startXConnection(this);
            controller.getEditorView().render();
        });
    }

    private void initDrag() {
        setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                isDragging = true;
                dragOffsetX = e.getSceneX() - getLayoutX();
                dragOffsetY = e.getSceneY() - getLayoutY();
                setCursor(Cursor.MOVE);

                controller.getEditorView().render();
            }
        });

        setOnMouseReleased(e -> {
            isDragging = false;
            setCursor(Cursor.DEFAULT);
        });

        setOnMouseDragged(e -> {
            if (isDragging) {
                double newX = e.getSceneX() - dragOffsetX;
                double newY = e.getSceneY() - dragOffsetY;
                relocate(newX, newY);
                controller.getEditorView().render();
            }
        });
    }

    public void connectTo(FlowNode target) {
        outputNodes.add(target);
        target.inputNodes.add(this);
    }

    public void connectToX(FlowNode target) {
        outputXNodes.add(target);
        target.inputXNodes.add(this);
    }

    public void disconnectAll() {
        inputNodes.forEach(n -> n.outputNodes.remove(this));
        outputNodes.forEach(n -> n.inputNodes.remove(this));
        inputXNodes.forEach(n -> n.outputXNodes.remove(this));
        outputXNodes.forEach(n -> n.inputXNodes.remove(this));

        inputNodes.clear();
        outputNodes.clear();
        inputXNodes.clear();
        outputXNodes.clear();
    }

    public void setTitle(String s) {
        this.title = s;
        titleLabel.setText(s);
    }

    public void drawConnection(GraphicsContext graphics) {
        for (FlowNode output : outputNodes) {
            Point2D start = getOutputPoint();
            Point2D end = output.getInputPoint();
            drawCurvedLine(graphics, start, end, connectionColor);
        }
    }

    public void drawXConnection(GraphicsContext graphics) {
        for (FlowNode output : outputXNodes) {
            Point2D start = getOutputXPoint();
            Point2D end = output.getInputXPoint();
            drawCurvedLine(graphics, start, end, connectionXColor);
        }
    }

    private void drawCurvedLine(GraphicsContext graphics, Point2D start, Point2D end, Color color) {
        double dx = end.getX() - start.getX();
        boolean isBackward = dx < 0;
        double offsetX = isBackward ? Math.abs(dx) / 2 + 100 : Math.abs(dx) / 3;

        graphics.setStroke(color);
        graphics.setLineWidth(2.0);
        graphics.beginPath();
        graphics.moveTo(start.getX(), start.getY());
        graphics.bezierCurveTo(
                start.getX() + offsetX, start.getY(),
                end.getX() - offsetX, end.getY(),
                end.getX(), end.getY()
        );
        graphics.stroke();
    }

    public Point2D getInputPoint() {
        if (isMinimized) return new Point2D(getLayoutX(), getLayoutY() + 10);
        return new Point2D(getLayoutX(), getLayoutY() + 95);
    }

    public Point2D getOutputPoint() {
        if (isMinimized) return new Point2D(getLayoutX() + width, getLayoutY() + 10);
        return new Point2D(getLayoutX() + width, getLayoutY() + 95);
    }

    public Point2D getInputXPoint() {
        if (isMinimized) return new Point2D(getLayoutX(), getLayoutY() + 25);
        return new Point2D(getLayoutX(), getLayoutY() + 125);
    }

    public Point2D getOutputXPoint() {
        if (isMinimized) return new Point2D(getLayoutX() + 210, getLayoutY() + 25);
        return new Point2D(getLayoutX() + width, getLayoutY() + 125);
    }

    public abstract void execute(boolean isStepExecution);

}
