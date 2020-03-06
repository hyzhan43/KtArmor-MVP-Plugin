package com.zhan.hy.config;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.options.SearchableConfigurable;
import com.zhan.hy.FileUtils;
import com.zhan.hy.component.MvpSettingForm;
import com.zhan.hy.constant.MvpConst;
import com.zhan.hy.utils.ProjectUtils;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author: hyzhan
 * @date: 2019/7/29
 * @desc: 插件全局配置
 */
public class MvpConfig implements SearchableConfigurable {

    private PropertiesComponent state;

    private MvpSettingForm mvpSettingForm;

    @NotNull
    @Override
    public String getId() {
        return "KtArmor Config";
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return this.getId();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        state = ProjectUtils.getProperties();
        mvpSettingForm = new MvpSettingForm();

        String cacheTemplate = state.getValue(MvpConst.TEMPLATE);
        if (StringUtils.isEmpty(cacheTemplate)) {
            cacheTemplate = FileUtils.readTemplateFile("template/FileHeaderTemplate.txt");
        }
        mvpSettingForm.templateArea.setText(cacheTemplate);
        return mvpSettingForm.panel;
    }

    /**
     * 当用户修改配置参数后，在点击“OK”“Apply”按钮前，框架会自动调用该方法，判断是否有修改，进而控制按钮“OK”“Apply”的是否可用
     */
    @Override
    public boolean isModified() {
        return !mvpSettingForm.templateArea.getText().equals(state.getValue(MvpConst.TEMPLATE));
    }

    /**
     * 用户点击“OK”或“Apply”按钮后会调用该方法，通常用于完成配置信息持久化。
     */
    @Override
    public void apply() {
        state.setValue(MvpConst.TEMPLATE, mvpSettingForm.templateArea.getText());
    }

    /**
     * 用户点击“Reset”按钮后，用户界面配置参数重置。
     */
    @Override
    public void reset() {
        state.setValue(MvpConst.TEMPLATE, "");
    }
}
