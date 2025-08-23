package com.flowforgefx.controller.menus;

import com.flowforgefx.controller.SidebarController;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.util.Optional;

import static com.flowforgefx.FlowForge.*;

public class VariableMenu extends ContextMenu {

    private SidebarController controller;
    private MenuItem addVariableItem, renameVariableItem, deleteVariableItem;

    public VariableMenu(SidebarController controller) {
        this.controller = controller;

        addVariableItem = new MenuItem("Add");
        addVariableItem.setOnAction(e -> controller.addVariable());

        renameVariableItem = new MenuItem("Rename");
//        renameVariableItem.setOnAction(e -> renameVariable());

        deleteVariableItem = new MenuItem("Delete");
//        deleteVariableItem.setOnAction(e -> deleteVariable());

        this.getItems().addAll(addVariableItem, renameVariableItem, deleteVariableItem);
    }

    public void display(Node variablesTree, double x, double y) {
        this.show(variablesTree, x, y);

        switch (controller.selectedItem.getValue()) {
            case "Integers" , "Strings", "Booleans", "Floats" : {
                renameVariableItem.setDisable(true);
                deleteVariableItem.setDisable(true);
                addVariableItem.setDisable(false);
            } break;

            case "Variables" : {
                renameVariableItem.setDisable(true);
                deleteVariableItem.setDisable(true);
                addVariableItem.setDisable(true);
            } break;

            default: {
                addVariableItem.setDisable(false);
                deleteVariableItem.setDisable(false);
                renameVariableItem.setDisable(false);
            }
        }
    }

}
