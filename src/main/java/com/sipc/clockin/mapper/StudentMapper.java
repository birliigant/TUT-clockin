package com.sipc.clockin.mapper;

import cn.hutool.core.date.DateTime;
import com.sipc.clockin.pojo.domain.PO.Clock;
import com.sipc.clockin.pojo.model.request.ClockRequest;
import com.sipc.clockin.pojo.model.result.RestResult;
import com.sipc.clockin.pojo.model.result.StudentClockDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Clock> getClockRecord(Integer id); //左下角获取打卡记录
    Integer addClock(ClockRequest request); //打卡
    List<StudentClockDetail> getClassClock(Integer workId, DateTime date); //获取班级打卡信息
    List<Integer> getClass (Integer classId);
    RestResult getRestInfo(Integer clockId);
}
