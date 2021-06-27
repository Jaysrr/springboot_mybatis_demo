package com.jay.demo.utils;

import lombok.Data;

/**
 * @program: springboot_mybatis_demo
 * @description:
 * @author: Jaysrr
 * @create: 2021-06-22 20:20
 **/
@Data
public class JsonResult {

    private Integer code;
    private String msg;
    private Object data;

    public JsonResult() {
    }

    public JsonResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static JsonResult ok(  ) {
        return new JsonResult(200, "ok", "操作成功");
    }

    public static JsonResult ok(Object data) {
        return new JsonResult(200, "ok", data);
    }

    public static JsonResult error() {
        return new JsonResult(500, "error", null);
    }

    public static JsonResult error(Object data) {
        return new JsonResult(500, "error", data);
    }
    public static JsonResult error(String msg) {
        return new JsonResult(500, msg, null);
    }


}
