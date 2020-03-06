package com.zhan.hy.bean;

/**
 * @author: hyzhan
 * @date: 2019/7/29
 * @desc: TODO
 */
public class InputInfo {

    // 模块名
    private String moduleName;

    // 是否要生成 contract类
    private boolean isSelectContract;

    // 是否要生成 activity类
    private boolean isSelectActivity;

    // 是否要生成 activity类
    private boolean isSelectFragment;

    // 是否要生成 presenter类
    private boolean isSelectPresenter;

    // 是否要生成 model类
    private boolean isSelectModel;

    // 是否要生成 layout 文件
    private boolean isSelectLayout;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public boolean isSelectContract() {
        return isSelectContract;
    }

    public void setSelectContract(boolean selectContract) {
        isSelectContract = selectContract;
    }

    public boolean isSelectActivity() {
        return isSelectActivity;
    }

    public void setSelectActivity(boolean selectActivity) {
        isSelectActivity = selectActivity;
    }

    public boolean isSelectFragment() {
        return isSelectFragment;
    }

    public void setSelectFragment(boolean selectFragment) {
        isSelectFragment = selectFragment;
    }

    public boolean isSelectPresenter() {
        return isSelectPresenter;
    }

    public void setSelectPresenter(boolean selectPresenter) {
        isSelectPresenter = selectPresenter;
    }

    public boolean isSelectModel() {
        return isSelectModel;
    }

    public void setSelectModel(boolean selectModel) {
        isSelectModel = selectModel;
    }

    public boolean isSelectLayout() {
        return isSelectLayout;
    }

    public void setSelectLayout(boolean selectLayout) {
        isSelectLayout = selectLayout;
    }
}
