package com.flowforgefx.views;

import com.flowforgefx.controller.EditorController;
import com.flowforgefx.models.nodes.FlowNode;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class EditorView extends Group {

    private EditorController controller;
    private Canvas canvas;

    private double viewportWidth = 800; // default values
    private double viewportHeight = 600;

    // Mouse dragging variables
    private boolean isDragging = false;
    private double lastMouseX, lastMouseY;

    public EditorView(EditorController controller) {
        this.controller = controller;
        this.controller.setEditorView(this);
        this.setFocusTraversable(true);

        createCanvas();
        setupMouseHandlers();

        controller.addNode(controller.startNode);

        sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                viewportWidth = newScene.getWidth();
                viewportHeight = newScene.getHeight();

                newScene.widthProperty().addListener((o, oldW, newW) -> viewportWidth = newW.doubleValue());
                newScene.heightProperty().addListener((o, oldH, newH) -> viewportHeight = newH.doubleValue());
            }
        });

    }

    private void createCanvas() {
        canvas = new Canvas(2048, 2048);
        this.getChildren().add(canvas);
    }

    private void setupMouseHandlers() {
        this.setOnMousePressed(this::handleMousePressed);
        this.setOnMouseDragged(this::handleMouseDragged);
        this.setOnMouseReleased(this::handleMouseReleased);
        render();
    }

    private void handleMousePressed(MouseEvent event) {
        if (event.isPrimaryButtonDown()) {
            if (isClickingOnNode(event)) return;

            isDragging = true;
            lastMouseX = event.getSceneX();
            lastMouseY = event.getSceneY();
            this.requestFocus();
            event.consume();
        }
    }

    private void handleMouseDragged(MouseEvent event) {
        if (isDragging) {
            double deltaX = event.getSceneX() - lastMouseX;
            double deltaY = event.getSceneY() - lastMouseY;

            setLayoutX(getLayoutX() + deltaX);
            setLayoutY(getLayoutY() + deltaY);

            lastMouseX = event.getSceneX();
            lastMouseY = event.getSceneY();

            render();
            event.consume();
        }
    }

    private boolean isClickingOnNode(MouseEvent event) {
        double localX = event.getX();
        double localY = event.getY();

        for (FlowNode node : controller.nodes) {
            if (node.getBoundsInParent().contains(localX, localY)) {
                return true;
            }
        }
        return false;
    }

    private void handleMouseReleased(MouseEvent event) {
        isDragging = false;
    }

    public void render() {
        GraphicsContext graphics = canvas.getGraphicsContext2D();

        double viewX = -getLayoutX();
        double viewY = -getLayoutY();

        graphics.clearRect(viewX, viewY, viewportWidth, viewportHeight);

        // Draw gridlines
        graphics.setStroke(Color.rgb(50, 50, 50));
        graphics.setLineWidth(1);

        int gridSize = 32;
        int startX = (int)(viewX / gridSize) * gridSize;
        for (int x = startX; x < viewX + viewportWidth; x += gridSize) {
            graphics.strokeLine(x, viewY, x, viewY + viewportHeight);
        }

        int startY = (int)(viewY / gridSize) * gridSize;
        for (int y = startY; y < viewY + viewportHeight; y += gridSize) {
            graphics.strokeLine(viewX, y, viewX + viewportWidth, y);
        }

        for (FlowNode node : controller.nodes) {
            node.drawConnection(graphics);
            node.drawXConnection(graphics);
        }

    }

}