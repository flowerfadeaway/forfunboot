package com.liu.forfunboot.util;

import com.liu.forfunboot.po.Result;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResultUtil implements Serializable {


    public static Result success(Object data){
        return Result.builder()
                .data(data)
                .code(0)
                .message("成功")
                .build();
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(){
        return Result.builder()
                .data(null)
                .code(1)
                .message("错误")
                .build();
    }



}
