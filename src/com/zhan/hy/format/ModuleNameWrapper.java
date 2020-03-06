package com.zhan.hy.format;

import com.zhan.hy.bean.FileInfo;

/**
 * AUTHOR：  HyZhan
 * create：  2019/7/6
 * desc：    TODO
 */
public class ModuleNameWrapper implements Wrapper {

    @Override
    public String wrap(String content, FileInfo fileInfo) {
        String template = content.replace("$name", fileInfo.getModuleName());
        return template.replace("$lowerModule", fileInfo.getModuleName().toLowerCase());
    }
}
