package com.flowforgefx.controller;

import com.flowforgefx.FlowForge;
import com.flowforgefx.models.nodes.DelayNode;
import com.flowforgefx.models.nodes.InputNode;
import com.flowforgefx.models.nodes.LoopNode;
import com.flowforgefx.models.nodes.PrintNode;
import com.flowforgefx.views.SidebarView;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class SidebarController {

    private FlowForge flowForge;
    private SidebarView sidebarView;

    public SidebarController(FlowForge flowForge) {
        this.flowForge = flowForge;



    }

    public void onTreeItemSelection(MouseEvent event) {
        var selectedItem = sidebarView.functionsTree.getSelectionModel().getSelectedItem();
        if (event.getButton() == MouseButton.SECONDARY) {
            System.out.println("popup, yay");
        } else {
            if (selectedItem == null) return;
            getSelectedNodeAtTree(selectedItem.getValue());
        }
    }

    public void getSelectedNodeAtTree(String itemValue) {
        var controller = flowForge.editorController;
        switch (itemValue) {
            case "Print" : controller.addNode(new PrintNode(controller)); break;
            case "Input" : controller.addNode(new InputNode(controller)); break;
            case "Delay" : controller.addNode(new DelayNode(controller)); break;
            case "Loop" : controller.addNode(new LoopNode(controller)); break;
        }
    }

    public void onVariableTreeItemSelection(MouseEvent event) {
        var selectedItem = sidebarView.variablesTree.getSelectionModel().getSelectedItem();

        if (event.getButton() == MouseButton.SECONDARY) {
            //variableMenu.show(sidebarView.variablesTree, event.getScreenX(), event.getScreenY());
        } else {
            if (selectedItem == null) return;
            getSelectedVariableAtTree(selectedItem.getValue());
        }
    }

    private void getSelectedVariableAtTree(String itemValue) {
        var controller = flowForge.editorController;


    }

    private void addVariable(ActionEvent e) {

    }

    private void deleteVariable(ActionEvent e) {

    }

    private void renameVariable(ActionEvent e) {

    }

    public void setSidebarView(SidebarView view) {
        this.sidebarView = view;
    }

    public FlowForge getFlowForge() {
        return this.flowForge;
    }

}
