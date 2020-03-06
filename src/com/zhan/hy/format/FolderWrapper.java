package com.zhan.hy.format;

import com.zhan.hy.bean.FileInfo;

/**
 * AUTHOR：  HyZhan
 * create：  2019/7/6
 * desc：    TODO
 */
public class FolderWrapper implements Wrapper {

    @Override
    public String wrap(String content, FileInfo fileInfo) {
        return content.replace("$dir", fileInfo.getCustomPath());
    }
}
