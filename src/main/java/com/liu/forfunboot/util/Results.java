package com.liu.forfunboot.util;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

public class Results {

    @Data
    @Builder
    public static class Result<T> implements Serializable {
        private int code;
        private String message;
        private T data;
    }

    public static <T> Result success(int code, String message, T data){
        return Result.builder().code(code).message(message).data(data).build();
    }

    public static <T> Result success(T data){
        return success(0, "success", data);
    }

    public static <T> Result error(int code, String message, T data){
        return Result.builder().code(code).message(message).data(data).build();
    }

}
