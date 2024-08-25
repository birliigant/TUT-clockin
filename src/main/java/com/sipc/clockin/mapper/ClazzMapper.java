package com.sipc.clockin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sipc.clockin.pojo.domain.PO.Clazz;
import com.sipc.clockin.pojo.model.result.GetClazzResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClazzMapper extends BaseMapper<Clazz> {
    List<String> selectNameByIds(@Param("ids") List<Integer> ids);
    Integer selectIdByName(String className);
    List<GetClazzResult> getClazz(List<Integer> clazzIds);
    String getClazzIds(Integer workId);
}
