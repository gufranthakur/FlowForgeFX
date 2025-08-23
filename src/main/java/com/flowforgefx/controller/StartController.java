package com.flowforgefx.controller;

import com.flowforgefx.FlowForge;
import com.flowforgefx.models.project.ForgeProject;

public class StartController {

    private FlowForge flowForge;

    public StartController(FlowForge flowForge) {
        this.flowForge = flowForge;
    }

    public void loadProject() {

    }

    public void newProject() {
        flowForge.project = new ForgeProject(flowForge);
        flowForge.project.createNewProjectData();

        flowForge.root.getChildren().remove(flowForge.startView);

        flowForge.editorPane.getChildren().add(flowForge.editorView);

        flowForge.splitPane.getItems().addAll(flowForge.editorPane, flowForge.console);

        flowForge.root.setCenter(flowForge.splitPane);
        flowForge.root.setLeft(flowForge.sidebarView);
    }
}
