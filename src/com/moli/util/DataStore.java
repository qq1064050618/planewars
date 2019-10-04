package com.moli.util;

import java.util.HashMap;
import java.util.Map;

public class DataStore {
    //用来存储GameFrame对象，方便在其他类里边调用。


    private static final Map<String,Object> map=new HashMap<>();
    public static void put(String key,Object value){
map.put(key,value);
    }
    public static <T> T get(String key) {
        return (T) map.get(key);
    }
}
