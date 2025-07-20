package com.flowforgefx.controller;

import com.flowforgefx.FlowForge;

public class StartController {

    private FlowForge flowForge;

    public StartController(FlowForge flowForge) {
        this.flowForge = flowForge;
    }

    public void switchToStartPane() {

    }

    public void switchToProgramPane() {
        flowForge.root.getChildren().remove(flowForge.startView);

        flowForge.editorPane.getChildren().add(flowForge.editorView);

        flowForge.root.setCenter(flowForge.editorPane);
        flowForge.root.setLeft(flowForge.sidebarView);
    }
}
