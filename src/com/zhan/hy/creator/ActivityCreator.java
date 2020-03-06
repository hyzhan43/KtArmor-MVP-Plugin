package com.zhan.hy.creator;

import com.zhan.hy.FileUtils;
import com.zhan.hy.bean.FileInfo;

/**
 * AUTHOR：  HyZhan
 * create：  2019/7/5
 * desc：    TODO
 */
public class ActivityCreator extends BaseCreator implements Creator {

    @Override
    public void mkdirFile(FileInfo fileInfo) {
        String fileName = "ActivityTemplate.txt";
        String content = FileUtils.readTemplateFile(templatePath + fileName);
        content = wrapperFactory.wrap(content, fileInfo);

        String className = fileInfo.getModuleName() + "Activity.kt";
        FileUtils.writeToFile(content, fileInfo.getSaveLocation(), className);
    }
}
