package com.liu.forfunboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

//@SpringBootTest
class ForfunbootApplicationTests {

    private static String PATH = "/Users/nowcoder/nowcoder/liujingxuan/";

    @Test
    void contextLoads() {
    }

    @Test
    public void test1() throws IOException {
        File file = new File(PATH);
        System.out.println(file.exists());
        System.out.println(file.getName());
        System.out.println(file.toString());
        System.out.println(file.createNewFile());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getCanonicalFile());
        System.out.println(file.getCanonicalPath());
        System.out.println(file.getParent());
//        System.out.println(file.list().toString());
        String[] list = file.list();
        System.out.println(Arrays.toString(list));
        System.out.println(file.length());
        System.out.println(file.toPath());
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());

    }

    @Test
    public void test2(){
        Path path = Paths.get("/Users", "nowcoder");
        System.out.println(path);
        File file = path.toFile();
        System.out.println(file.isDirectory());
        System.out.println(file.isFile());
        System.out.println(path.getFileName());
//        path.resolve()
        Path path1 = Paths.get(PATH, "zaji.md");
        File file1 = path1.toFile();

        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.isFile());
        System.out.println(file1.isDirectory());
        System.out.println(Arrays.toString(file1.list()));

    }

    public static void getFilesMap(Map<String,File> fileMap, Path path){
        File file = path.toFile();
        if (file.isFile()){
            fileMap.put(file.getName(), file);
            return;
        }
    }

    public static void getFilesList(List<File> fileList, Path path){
        File file = path.toFile();
        if (file.isFile()){
            fileList.add(file);
            return;
        }
        File[] nextFiles = file.listFiles();
        for (File nextFile : nextFiles) {
            getFilesList(fileList, Paths.get(nextFile.getAbsolutePath()));
        }

    }

    @Test
    public void test3(){
        ArrayList<File> files = new ArrayList<>();
        Path path = Paths.get(PATH );
        getFilesList(files, path);
        files.stream().forEach(System.out::println);
    }

}
