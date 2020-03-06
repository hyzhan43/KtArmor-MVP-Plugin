package com.zhan.hy.format;

import com.zhan.hy.bean.FileInfo;

/**
 * AUTHOR：  HyZhan
 * create：  2019/7/6
 * desc：    TODO
 */
public class PackageNameWrapper implements Wrapper {

    @Override
    public String wrap(String content, FileInfo fileInfo) {
        String packageName = fileInfo.getPackageName() + fileInfo.getCustomPath();
        return content.replace("$packageName", packageName);
    }
}
