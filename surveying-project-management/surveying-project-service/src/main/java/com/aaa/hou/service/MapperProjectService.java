package com.aaa.hou.service;

import com.aaa.hou.base.BaseService;
import com.aaa.hou.mapper.MappingProjectMapper;
import com.aaa.hou.model.MappingProject;
import com.aaa.hou.status.OperationStatus;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hou
 * @description
 * @data 2020/7/18 14:58
 **/
@Service
public class MapperProjectService extends BaseService<MappingProject> {
    @Autowired
    private MappingProjectMapper mappingProjectMapper;

    public PageInfo<MappingProject> selectAllMappingProject(Integer pageNo, Integer pageSize){
        /**
         * @Author: hou
         * @Param: [pageNo, pageSize]
         * @Description:
         * @Return: com.github.pagehelper.PageInfo<com.aaa.hou.model.MappingProject>
         * @Date: 2020/7/18 15:20
         **/
        PageHelper.startPage(pageNo,pageSize);
        List<MappingProject> mappingProjects = mappingProjectMapper.selectAll();

        if (mappingProjects.size()>0) {
            PageInfo<MappingProject> pageInfo = new PageInfo<>(mappingProjects);
            return pageInfo;
        }else {
          return null;
        }
    }

//    public Map<String,Object> SelectGroupByName(String name){
//        HashMap<String,Object> resultMap =new HashMap<>();
//
//    }
}
