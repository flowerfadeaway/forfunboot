package com.liu.forfunboot;

import com.liu.forfunboot.po.User2;
import com.liu.forfunboot.util.CsvExportUtil;
import com.liu.forfunboot.util.CsvParser;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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

    public static Map<String,File> getFilesMap(Path path){
        //给定一个路径，能够找到该路径下所有的文件形成嵌套map。
        HashMap<String, File> fileHashMap = new HashMap<>();
        File file = path.toFile();
        if (file.isFile()){
            fileHashMap.put(file.getName(), file);
            return fileHashMap;
        }
        List<File> nextFiles = Arrays.asList(file.listFiles());
        for (File nextFile : nextFiles) {

        }
        return null;
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


    @Test
    public void test4(){
        String delimiter = ",";
        // 初始化parser
        CsvParser<User2> parser = new CsvParser<>(delimiter);
        // 定义测试对象
        User2 user = new User2(1, "mwf");
        // 转换
        String parsed = parser.parse(user);

        System.out.println(parsed);
    }


    @Test
    public void test5(){
        ArrayList<User2> user2s = Lists.newArrayList(new User2(1, "ljx1"), new User2(2, "ljx2"));
        String[] headers = {"id", "name"};
        byte[] bytes = CsvExportUtil.writeCSV(user2s, headers);
        System.out.println(new String(bytes));
    }

}
