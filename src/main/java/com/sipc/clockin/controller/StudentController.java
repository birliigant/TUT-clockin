package com.sipc.clockin.controller;

import cn.hutool.core.date.DateTime;
import com.sipc.clockin.pojo.domain.DO.UserInfo;
import com.sipc.clockin.pojo.domain.PO.Clock;
import com.sipc.clockin.pojo.model.CommonResult;
import com.sipc.clockin.pojo.model.request.ClockRequest;
import com.sipc.clockin.pojo.model.result.BlankResult;
import com.sipc.clockin.pojo.model.result.HomePageResult;
import com.sipc.clockin.pojo.model.result.StudentClockDetail;
import com.sipc.clockin.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentServiceImpl;

    //获取首页信息
    @GetMapping("/home")
    private CommonResult<HomePageResult> getHomePage() {
        return studentServiceImpl.getHomePage();
    }

    //普通学生查看历史打卡记录
    @GetMapping("/clockin")
    private CommonResult<List<Clock>> getClockRecord() {
        return studentServiceImpl.getClockRecord();
    }

    //管理员查看历史打卡记录
    @GetMapping("record")
    private CommonResult<List<StudentClockDetail>> getAdminRecord(DateTime date) {
        return studentServiceImpl.getAdminRecord(date);
    }

    //打卡
    @PostMapping("/clockin")
    private CommonResult<BlankResult> clockIn(@RequestBody ClockRequest request){
        return studentService.clockIn(request);

    //查看个人信息
    @GetMapping("/info")
    private CommonResult<UserInfo> getInfo() {
        return studentServiceImpl.getUserInfo();
    }

}
