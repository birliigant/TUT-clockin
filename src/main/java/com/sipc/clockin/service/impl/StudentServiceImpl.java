package com.sipc.clockin.service.impl;

import cn.hutool.core.date.DateTime;
import com.sipc.clockin.handler.token.TokenHandler;
import com.sipc.clockin.mapper.StudentMapper;
import com.sipc.clockin.pojo.domain.PO.Clock;
import com.sipc.clockin.pojo.model.CommonResult;
import com.sipc.clockin.pojo.model.request.ClockRequest;
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
        //TODO: 可能需要保留为只有日期
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
    //老师获取首页
    @Override
    public CommonResult<HomePageResult> getTeacherHomePage(Integer classId) {
        //因为和学生的界面长得都差不多,拿一个当前班级的学生id
        List<Integer> aClass = studentMapper.getClass(classId);
        Integer studentId = aClass.get(0);
        //TODO: 可能需要保留为只有日期
        DateTime date = new DateTime();
        //获取班级所有人的详细打卡记录
        List<StudentClockDetail> classClock = studentMapper.getClassClock(studentId, DateTime.of(DateTimeParseUtils.getStartOfDay(date)));
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
    //老师查看打卡记录
    public CommonResult<List<StudentClockDetail>> getTeacherRecord(Integer classId,DateTime date) {
        List<Integer> aClass = studentMapper.getClass(classId);
        Integer workId = aClass.get(0);
        List<StudentClockDetail> classClock = studentMapper.getClassClock(workId, date);
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
}
