package com.aaa.hou.controller;

import com.aaa.hou.base.BaseController;
import com.aaa.hou.model.Role;
import com.aaa.hou.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hou
 * @description
 * @data 2020/7/18 11:10
 **/
@RestController
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/selectAllRole")
    public PageInfo selectAllARole (Integer pageNo,Integer pageSize){
        PageInfo pageInfo = roleService.selectAllARole(pageNo, pageSize);
        if (pageInfo != null) {
            return pageInfo;
        }
        return null;
    }

    /**
     *
     * @param roleId
     * @return
     */
    @GetMapping("selectRolePrimaryKey")
    public Role selectRolePrimaryKey (@RequestParam("roleId") Long roleId){
        Role role = roleService.selectRoleByParimaryKey(roleId);
        if (role != null && !"".equals(role)) {
            return role;
        }
            return null;
    }

}
