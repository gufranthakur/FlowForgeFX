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

        flowForge.root.setCenter(flowForge.editorView);
        flowForge.root.setLeft(flowForge.sidebarView);
    }
}
