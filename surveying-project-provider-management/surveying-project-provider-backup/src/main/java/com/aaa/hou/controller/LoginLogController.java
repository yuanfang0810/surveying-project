package com.aaa.hou.controller;


import com.aaa.hou.base.BaseService;
import com.aaa.hou.base.CommonController;
import com.aaa.hou.model.LoginLog;
import com.aaa.hou.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/7/15 12:05
 * @Description
 **/
@RestController
public class LoginLogController extends CommonController<LoginLog> {

    @Autowired
    private LoginLogService loginLogService;

    @Override
    public BaseService<LoginLog> getBaseService() {
        return loginLogService;
    }

    /**
     * @author Seven Lee
     * @description
     *      保存日志
     * @param [loginLog]
     * @date 2020/7/15
     * @return java.lang.Integer
     * @throws
     **/
    @PostMapping("/addLoginLog")
    public Integer addLoginLog(@RequestBody LoginLog loginLog) {
        return getBaseService().add(loginLog);
    }

}
