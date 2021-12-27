package com.liu.forfunboot.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.liu.forfunboot.dto.FileDto;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.tuple.Pair;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileUtil {

    public static final String BASICPATH = "/Users/liujx/javastudy";

    public static List<File> getFilelistByOne(Path path) {
        File file = path.toFile();
        if (file.isFile()) {
            return Lists.newArrayList(file);
        }
        return Lists.newArrayList(Objects.requireNonNull(file.listFiles()));
    }

    public static Map<String, String> getFileMapByOne(Path path) {
        File file = path.toFile();
        if (file.isFile()) {
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

    /**
     * 下载指定路径下的文件
     * @qq 153358882
     * @param response
     * @return 文件
     * @throws IOException
     * @filePath 文件路径
     */
    public static void  downloadFile (HttpServletRequest request, HttpServletResponse response, String filePath)
    {

        try {
            File file=new File(filePath);
            if(file.exists()){
                String fileName  = file.getName().toString();
                // firefox浏览器
                if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
                    fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
                } // IE浏览器
                else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
                    fileName = URLEncoder.encode(fileName, "UTF-8");
                }// 谷歌
                else if (request.getHeader("User-Agent").toUpperCase().indexOf("CHROME") > 0) {
                    fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
                }

                response.setContentType("application/octet-stream");
                response.setCharacterEncoding("utf-8");
                response.setContentLength((int) file.length());
                response.setHeader("Content-Disposition", "attachment;filename="  + fileName );
                //首先设置响应的内容格式是force-download，那么你一旦点击下载按钮就会自动下载文件了
//                response.setContentType("application/force-download");
                //通过设置头信息给文件命名，也即是，在前端，文件流被接受完还原成原文件的时候会以你传递的文件名来命名
//                response.addHeader("Content-Disposition","attachment;filename="+ fileName );
//                response.setCharacterEncoding("UTF-8");
                //进行读写操作
                byte[]buffer=new byte[1024];
                FileInputStream fis=null;
                BufferedInputStream bis=null;
                try{
                    fis=new FileInputStream(file);
                    bis=new BufferedInputStream(fis);
                    OutputStream os=response.getOutputStream();
                    //从源文件中读
                    int i=bis.read(buffer);
                    while(i!=-1){
                        //写到response的输出流中
                        os.write(buffer,0,i);
                        i=bis.read(buffer);
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
