package com.aaa.hou.service;

import com.aaa.hou.base.BaseService;
import com.aaa.hou.mapper.DeptMapper;
import com.aaa.hou.model.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hou
 * @description
 * @data 2020/7/17 16:37
 **/
@Service
public class DeptService extends BaseService <Dept> {
    @Autowired
    private DeptMapper deptMapper;

    public Dept selectDeptByDeptId(Integer deptId) {
        /**
         * @Description:
         *      查询部门信息，根据主键id查询部门的信息
         * @Param: [deptId]
         * @Author: PC
         * @Return: com.aaa.hou.model.Dept
         * @Date: 2020/7/17 16:46
         **/
        // 调用 deptMapper 中的 selectDeptByDeptId 方法，查询部门信息
        Dept deptByDeptId = deptMapper.selectDeptByDeptId(deptId);

        // 判断 结果是否为空
        if(null != deptByDeptId && !"".equals(deptByDeptId)){
            // 返回 查询的部门信息

            return deptByDeptId;
        }else {
            // 查询失败，返回null
            return null;
        }
    }

}
