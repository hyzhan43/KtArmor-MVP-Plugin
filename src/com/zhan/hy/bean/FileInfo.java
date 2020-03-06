package com.zhan.hy.bean;

/**
 * AUTHOR：  HyZhan
 * create：  2019/7/6
 * desc：    TODO
 */
public class FileInfo {

    // 模块名
    private String moduleName;

    // xml 布局名
    private String layoutName;

    // 包名
    private String packageName;

    // 项目根路径
    private String basePath;

    // basePath + app/src/main/java + packageName
    private String appPath;

    private String saveLocation;

    private String customPath;

    private Boolean isActivity;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getLayoutName() {
        return layoutName;
    }

    public void setLayoutName(String layoutName) {
        this.layoutName = layoutName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    public String getSaveLocation() {
        return saveLocation;
    }

    public void setSaveLocation(String saveLocation) {
        this.saveLocation = saveLocation;
    }

    public String getCustomPath() {
        return customPath;
    }

    public void setCustomPath(String customPath) {
        this.customPath = customPath;
    }

    public Boolean isActivity() {
        return isActivity;
    }

    public void setActivity(Boolean isActivity) {
        this.isActivity = isActivity;
    }
}
