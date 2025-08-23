package com.flowforgefx.controller;

import com.flowforgefx.FlowForge;
import com.flowforgefx.controller.menus.VariableMenu;
import com.flowforgefx.models.nodes.DelayNode;
import com.flowforgefx.models.nodes.InputNode;
import com.flowforgefx.models.nodes.LoopNode;
import com.flowforgefx.models.nodes.PrintNode;
import com.flowforgefx.views.SidebarView;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.Map;
import java.util.Optional;


import static com.flowforgefx.models.project.ForgeProject.booleans;
import static com.flowforgefx.models.project.ForgeProject.floats;
import static com.flowforgefx.models.project.ForgeProject.integers;
import static com.flowforgefx.models.project.ForgeProject.strings;

public class SidebarController {

    private FlowForge flowForge;
    private SidebarView sidebarView;

    private VariableMenu variableMenu;

    public TreeItem<String> selectedItem;

    public SidebarController(FlowForge flowForge) {
        this.flowForge = flowForge;

        variableMenu = new VariableMenu(this);
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
        selectedItem = sidebarView.variablesTree.getSelectionModel().getSelectedItem();

        if (event.getButton() == MouseButton.SECONDARY) {
            variableMenu.display(sidebarView.variablesTree, event.getScreenX(), event.getScreenY());
        } else {
            if (selectedItem == null) return;
            getSelectedVariableAtTree(selectedItem.getValue());
        }
    }

    private void getSelectedVariableAtTree(String itemValue) {
        var controller = flowForge.editorController;


    }

    public void addVariable() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter variable name");
        dialog.setHeaderText(null);
        dialog.setContentText("Name:");

        Optional<String> input = dialog.showAndWait();
        String name = input.get();

        if (isVariableNameUsed(name)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Variable name is already in use");
            alert.showAndWait();
            return;
        }

        if (name.isBlank()) return;

        switch (selectedItem.getValue()) {
            case "Integers" : {
                integers.put(name, 0);
                sidebarView.intTreeItem.getChildren().add(new TreeItem<>(name));
            } break;
            case "Strings" : {
                strings.put(name, "");
                sidebarView.stringTreeItem.getChildren().add(new TreeItem<>(name));
            } break;
            case "Booleans" : {
                booleans.put(name, false);
                sidebarView.booleanTreeItem.getChildren().add(new TreeItem<>(name));
            } break;
            case "floats" : {
                floats.put(name, 0.0f);
                sidebarView.floatTreeItem.getChildren().add(new TreeItem<>(name));
            } break;
            default: break;
        }

    }

    public boolean isVariableNameUsed(String variableName) {

        for (String key : strings.keySet()) {
            if (variableName.equals(key)) return true;
        }

        for (String key : integers.keySet()) {
            if (variableName.equals(key)) return true;
        }

        for (String key : booleans.keySet()) {
            if (variableName.equals(key)) return true;
        }

        for (String key : floats.keySet()) {
            if (variableName.equals(key)) return true;
        }

        return false;
    }


    public void setSidebarView(SidebarView view) {
        this.sidebarView = view;
    }


    public FlowForge getFlowForge() {
        return this.flowForge;
    }

}
