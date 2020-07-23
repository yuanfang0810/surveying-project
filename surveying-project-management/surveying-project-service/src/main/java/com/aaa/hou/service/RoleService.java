package com.aaa.hou.service;

import com.aaa.hou.base.BaseService;
import com.aaa.hou.base.ResultData;
import com.aaa.hou.mapper.RoleMapper;
import com.aaa.hou.mapper.RoleMenuMapper;
import com.aaa.hou.model.Role;
import com.aaa.hou.model.RoleMenu;
import com.aaa.hou.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @author hou
 * @description
 * @data 2020/7/16
 **/
@Service
public class RoleService extends BaseService<Role> {
    @Autowired
    RoleMapper roleMapper;

    /**
     * @Author hou
     * @Description 查询所有角色信息
     * @Param
     * @return
     **/
    public PageInfo selectAllARole(Integer pageNo,Integer pageSize){
        // 当前页数和一页数量
        PageHelper.startPage(pageNo,pageSize);

        try {
            //查询权限信息
            List<Role> roles = roleMapper.selectAll();
            //判断查询结果是否为空
            if (!"".equals(roles) && null !=roles){
                //将查询结果放入
                PageInfo<Role> rolePageInfo = new PageInfo<>(roles);
                //返回查询结果
                return rolePageInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @Author wxz
     * @Description 条件查询
     * @Param
     * @return
     **/
    public PageInfo selectRoleByField(Map map, Integer pageNo, Integer pageSize){
        // 当前页数和一页数量
        PageHelper.startPage(pageNo,pageSize);

        try {
            //查询角色信息
            List<Role> roles = roleMapper.selectRoleByField(map);
            //判断查询结果是否为空
            if(!"".equals(roles) && null !=roles){
                //将查询结果放入
                PageInfo<Role> rolePageInfo = new PageInfo<>(roles);
                return rolePageInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author wxz
     * @Description  根据id查询角色信息
     * @Param
     * @return
     **/
    public Role selectRoleByParimaryKey(Long roleId){
        //判断前段是否传值成功
        if (null !=roleId){
            try {
                //根据id查询
                Role role = roleMapper.selectByPrimaryKey(roleId);
                //判断查询结果是否为空
                if (!"".equals(role) && null !=role){
                    //返回查询信息
                    return role;
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }




}
