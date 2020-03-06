package com.zhan.hy.format;

import com.zhan.hy.utils.TimeUtils;
import com.zhan.hy.bean.FileInfo;

/**
 * author：  HyZhan
 * create：  2019/7/6
 * desc：    TODO
 */
public class DateWrapper implements Wrapper {

    @Override
    public String wrap(String content, FileInfo fileInfo) {
        return content.replace("$date", TimeUtils.getDate());
    }
}
