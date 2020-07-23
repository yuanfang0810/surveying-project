package com.aaa.hou.mapper;

import com.aaa.hou.model.MappingProject;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MappingProjectMapper extends Mapper<MappingProject> {

    List<MappingProject> selectAllMappingProject();

    List<MappingProject>selectGroupByName();
}