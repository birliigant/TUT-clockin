package com.sipc.clockin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sipc.clockin.pojo.domain.PO.Clazz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClazzMapper extends BaseMapper<Clazz> {
    List<String> selectNameByIds(@Param("ids") List<Integer> ids);
    Integer selectIdByName(String className);
}
