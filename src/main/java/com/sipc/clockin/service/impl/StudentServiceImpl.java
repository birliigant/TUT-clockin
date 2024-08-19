package com.sipc.clockin.service.impl;

import cn.hutool.core.date.DateTime;
import com.sipc.clockin.handler.token.TokenHandler;
import com.sipc.clockin.mapper.StudentMapper;
import com.sipc.clockin.pojo.domain.DO.UserInfo;
import com.sipc.clockin.pojo.domain.PO.Clock;
import com.sipc.clockin.pojo.model.CommonResult;
import com.sipc.clockin.pojo.model.request.ClockRequest;
import com.sipc.clockin.pojo.model.request.UpdateStudentRequest;
import com.sipc.clockin.pojo.model.result.BlankResult;
import com.sipc.clockin.pojo.model.result.HomePageResult;
import com.sipc.clockin.pojo.model.result.RestResult;
import com.sipc.clockin.pojo.model.result.StudentClockDetail;
import com.sipc.clockin.service.StudentService;
import com.sipc.clockin.utils.DateTimeParseUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;
    //获取首页信息
    @Override
    public CommonResult<HomePageResult> getHomePage() {
        Integer id = TokenHandler.getTokenModelThreadLocal().getWorkId();
        DateTime date = new DateTime();
        //获取班级所有人的详细打卡记录
        List<StudentClockDetail> classClock = studentMapper.getClassClock(id, DateTime.of(DateTimeParseUtils.getStartOfDay(date)));
        if (classClock == null || classClock.isEmpty()) return CommonResult.fail("未查询到相关打卡信息");
        //统计当前打卡轮次中参与人数
        Integer completionNum = 0;
        for (StudentClockDetail tem : classClock) {
            if (tem.getIsPass() != 0) completionNum++;
        }
        StudentClockDetail studentClockDetail = classClock.get(0);
        HomePageResult result = new HomePageResult(
                studentClockDetail.getClassId(),studentClockDetail.getClassName(),
                studentClockDetail.getStudentNum(),studentClockDetail.getMessageId(),
                studentClockDetail.getStartTime(),studentClockDetail.getEndTime(),
                completionNum,classClock
        );
        return CommonResult.success(result);
    }
    //普通学生获取打卡记录
    @Override
    public CommonResult<List<Clock>> getClockRecord() {
        Integer id = TokenHandler.getTokenModelThreadLocal().getWorkId();
        List<Clock> clockRecord = studentMapper.getClockRecord(id);
        return CommonResult.success(clockRecord);
    }
    //管理员获取打卡记录
    @Override
    public CommonResult<List<StudentClockDetail>> getAdminRecord(DateTime date) {
        Integer id = TokenHandler.getTokenModelThreadLocal().getWorkId();
        List<StudentClockDetail> classClock = studentMapper.getClassClock(id, DateTime.of(DateTimeParseUtils.getStartOfDay(date)));
        return CommonResult.success(classClock);
    }

    //打卡
    @Override
    public CommonResult<BlankResult> clockIn(ClockRequest request) {
        Integer id = TokenHandler.getTokenModelThreadLocal().getWorkId();
        request.setStudentId(id);
        studentMapper.addClock(request);
        return CommonResult.success();
    }
    //获取请假详情
    @Override
    public CommonResult<RestResult> getRestInfo(Integer clockId){
        RestResult restInfo = studentMapper.getRestInfo(clockId);
        return CommonResult.success(restInfo);
    }

    //查看学生信息
    @Override
    public CommonResult<UserInfo> getUserInfo() {
        Integer studentId = TokenHandler.getTokenModelThreadLocal().getWorkId();
        UserInfo userInfo = studentMapper.getUserById(studentId);
        return CommonResult.success(userInfo);
    }

    @Override
    public CommonResult<BlankResult> updateUserInfo(UpdateStudentRequest request) {
        Integer studentId = TokenHandler.getTokenModelThreadLocal().getWorkId();
        Integer i = studentMapper.updateUserInfo(studentId, request);
        if (i == 1) {
            return CommonResult.success("更新成功");
        } else {
            return CommonResult.fail("更新失败");
        }
    }


}
