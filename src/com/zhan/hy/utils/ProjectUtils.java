package com.zhan.hy.utils;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;

/**
 * @author: hyzhan
 * @date: 2019/7/30
 * @desc: TODO
 */
public class ProjectUtils {

    public static Project getProject() {
        ProjectManager projectManager = ProjectManager.getInstance();
        if (projectManager.getOpenProjects().length != 0) {
            return projectManager.getOpenProjects()[0];
        } else {
            return projectManager.getDefaultProject();
        }
    }

    public static PropertiesComponent getProperties(){
        return PropertiesComponent.getInstance(getProject());
    }
}
