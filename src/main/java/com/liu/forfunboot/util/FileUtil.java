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
     * ??????????????????????????????
     * @qq 153358882
     * @param response
     * @return ??????
     * @throws IOException
     * @filePath ????????????
     */
    public static void  downloadFile (HttpServletRequest request, HttpServletResponse response, String filePath)
    {

        try {
            File file=new File(filePath);
            if(file.exists()){
                String fileName  = file.getName().toString();
                // firefox?????????
                if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
                    fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
                } // IE?????????
                else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
                    fileName = URLEncoder.encode(fileName, "UTF-8");
                }// ??????
                else if (request.getHeader("User-Agent").toUpperCase().indexOf("CHROME") > 0) {
                    fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
                }

                response.setContentType("application/octet-stream");
                response.setCharacterEncoding("utf-8");
                response.setContentLength((int) file.length());
                response.setHeader("Content-Disposition", "attachment;filename="  + fileName );
                //????????????????????????????????????force-download???????????????????????????????????????????????????????????????
//                response.setContentType("application/force-download");
                //???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
//                response.addHeader("Content-Disposition","attachment;filename="+ fileName );
//                response.setCharacterEncoding("UTF-8");
                //??????????????????
                byte[]buffer=new byte[1024];
                FileInputStream fis=null;
                BufferedInputStream bis=null;
                try{
                    fis=new FileInputStream(file);
                    bis=new BufferedInputStream(fis);
                    OutputStream os=response.getOutputStream();
                    //??????????????????
                    int i=bis.read(buffer);
                    while(i!=-1){
                        //??????response???????????????
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
