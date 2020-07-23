package com.aaa.hou.controller;

import com.aaa.hou.model.Dept;
import com.aaa.hou.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.aaa.hou.status.OperationStatus.FAILED;
import static com.aaa.hou.status.OperationStatus.SUCCESS;

/**
 * @author hou
 * @description
 * @data 2020/7/17 16:47
 **/
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

        @PostMapping("/selectDeptByDeptId")
    public Dept selectDeptByDeptId(@RequestParam("deptId") Integer deptId) {
        /**
         * @Description:
         *         查询部门信息，根据主键id查询部门的信息
         * @Param: [deptId]
         * @Author: PC
         * @Return: com.aaa.hou.model.Dept
         * @Date: 2020/7/17 16:49
         **/
        // 调用 deptService 中的 selectDeptByDeptId 方法，得到查询结果
        Dept deptByDeptId = deptService.selectDeptByDeptId(deptId);

            // 判断 结果是否为空
            if(null != deptByDeptId && !"".equals(deptByDeptId)){
                // 返回 查询的部门信息
                return deptByDeptId;
            }else {
                // 查询失败，返回null
                return null;
            }
        // 判断 结果是否为空

//        if (dept != null) {
//            // 说明结果不为空，返回查询的结果
//            map.put("code",SUCCESS.getCode());
//            map.put("msg",SUCCESS.getMsg());
//            map.put("data",dept);
//
//        }else {
//            // 返回null
//            map.put("code",FAILED.getCode());
//            map.put("msg",FAILED.getMsg());
//        }
//        return map;
    }

}
