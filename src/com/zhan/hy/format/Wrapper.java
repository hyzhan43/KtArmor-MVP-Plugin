package com.zhan.hy.format;

import com.zhan.hy.bean.FileInfo;

/**
 * AUTHOR：  HyZhan
 * create：  2019/7/6
 * desc：    TODO
 */
interface Wrapper {

    String wrap(String content, FileInfo fileInfo);
}
