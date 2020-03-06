package com.zhan.hy.format;

import com.zhan.hy.bean.FileInfo;

/**
 * AUTHOR：  HyZhan
 * create：  2019/7/25
 * desc：    TODO
 */
public class BasePackageName implements Wrapper{
    @Override
    public String wrap(String content, FileInfo fileInfo) {
        String basePackageName = fileInfo.getPackageName();
        return content.replace("$basePackageName", basePackageName);
    }
}
