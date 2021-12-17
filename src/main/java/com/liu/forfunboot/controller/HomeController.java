package com.liu.forfunboot.controller;

import com.liu.forfunboot.dto.WrapDto;
import com.liu.forfunboot.service.HomeProfileService;
import com.liu.forfunboot.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/home")
@CrossOrigin(origins = "*")
public class HomeController {

    @Autowired
    private HomeProfileService homeProfileService;

    @GetMapping(value = "/getProfile")
    public Results.Result getProfile(@RequestParam("id") String id) {
        List<WrapDto> profilePath = homeProfileService.getProfilePath(id);
        return Results.success(profilePath);
    }

    @GetMapping(value = "/getFiles")
    public Results.Result getFiles(@RequestParam("path") String path) {
        List<WrapDto> files = homeProfileService.getFiles(path);
        return Results.success(files);
    }

}
