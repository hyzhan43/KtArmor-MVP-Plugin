package com.zhan.hy.creator;

/**
 * AUTHOR：  HyZhan
 * create：  2019/7/6
 * desc：    TODO
 */
public class FileFactory {

    public static <T extends Creator> T createCreator(Class<T> creator){
        try {
            return creator.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
