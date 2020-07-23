package com.aaa.hou.mapper;

import com.aaa.hou.model.Role;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Param:
 * @Author: PC
 * @Return:
 * @Date: 2020/7/17 18:57
 **/
public interface RoleMapper extends Mapper<Role> {

    List<Role> selectRoleByField(Map map);

}