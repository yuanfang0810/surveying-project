package com.aaa.hou.mapper;

import com.aaa.hou.model.Principal;
import org.hibernate.validator.constraints.SafeHtml;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface PrincipalMapper extends Mapper<Principal> {
     List<Principal> queryOne (Long id);
}