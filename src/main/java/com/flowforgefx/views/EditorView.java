package com.flowforgefx.views;

import com.flowforgefx.controller.EditorController;
import com.flowforgefx.models.FlowNode;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class EditorView extends Group {

    private EditorController controller;
    private Canvas canvas;
    private AnimationTimer animationTimer;

    public static boolean needsRedraw = true;

    public EditorView(EditorController controller) {
        this.controller = controller;
        this.controller.setEditorView(this);

        createCanvas();
        createTimer();
    }

    private void createCanvas() {
        canvas = new Canvas(3000, 3000);

        this.getChildren().add(canvas);
    }

    private void createTimer() {

    }

    public void addNodeToEditor(FlowNode node) {
        node.relocate(200, 200);
        this.getChildren().add(node);

    }

    public void render() {
        GraphicsContext graphics = canvas.getGraphicsContext2D();

        graphics.clearRect(0, 0, 1500, 800);

        for (FlowNode node : controller.nodes) {
            node.drawConnection(graphics);
            node.drawXConnection(graphics);
        }


    }

}
