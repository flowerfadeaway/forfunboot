package com.liu.forfunboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.liu.forfunboot.dto.WrapDto;
import com.liu.forfunboot.po.Result;
import com.liu.forfunboot.service.HomeProfileService;
import com.liu.forfunboot.util.FileUtil;
import com.liu.forfunboot.util.ResultUtil;
import com.liu.forfunboot.util.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/home")
@CrossOrigin(origins = "*")
public class HomeController {

    @Autowired
    private HomeProfileService homeProfileService;

    @GetMapping(value = "/getProfile")
    public Results.Result getProfile(@RequestParam("id") String id, @RequestParam(value = "path",defaultValue = "") String path) {
        List<WrapDto> profilePath = homeProfileService.getProfilePath(id, path);
        return Results.success(profilePath);
    }

    @GetMapping(value = "/getFiles")
    public Results.Result getFiles(@RequestParam("path") String path) {
        List<WrapDto> files = homeProfileService.getFiles(path);
        return Results.success(files);
    }

    @GetMapping(value = "/download")
    public Results.Result getDownload(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id, @RequestParam(value = "path",defaultValue = "") String path) {
        FileUtil.downloadFile(request, response, path);
        return Results.success(null);
    }

    @RequestMapping("/upload")
    public Result getupload(@RequestParam("files") MultipartFile[] files, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        JSONObject data = JSONObject.parseObject(httpServletRequest.getParameter("data"));
        String uploadStore = data.getString("path");
        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            Path destPath = Paths.get(uploadStore, originalFilename);
            File destFile = destPath.toFile();
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            try {
                file.transferTo(destFile);
                log.info("文件{}已经存储在了{}",originalFilename,uploadStore);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                return ResultUtil.error();
            }
        }
        return ResultUtil.success();
    }

    @GetMapping("/hello")
    public String hello() {
        return "welcome to home";
    }

}
