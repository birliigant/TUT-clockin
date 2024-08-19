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
import com.sipc.clockin.pojo.model.result.StudentClockDetail;
import com.sipc.clockin.service.StudentService;
import lombok.AllArgsConstructor;
import org.apache.el.parser.Token;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;

    @Override
    public CommonResult<HomePageResult> getHomePage() {
        Integer id = TokenHandler.getTokenModelThreadLocal().getWorkId();
        //TODO: 可能需要保留为只有日期
        DateTime date = new DateTime();
        //获取班级所有人的详细打卡记录
        List<StudentClockDetail> classClock = studentMapper.getClassClock(id, date);
        //统计当前打卡轮次中参与人数
        Integer completionNum = 0;
        for (StudentClockDetail tem : classClock) {
            if (tem.getType() != 0) completionNum++;
        }
        StudentClockDetail studentClockDetail = classClock.get(0);
        HomePageResult result = new HomePageResult(
                studentClockDetail.getClassId(), studentClockDetail.getClassName(),
                studentClockDetail.getNum(), studentClockDetail.getMessageId(),
                completionNum, classClock
        );
        return CommonResult.success(result);
    }

    @Override
    public CommonResult<List<Clock>> getClockRecord() {
        Integer id = TokenHandler.getTokenModelThreadLocal().getWorkId();
        List<Clock> clockRecord = studentMapper.getClockRecord(id);
        return CommonResult.success(clockRecord);
    }

    @Override
    public CommonResult<List<StudentClockDetail>> getAdminRecord(DateTime date) {
        Integer id = TokenHandler.getTokenModelThreadLocal().getWorkId();
        List<StudentClockDetail> classClock = studentMapper.getClassClock(id, date);
        return CommonResult.success(classClock);
    }

    @Override
    public CommonResult<BlankResult> clockIn(ClockRequest request) {
        Integer id = TokenHandler.getTokenModelThreadLocal().getWorkId();
        request.setStudentId(id);
        studentMapper.addClock(request);
        return CommonResult.success();
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
