package com.flowforgefx.controller;

import com.flowforgefx.FlowForge;
import com.flowforgefx.controller.menus.VariableMenu;
import com.flowforgefx.models.nodes.*;
import com.flowforgefx.models.nodes.variables.BooleanNode;
import com.flowforgefx.models.nodes.variables.FloatNode;
import com.flowforgefx.models.nodes.variables.IntegerNode;
import com.flowforgefx.models.nodes.variables.StringNode;
import com.flowforgefx.utils.DialogUtility;
import com.flowforgefx.views.SidebarView;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

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
            case "Print" -> controller.addNode(new PrintNode(controller));
            case "Input" -> controller.addNode(new InputNode(controller));
            case "Delay" -> controller.addNode(new DelayNode(controller));
            case "Loop" -> controller.addNode(new LoopNode(controller));
            case "Conditional Loop" -> controller.addNode(new ConditionalLoop(controller));
        }
    }

    public void onVariableTreeItemSelection(MouseEvent event) {
        selectedItem = sidebarView.variablesTree.getSelectionModel().getSelectedItem();

        if (event.getButton() == MouseButton.SECONDARY) {
            variableMenu.display(sidebarView.variablesTree, event.getScreenX(), event.getScreenY());
        } else {
            if (selectedItem == null) return;
            getSelectedVariableAtTree();
        }
    }

    private void getSelectedVariableAtTree() {
        var controller = flowForge.editorController;

        if (selectedItem.getParent() == null) return;
        String variableName = selectedItem.getValue();

        switch (selectedItem.getParent().getValue()) {
            case "Strings" -> controller.addNode(new StringNode(controller, variableName));
            case "Integers" -> controller.addNode(new IntegerNode(controller, variableName));
            case "Booleans" -> controller.addNode(new BooleanNode(controller, variableName));
            case "Floats" -> controller.addNode(new FloatNode(controller, variableName));
        }
    }

    public void addVariable() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter variable name");
        dialog.setHeaderText(null);
        dialog.setContentText("Name:");

        Optional<String> input = dialog.showAndWait();
        String name = input.get();

        if (isVariableNameUsed(name)) {
            DialogUtility.showError("Variable name already in use");
            return;
        }

        if (name.isBlank()) return;

        switch (selectedItem.getValue()) {
            case "Integers" -> {
                integers.put(name, 0);
                sidebarView.intTreeItem.getChildren().add(new TreeItem<>(name));
            }
            case "Strings" -> {
                strings.put(name, "");
                sidebarView.stringTreeItem.getChildren().add(new TreeItem<>(name));
            }
            case "Booleans" -> {
                booleans.put(name, false);
                sidebarView.booleanTreeItem.getChildren().add(new TreeItem<>(name));
            }
            case "Floats" -> {
                floats.put(name, 0.0f);
                sidebarView.floatTreeItem.getChildren().add(new TreeItem<>(name));
            }
            default -> { }
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
