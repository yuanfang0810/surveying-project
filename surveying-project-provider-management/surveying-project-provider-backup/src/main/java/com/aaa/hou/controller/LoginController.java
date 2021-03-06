package com.aaa.hou.controller;


import com.aaa.hou.base.BaseService;
import com.aaa.hou.base.CommonController;
import com.aaa.hou.base.ResultData;
import com.aaa.hou.model.User;
import com.aaa.hou.service.LoginService;
import com.aaa.hou.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.aaa.hou.status.LoginStatus.*;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/7/15 9:32
 * @Description
 **/
@RestController
public class LoginController extends CommonController<User> {

    @Autowired
    private LoginService loginService;

    @Override
    public BaseService<User> getBaseService() {
        return loginService;
    }

    /**
     * @author Seven Lee
     * @description
     *      执行登录操作
     * @param [user]
     * @date 2020/7/15
     * @return com.aaa.lee.base.ResultData
     * @throws
    **/
    @PostMapping("/doLogin")
        public ResultData doLogin(@RequestBody User user) {
        TokenVo tokenVo = loginService.doLogin(user);
        if(tokenVo.getIfSuccess()) {
            return super.loginSuccess(tokenVo.getToken());
        } else if(tokenVo.getType() == 1) {
            return super.loginFailed(USER_NOT_EXIST.getMsg());
        } else if(tokenVo.getType() == 2) {
            return super.loginFailed(PASSWORD_WRONG.getMsg());
        } else {
            return super.loginFailed(SYSTEM_EXCEPTION.getMsg());
        }
    }


}
