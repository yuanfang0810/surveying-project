package com.aaa.hou.controller;

import com.aaa.hou.base.BaseService;
import com.aaa.hou.base.CommonController;
import com.aaa.hou.base.ResultData;
import com.aaa.hou.model.User;
import com.aaa.hou.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hou
 * @description
 * @data 2020/7/17 14:52
 **/
@RestController
public class UserController extends CommonController<User> {
    @Autowired
    private UserService userService;

    @Override


    public BaseService<User> getBaseService() {

        return userService;
    }
    /**
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("/selectAllUser")
    public ResultData selectAllUser(User user, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        
        /**
         * @Author: hou
         * @Param: [user, pageNo, pageSize]
         * @Description:
         * @Return: com.aaa.hou.base.ResultData
         * @Date: 2020/7/18 16:11
         **/

        PageInfo<User> userPageInfo = userService.selectListByPage(user, pageNo, pageSize);
        if (userPageInfo != null &&!"".equals(userPageInfo)){
            return operationSuccess(userPageInfo);
        }else {
            return operationFailed();
        }
    }

}
