package com.aaa.hou.mapper;

import com.aaa.hou.model.MappingUnit;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

public interface MappingUnitMapper extends Mapper<MappingUnit> {

    /**
     * @Author: hou
     * @Param:
     * @Description:通过字段查询所有的区域和资质，进行分组
     * @Return:
     * @Date: 2020/7/20 19:48
     **/
    public List<HashMap> selectAllUnit();


}