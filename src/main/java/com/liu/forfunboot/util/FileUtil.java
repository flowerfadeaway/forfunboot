package com.liu.forfunboot.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.ListUtils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FileUtil {

    public static final String BASICPATH = "/Users/liujx/javastudy";

    public static List<File> getFilelistByOne(Path path){
        File file = path.toFile();
        if (file.isFile()){
            return Lists.newArrayList(file);
        }
        return Lists.newArrayList(Objects.requireNonNull(file.listFiles()));
    }

    public static Map<String,String> getFileMapByOne(Path path){
        File file = path.toFile();
        if (file.isFile()){
            HashMap<String, String> hashMap = Maps.newHashMap();
            hashMap.put(file.toString(), file.getAbsolutePath());
            return hashMap;
        }
        return null;
    }

}
