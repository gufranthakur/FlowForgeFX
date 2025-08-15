package com.flowforgefx.controller;

import com.flowforgefx.FlowForge;
import com.flowforgefx.core.Console;
import com.flowforgefx.models.nodes.FlowNode;
import com.flowforgefx.models.nodes.StartNode;
import com.flowforgefx.views.EditorView;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class EditorController {

    private FlowForge flowForge;
    private EditorView view;

    public ArrayList<FlowNode> nodes;
    private FlowNode sourceNode;

    public FlowNode selectedNode;
    public FlowNode currentNodeAtExecution;

    public StartNode startNode;
    public HashMap<String, Integer> integerHashMap;
    public HashMap<String, String> stringStringHashMap;
    public HashMap<String, Boolean> booleanHashMap;
    public HashMap<String, Float> floatHashMap;

    public EditorController(FlowForge flowForge) {
        this.flowForge = flowForge;

        nodes = new ArrayList<>();
        startNode = new StartNode(this);

        integerHashMap = new HashMap<>(5);
        stringStringHashMap = new HashMap<>(5);
        booleanHashMap = new HashMap<>(5);
        floatHashMap = new HashMap<>(5);

    }

    public void addNode(FlowNode node) {
        try {
            int lastNodeX = (int) nodes.getLast().getLayoutX();
            node.relocate(lastNodeX + 230, 300);
            nodes.add(node);
            getEditorView().getChildren().add(node);

        } catch (Exception e) {
            node.relocate(300, 300);
            nodes.add(node);
            getEditorView().getChildren().add(node);
        }

    }

    public void addVariable(String name, String type) {
        switch (type) {
            case "int" : integerHashMap.put(name, 0); break;
            case "string" : stringStringHashMap.put(name, ""); break;
            case "boolean" : booleanHashMap.put(name, false); break;
            case "float" : floatHashMap.put(name, 0.0f); break;
        }
    }

    public void startConnection(FlowNode node) {
        this.sourceNode = node;
    }

    public void finishConnection(FlowNode targetNode) {
        if (sourceNode != null && sourceNode != targetNode) {
            sourceNode.connectTo(targetNode);
            sourceNode.outputButton.setSelected(true);
            targetNode.inputButton.setSelected(true);
            System.out.println("node : " + sourceNode + " connected to " + targetNode);

        }
    }

    public void startXConnection(FlowNode node) {
        this.sourceNode = node;
    }

    public void finishXConnection(FlowNode targetNode) {
        if (sourceNode != null && sourceNode != targetNode) {
            sourceNode.connectToX(targetNode);

            sourceNode.outputXButton.setSelected(true);
            targetNode.inputXButton.setSelected(true);

            selectedNode.isBeingXConnected = false;
            sourceNode = null;
        }
    }

    //-------------------------Getters and setters---------------------------//

    public void setEditorView(EditorView view) {
        this.view = view;
    }

    public EditorView getEditorView() {
        return view;
    }

    public FlowForge getFlowForge() {
        return flowForge;
    }

    public Task<?> getExecutor() {
        return flowForge.forgeExecutor.executor;
    }

    public Console getConsole() {
        return flowForge.console;
    }

}
