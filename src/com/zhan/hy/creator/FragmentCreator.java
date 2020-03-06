package com.zhan.hy.creator;

import com.zhan.hy.FileUtils;
import com.zhan.hy.bean.FileInfo;

/**
 * @author: HyJame
 * @date: 2019/9/17
 * @desc: TODO
 */
public class FragmentCreator extends BaseCreator implements Creator {

    @Override
    public void mkdirFile(FileInfo fileInfo) {
        String fileName = "FragmentTemplate.txt";
        String content = FileUtils.readTemplateFile(templatePath + fileName);
        content = wrapperFactory.wrap(content, fileInfo);

        String className = fileInfo.getModuleName() + "Fragment.kt";
        FileUtils.writeToFile(content, fileInfo.getSaveLocation(), className);
    }
}
