package com.flowforgefx.core;

import com.flowforgefx.FlowForge;
import javafx.application.Platform;
import javafx.concurrent.Task;

public class ForgeExecutor {

    private FlowForge flowForge;
    public Task<?> executor;

    public ForgeExecutor(FlowForge flowForge) {
        this.flowForge = flowForge;
    }

    public void executeProgram() {
        flowForge.console.clearConsole();
        flowForge.console.print("Program execution started", "SUCCESS");

        executor = new Task<>() {
            @Override
            protected Object call(){
                flowForge.editorController.startNode.execute(false);
                return null;
            }
        };
        new Thread(executor).start();

        executor.setOnSucceeded(e -> {
            flowForge.console.print("Program executed successfully", "SUCCESS");
        });

        executor.setOnFailed(e -> {
            flowForge.console.print("Program failed to execute", "FAIL");
            System.out.println(executor.getException());
        });
    }

}
