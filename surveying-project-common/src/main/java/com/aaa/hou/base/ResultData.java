package com.aaa.hou.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author hou
 * @description
 * @data 2020/7/16
 **/

@Data
@Accessors(chain = true)
public class ResultData<T> implements Serializable {

    private String code;
    private String msg;
    private String detail;
    private T data;

}