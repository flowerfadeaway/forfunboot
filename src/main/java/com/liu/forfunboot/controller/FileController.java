package com.liu.forfunboot.controller;

import com.liu.forfunboot.dto.FileDto;
import com.liu.forfunboot.po.Result;
import com.liu.forfunboot.util.FileUtil;
import com.liu.forfunboot.util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileController {

    @GetMapping("/test")
    public Result<String> test(){
        return ResultUtil.success("this is a test");
    }



    @GetMapping("/getfile")
    public Result<FileDto> getFilesMapByPath(@RequestParam("filepath") String filePath ){
        Path path = Paths.get(filePath, "");
        File file = path.toFile();

        //TODO 如果该地址是文件，则获取空的文件map 返回null；如果该地址是文件夹，则获取该文件夹下所有的文件map <文件路径，文件全名>
        Map<String, String> childrenMap = FileUtil.getFileMapByOne(path);

        if (childrenMap==null){
            //文件无法显示子路径文件。
            return ResultUtil.error();
        }

        //TODO 将childrenMap中的内容抽取出来放在FileDto中，再响应返回。此时childrenMap不会是null，但有可能文件夹下空，就是childrenMap是个空map。空map也可以返回前端。没问题。
        List<FileDto> fileDtos = FileUtil.toFileDto(childrenMap);



        return ResultUtil.success(fileDtos);

    }

}
