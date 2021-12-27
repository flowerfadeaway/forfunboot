package com.liu.forfunboot.controller;

import com.liu.forfunboot.po.Result;
import com.liu.forfunboot.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/")
@Slf4j
@CrossOrigin
public class UploadDownloadController {

    @Value("${upload.store}")
    private String uploadStore;

    @RequestMapping("/upload")
    public Result uploadFile(@RequestParam("files")MultipartFile[] files){
        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            Path destPath = Paths.get(uploadStore, originalFilename);
            File destFile = destPath.toFile();
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            try {
                file.transferTo(destFile);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                return ResultUtil.error();
            }
        }
        return ResultUtil.success();
    }

    @RequestMapping("/download")
    public Result fileDownLoad(HttpServletResponse response, @RequestParam("fileName") String fileName) throws UnsupportedEncodingException {
        File file = new File(fileName);
        if(!file.exists()){
            return ResultUtil.error();
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename="  + URLEncoder.encode(fileName.substring(fileName.lastIndexOf("/")+1),"utf8") );

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return ResultUtil.error();
        }
        return ResultUtil.success();
    }

}
