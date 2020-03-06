package com.zhan.hy.creator;

import com.zhan.hy.format.WrapperFactory;

/**
 * AUTHOR：  HyZhan
 * create：  2019/7/5
 * desc：    TODO
 */
class BaseCreator {

    String templatePath = "template/";

    protected WrapperFactory wrapperFactory;

    BaseCreator() {
        wrapperFactory = new WrapperFactory();
    }

}
