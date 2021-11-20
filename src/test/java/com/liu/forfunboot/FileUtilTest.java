package com.liu.forfunboot;

import com.liu.forfunboot.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class FileUtilTest {

    @Test
    public void test1(){
        Path path = Paths.get(FileUtil.BASICPATH, "");
        List<File> filelistByOne = FileUtil.getFilelistByOne(path);
        filelistByOne.forEach(System.out::println);

    }
    @Test
    public void test2(){
        Path path = Paths.get(".");
        Map<String, String> fileMapByOne = FileUtil.getFileMapByOne(path);
        System.out.println(fileMapByOne);
    }

}
