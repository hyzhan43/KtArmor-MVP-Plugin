package com.zhan.hy.creator;

import com.zhan.hy.FileUtils;
import com.zhan.hy.bean.FileInfo;

/**
 * AUTHOR：  HyZhan
 * create：  2019/7/25
 * desc：    生成layout文件
 */
public class LayoutCreator extends BaseCreator implements Creator {

    @Override
    public void mkdirFile(FileInfo fileInfo) {

        String fileName = "LayoutTemplate.txt";
        String content = FileUtils.readTemplateFile(templatePath + fileName);
        content = wrapperFactory.wrap(content, fileInfo);

        String layoutPath = fileInfo.getBasePath() + "/app/src/main/res/layout";
        String module = fileInfo.getModuleName().toLowerCase();

        String layoutName = isActivity(fileInfo) + module + ".xml";

        FileUtils.writeToFile(content, layoutPath, layoutName);
    }

    private String isActivity(FileInfo fileInfo) {
        if (fileInfo.isActivity()) {
            return "activity_";
        } else {
            return "fragment_";
        }
    }
}
