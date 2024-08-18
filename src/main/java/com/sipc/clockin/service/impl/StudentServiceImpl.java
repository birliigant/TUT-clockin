package com.sipc.clockin.service.impl;

import cn.hutool.core.date.DateTime;
import com.sipc.clockin.handler.token.TokenHandler;
import com.sipc.clockin.mapper.StudentMapper;
import com.sipc.clockin.pojo.domain.PO.Clock;
import com.sipc.clockin.pojo.model.CommonResult;
import com.sipc.clockin.pojo.model.request.ClockRequest;
import com.sipc.clockin.pojo.model.result.BlankResult;
import com.sipc.clockin.pojo.model.result.HomeClassResult;
import com.sipc.clockin.pojo.model.result.HomePageResult;
import com.sipc.clockin.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;
    @Override
    public CommonResult<HomePageResult> getHomePage(DateTime date) {
        Integer id = TokenHandler.getTokenModelThreadLocal().getWorkId();
        HomePageResult homePageResult = studentMapper.getClockInformation(id,date);
        Integer classId = homePageResult.getClassId();
        List<HomeClassResult> classInformation = studentMapper.getClassInformation(classId,date);
        homePageResult.setHomeClassResults(classInformation);
        return CommonResult.success(homePageResult);
    }

    @Override
    public CommonResult<List<Clock>> getClockRecord() {
        Integer id = TokenHandler.getTokenModelThreadLocal().getWorkId();
        List<Clock> clockRecord = studentMapper.getClockRecord(id);
        return CommonResult.success(clockRecord);
    }

    @Override
    public CommonResult<BlankResult> clockIn(ClockRequest request) {
        Integer id = TokenHandler.getTokenModelThreadLocal().getWorkId();
        HomePageResult clockInformation = studentMapper.getClockInformation(id,request.getTime());
        studentMapper.addClock(request,clockInformation.getClassId(),clockInformation.getMessageId());
        return CommonResult.success();
    }
}
