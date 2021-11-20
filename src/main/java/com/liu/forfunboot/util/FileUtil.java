package com.liu.forfunboot.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.liu.forfunboot.dto.FileDto;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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
            return null;
        }
        HashMap<String, String> hashMap = Maps.newHashMap();
        List<File> childFiles = Arrays.asList(Objects.requireNonNull(file.listFiles()));
        for (File childFile : childFiles) {
            hashMap.put(childFile.getAbsolutePath(), childFile.getName());
        }
        return hashMap;
    }

    public static List<FileDto> toFileDto(Map<String, String> pathMap) {
        ArrayList<FileDto> fileDtos = new ArrayList<>();
        for (Map.Entry<String, String> entry : pathMap.entrySet()) {
            String fullPath = entry.getKey();
            String fullName = entry.getValue();
            FileDto fileDto = FileDto.builder()
                    .fileName(fullName)
                    .filePureName(Files.getNameWithoutExtension(fullName))
                    .fileSuffix(Files.getFileExtension(fullName))
                    .filePath(fullPath)
                    .isFile(new File(fullPath).isFile())
                    .pathCurrent(new File(fullPath).getParent())
                    .build();
            fileDtos.add(fileDto);
        }
        return fileDtos;


    }

}
