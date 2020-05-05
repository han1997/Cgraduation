package com.example.graduation.sys.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: huiyuan
 * @date: 2020/5/4 21:44
 * @desc: 这是描述
 **/
@Data
public class AjaxVoResult<T> {
    private int statusCode;
    private String statusMessage;
    private T datas;

    public AjaxVoResult() {
    }

    public AjaxVoResult(int statusCode, String statusMessage, T datas) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.datas = datas;
    }

}
