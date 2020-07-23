package com.aaa.hou.controller;

import com.aaa.hou.model.MappingProject;
import com.aaa.hou.service.MapperProjectService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hou
 * @description
 * @data 2020/7/18 15:33
 **/
@RestController
public class MappingProjectController {
    @Autowired
    private MapperProjectService mapperProjectService;
    @GetMapping("/selectAll")
    public PageInfo<MappingProject> selectAllProject(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        return mapperProjectService.selectAllMappingProject(pageNo,pageSize);

    }

}
