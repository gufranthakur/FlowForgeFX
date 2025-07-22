package com.flowforgefx.controller;

import com.flowforgefx.FlowForge;
import com.flowforgefx.models.FlowNode;
import com.flowforgefx.models.nodes.StartNode;
import com.flowforgefx.views.EditorView;

import java.util.ArrayList;

public class EditorController {

    private FlowForge flowForge;
    private EditorView view;

    public ArrayList<FlowNode> nodes;
    private FlowNode sourceNode;
    public FlowNode selectedNode;

    public StartNode startNode;

    public EditorController(FlowForge flowForge) {
        this.flowForge = flowForge;

        nodes = new ArrayList<>();

        startNode = new StartNode("Start", this);

    }

    public void addNode(FlowNode node) {
        node.relocate(300, 300);
        nodes.add(node);
        getEditorView().getChildren().add(node);
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

}
