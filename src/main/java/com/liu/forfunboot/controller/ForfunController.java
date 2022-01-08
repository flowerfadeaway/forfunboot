package com.liu.forfunboot.controller;

import com.alibaba.excel.EasyExcel;
import com.liu.forfunboot.client.BadGuyFeignClient;
import com.liu.forfunboot.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/forfun")
@CrossOrigin(origins = "*")
public class ForfunController {

    @Autowired
    private BadGuyFeignClient badGuyFeignClient;

    @GetMapping("/hello")
//    @CrossOrigin
    public String hello(){
        System.out.println("hello ljx");
        return "hello ljx.jenkins";
    }


    /**
     * Author: HuYuQiao
     * Description: 浏览器下载--excel
     */
    @GetMapping("/testRespExcel")
    public void testRespExcel(HttpServletResponse response){
        response.addHeader("Content-Disposition", "attachment;filename=" + "huyuqiao.xlsx");
        response.setContentType("application/vnd.ms-excel;charset=gb2312");
        try {
//            从HttpServletResponse中获取OutputStream输出流
            ServletOutputStream outputStream = response.getOutputStream();
            /*
             * EasyExcel 有多个不同的read方法，适用于多种需求
             * 这里调用EasyExcel中通过OutputStream流方式输出Excel的write方法
             * 它会返回一个ExcelWriterBuilder类型的返回值
             * ExcelWriterBuilde中有一个doWrite方法，会输出数据到设置的Sheet中
             */
            EasyExcel.write(outputStream, User.class).sheet("测试数据").doWrite(getAllUser());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUser(){
        List<User> userList = new ArrayList<>();
        for (int i=0;i<100;i++){
            User user = User.builder().name("胡宇乔"+ i).password("huyuqiao").age(i).build();
            userList.add(user);
        }
        return userList;
    }

    @RequestMapping(value="/downloadFile")
    public void downloadFile(HttpServletResponse response) {
        String downloadFilePath = "/Users/nowcoder/Downloads/";	//被下载的文件在服务器中的路径,
        String fileName = "suanfa.pdf";			//被下载文件的名称

        File file = new File(downloadFilePath+fileName);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(bis != null) {
                    try {
                        bis.close();
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
                }
                if(fis != null) {
                    try {
                        fis.close();
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @GetMapping("/badGuy")
    public String getSweething(){
        String sweetNothings = badGuyFeignClient.getSweetNothings();
        return sweetNothings;
    }



}
