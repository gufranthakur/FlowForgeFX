package com.flowforgefx.views;

import com.flowforgefx.controller.SidebarController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.LinkedHashMap;

public class SidebarView extends VBox {

    private SidebarController controller;

    private HBox programControlBox;
    private Button runButton, runWithStepsButton, consoleButton;

    private TabPane tabPane;

    private Tab functionTab;
    public TreeView<String> functionsTree;
    private TreeItem<String> functionRoot;
        private TreeItem<String> arithmeticItem, comparatorsItem, logicGatesItem, utilityItems;
    private LinkedHashMap<String , TreeItem<String>> functionItems;

    private Tab variableTab;
    private TreeView<String> variablesTree;
    private TreeItem<String> variableRoot;

    public SidebarView(SidebarController controller) {
        this.controller = controller;
        this.setPrefWidth(300);
        this.setPadding(new Insets(5));
        this.setBackground(new Background(new BackgroundFill(Color.rgb(20, 20, 20), new CornerRadii(0), Insets.EMPTY)
        ));

        controller.setSidebarView(this);

        createProgramControlBox();
        createTabPane();
        createFunctionItems();
        createFunctionTab();
    }

    private void createProgramControlBox() {
        programControlBox = new HBox();
        programControlBox.setPadding(new Insets(5));
        programControlBox.setSpacing(5);
        programControlBox.setAlignment(Pos.CENTER);

        runButton = new Button("Run");
        runButton.setStyle(
                "-fx-background-color: #1f68f0;"  // Darker blue gradient
        );

        runWithStepsButton = new Button("Run in Steps");
        runWithStepsButton.setStyle(
                "-fx-background-color: #fa9305;"  // Darker orange gradient
        );

        consoleButton = new Button("Console");

        programControlBox.getChildren().addAll(runButton, runWithStepsButton, consoleButton);
    }

    private void createTabPane() {
        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        //tabPane.setPadding(new Insets(5));

        variableTab = new Tab("Variables");

    }

    private void createFunctionItems() {
        functionItems = new LinkedHashMap<>();

        functionItems.put("print", new TreeItem<>("Print"));
        functionItems.put("branch", new TreeItem<>("Branch"));
        functionItems.put("input", new TreeItem<>("Input"));
        functionItems.put("delay", new TreeItem<>("Delay"));
        functionItems.put("loop", new TreeItem<>("Loop"));
        functionItems.put("conditionalLoop", new TreeItem<>("Conditional Loop"));

        functionItems.put("equalTo", new TreeItem<>("Equal To"));
        functionItems.put("greaterThan", new TreeItem<>("Greater Than"));
        functionItems.put("lessThan", new TreeItem<>("Less Than"));
        functionItems.put("greaterThanEqualTo", new TreeItem<>("Greater Than or Equal To"));
        functionItems.put("lessThanEqualTo", new TreeItem<>("Less Than or Equal To"));
        functionItems.put("notEqualTo", new TreeItem<>("Not Equal To"));

        functionItems.put("notGate", new TreeItem<>("NOT Gate"));
        functionItems.put("andGate", new TreeItem<>("AND Gate"));
        functionItems.put("orGate", new TreeItem<>("OR Gate"));
        functionItems.put("nandGate", new TreeItem<>("NAND Gate"));
        functionItems.put("norGate", new TreeItem<>("NOR Gate"));
        functionItems.put("xorGate", new TreeItem<>("XOR Gate"));

        functionItems.put("add", new TreeItem<>("Add"));
        functionItems.put("subtract", new TreeItem<>("Subtract"));
        functionItems.put("multiply", new TreeItem<>("Multiply"));
        functionItems.put("divide", new TreeItem<>("Divide"));
        functionItems.put("modulus", new TreeItem<>("Modulus"));
        functionItems.put("random", new TreeItem<>("Random"));
        functionItems.put("eval", new TreeItem<>("Eval"));

        functionItems.put("route", new TreeItem<>("Route"));
        functionItems.put("recursive", new TreeItem<>("Recursive"));

        //Do it here
        arithmeticItem = new TreeItem<>("Arithmetic");
        arithmeticItem.getChildren().addAll(
                functionItems.get("add"),
                functionItems.get("subtract"),
                functionItems.get("multiply"),
                functionItems.get("divide"),
                functionItems.get("modulus"),
                functionItems.get("random"),
                functionItems.get("eval")
        );
        comparatorsItem = new TreeItem<>("Comparators");
        comparatorsItem.getChildren().addAll(
                functionItems.get("equalTo"),
                functionItems.get("greaterThan"),
                functionItems.get("lessThan"),
                functionItems.get("greaterThanEqualTo"),
                functionItems.get("lessThanEqualTo"),
                functionItems.get("notEqualTo")
        );
        logicGatesItem = new TreeItem<>("Logic Gates");
        logicGatesItem.getChildren().addAll(
                functionItems.get("notGate"),
                functionItems.get("andGate"),
                functionItems.get("orGate"),
                functionItems.get("nandGate"),
                functionItems.get("norGate"),
                functionItems.get("xorGate")
        );
        utilityItems = new TreeItem<>("Utilities");
        utilityItems.getChildren().addAll(
                functionItems.get("route"),
                functionItems.get("recursive")
        );
    }

    private void createFunctionTab() {
        functionTab = new Tab("Functions");
        functionRoot = new TreeItem<>("Functions");

        functionRoot.getChildren().add(functionItems.get("print"));
        functionRoot.getChildren().add(functionItems.get("branch"));
        functionRoot.getChildren().add(functionItems.get("input"));
        functionRoot.getChildren().add(functionItems.get("delay"));
        functionRoot.getChildren().add(functionItems.get("loop"));
        functionRoot.getChildren().add(functionItems.get("conditionalLoop"));

        functionRoot.getChildren().add(arithmeticItem);
        functionRoot.getChildren().add(comparatorsItem);
        functionRoot.getChildren().add(logicGatesItem);
        functionRoot.getChildren().add(utilityItems);

        functionsTree = new TreeView<>(functionRoot);
        functionsTree.setFixedCellSize(32);
        functionsTree.setOnMouseClicked(controller::onTreeItemSelection);

        functionTab.setContent(functionsTree);
    }

    public void addComponents() {
        VBox.setVgrow(tabPane, Priority.ALWAYS);

        tabPane.getTabs().add(functionTab);
        tabPane.getTabs().add(variableTab);

        this.getChildren().add(programControlBox);
        this.getChildren().add(tabPane);
    }

}
