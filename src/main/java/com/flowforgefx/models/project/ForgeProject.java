package com.flowforgefx.models.project;

import com.flowforgefx.FlowForge;

import java.util.HashMap;

public class ForgeProject {

    private FlowForge flowForge;

    public static HashMap<String, String> strings;
    public static HashMap<String, Integer> integers;
    public static HashMap<String, Boolean> booleans;
    public static HashMap<String, Float> floats;

    public ForgeProject(FlowForge flowForge) {
        this.flowForge = flowForge;
    }

    public void loadProjectData() {

    }

    public void createNewProjectData() {
        strings = new HashMap<>();
        integers = new HashMap<>();
        booleans = new HashMap<>();
        floats = new HashMap<>();
    }

}
