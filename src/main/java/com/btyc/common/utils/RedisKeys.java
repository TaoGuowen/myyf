package com.btyc.common.utils;

/**
 * Redis所有Keys
 *
 * @author ams
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }
}
