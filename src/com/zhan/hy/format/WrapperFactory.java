package com.zhan.hy.format;

import com.zhan.hy.bean.FileInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * AUTHOR：  HyZhan
 * create：  2019/7/6
 * desc：    TODO
 */
public class WrapperFactory {

    private List<Wrapper> wrappers = new ArrayList<>();

    public WrapperFactory() {
        wrappers.add(new TemplateWrapper());
        wrappers.add(new DateWrapper());
        wrappers.add(new FolderWrapper());
        wrappers.add(new ModuleNameWrapper());
        wrappers.add(new PackageNameWrapper());
        wrappers.add(new BasePackageName());
    }

    public String wrap(String content, FileInfo fileInfo) {
        String newContent = content;
        for (Wrapper wrapper : wrappers) {
            newContent = wrapper.wrap(newContent, fileInfo);
        }

        return newContent;
    }
}
