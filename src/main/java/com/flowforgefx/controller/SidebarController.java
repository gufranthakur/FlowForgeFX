package com.flowforgefx.controller;

import com.flowforgefx.FlowForge;
import com.flowforgefx.models.nodes.PrintNode;
import com.flowforgefx.views.SidebarView;
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
            getSelectedNodeAtTree(selectedItem.getValue());
        }
    }

    public void getSelectedNodeAtTree(String itemValue) {
        var controller = flowForge.editorController;
        switch (itemValue) {
            case "Print" : controller.addNode(new PrintNode("Print", controller));
            case "Input" : //

        }
    }

    public void setSidebarView(SidebarView view) {
        this.sidebarView = view;
    }

}
