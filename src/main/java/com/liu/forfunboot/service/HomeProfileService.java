package com.liu.forfunboot.service;

import com.liu.forfunboot.constant.HomeConstants;
import com.liu.forfunboot.dto.WrapDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class HomeProfileService {

    public List<WrapDto> getProfilePath(String id) {
        String path = HomeConstants.idToProfilePath.getOrDefault(id, null);
        if (StringUtils.isBlank(path)){
            log.error("id not exist");
            return null;
        }
        //仅搜索一层目录下所有文件，仅仅包括文件名，不包括路径。
        List<String> childs = searchProfileByPath(path);
        ArrayList<WrapDto> wrapDtos = new ArrayList<WrapDto>();
        for (String child : childs) {
            WrapDto wrapDto = getWrapDto(path + "/" + child);
            wrapDtos.add(wrapDto);
        }
        return wrapDtos;
    }

    private WrapDto getWrapDto(String path) {
        WrapDto wrapDto = new WrapDto();
        File file = new File(path);
        wrapDto.setName(file.getName());
        wrapDto.setPath(file.getParent());
        wrapDto.setFile(file.isFile());
        return wrapDto;
    }

    private List<String> searchProfileByPath(String path) {
        //全路径查找
        Path filePath = Paths.get(path, "");
        File file = filePath.toFile();
        boolean isFile = file.isFile();
        if (isFile){
            return null;
        }
        File[] childFiles = file.listFiles();
        ArrayList<String> pathList = new ArrayList<>();
        for (File childFile : childFiles) {
            pathList.add(childFile.getName());
        }
        return pathList;
    }

    public List<WrapDto> getFiles(String path) {
        //左侧某个链接获取该文件的子文件
        ArrayList<WrapDto> wrapDtos = new ArrayList<>();
        File file = new File(path);
        File[] files = file.listFiles();
        for (File file1 : files) {
            wrapDtos.add(getWrapDto(file1.getAbsolutePath()));
        }
        return wrapDtos;

    }
}
