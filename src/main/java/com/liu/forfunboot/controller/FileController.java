package com.liu.forfunboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileController {



    @GetMapping("/")
    public void getFilesMapByPath(@RequestParam("filepath") String filePath ){

    }

}
