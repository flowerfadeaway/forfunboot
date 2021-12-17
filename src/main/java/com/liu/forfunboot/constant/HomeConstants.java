package com.liu.forfunboot.constant;

import java.util.HashMap;
import java.util.Map;

public class HomeConstants {
    public static Map<String, String> idToProfilePath = new HashMap<>();

    static {
        idToProfilePath.put("root", "/Users");
        idToProfilePath.put("xuanxuan", "/Users/ljx");
        idToProfilePath.put("ruirui", "/Users/ruirui");
    }

}
