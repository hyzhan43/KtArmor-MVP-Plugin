package com.zhan.hy.format;

import com.intellij.ide.util.PropertiesComponent;
import com.zhan.hy.FileUtils;
import com.zhan.hy.bean.FileInfo;
import com.zhan.hy.constant.MvpConst;
import com.zhan.hy.utils.ProjectUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author: hyzhan
 * @date: 2019/7/30
 * @desc: TODO
 */
public class TemplateWrapper implements Wrapper {

    @Override
    public String wrap(String content, FileInfo fileInfo) {
        PropertiesComponent state = ProjectUtils.getProperties();
        String template = state.getValue(MvpConst.TEMPLATE);
        if (StringUtils.isEmpty(template)) {
            template = FileUtils.readTemplateFile("template/FileHeaderTemplate.txt");
        }
        return content.replace("$headerTemplate", template);
    }
}
