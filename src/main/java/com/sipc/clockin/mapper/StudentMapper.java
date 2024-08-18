package com.sipc.clockin.mapper;

import cn.hutool.core.date.DateTime;
import com.sipc.clockin.pojo.domain.PO.Clock;
import com.sipc.clockin.pojo.model.request.ClockRequest;
import com.sipc.clockin.pojo.model.result.HomeClassResult;
import com.sipc.clockin.pojo.model.result.HomePageResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    HomePageResult getClockInformation(Integer id, DateTime date);//获取打卡哪里的上边的信息
    List<HomeClassResult> getClassInformation(Integer classId,DateTime date);//获取打卡哪里班级里其他人的信息
    List<Clock> getClockRecord(Integer id); //左下角获取打卡记录
    Integer addClock(ClockRequest request, Integer classId, Integer messageId); //打卡
    Integer addMessage(ClockRequest request);
}
