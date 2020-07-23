package com.aaa.hou.mapper;


import com.aaa.hou.model.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

public interface UserMapper extends Mapper<User> {


    /**
     * 条件分页查询所有用户
     * @param map
     * @return
     */

    public HashMap<String,Object> selectName(@Param("username") String username);

//    List<HashMap>selectId(HashMap map);

}