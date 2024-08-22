package com.sipc.clockin.controller;

import cn.hutool.core.date.DateTime;
import com.sipc.clockin.enums.RoleEnum;
import com.sipc.clockin.handler.interceptor.Role;
import com.sipc.clockin.pojo.domain.DO.UserInfo;
import com.sipc.clockin.pojo.domain.PO.Clock;
import com.sipc.clockin.pojo.model.CommonResult;
import com.sipc.clockin.pojo.model.request.ClockRequest;
import com.sipc.clockin.pojo.model.request.UpdateStudentRequest;
import com.sipc.clockin.pojo.model.result.BlankResult;
import com.sipc.clockin.pojo.model.result.HomePageResult;
import com.sipc.clockin.pojo.model.result.RestResult;
import com.sipc.clockin.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    //一般学生获取首页信息
    @GetMapping("/ordinary/home")
    @Role(identities = {RoleEnum.ORDINARY})
    private CommonResult<HomePageResult> getOrdinaryHomePage(){
        return studentService.getOrdinaryHomePage();
    }
    //管理员获取首页信息
    @GetMapping("/home")
    @Role(identities = {RoleEnum.CENTRE})
    private CommonResult<HomePageResult> getHomePage(
            @RequestParam(value = "type",required = false,defaultValue = "打卡") String type){
        return studentService.getHomePage(type);
    }

    //普通学生查看历史打卡记录
    @GetMapping("/clockin")
    @Role(identities = {RoleEnum.ORDINARY})
    private CommonResult<List<Clock>> getClockRecord(){
        return studentService.getClockRecord();
    }

    //管理员查看历史打卡记录
    @GetMapping("record")
    @Role(identities = {RoleEnum.CENTRE})
    private CommonResult<HomePageResult>getAdminRecord(
            @RequestParam(value = "type",required = false,defaultValue = "打卡") String type,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") DateTime date){
        return studentService.getAdminRecord(type,date);
    }

    //打卡
    @PostMapping("/clockin")
    @Role(identities = {RoleEnum.CENTRE,RoleEnum.ORDINARY})
    private CommonResult<BlankResult> clockIn(@RequestBody ClockRequest request){
        return studentService.clockIn(request);
    }
    //获取请假详情
    @GetMapping("/leave")
    @Role(identities = {RoleEnum.CENTRE})
    private CommonResult<RestResult> getRestInfo(@RequestParam("clock_id") Integer clockId){
        return studentService.getRestInfo(clockId);
    }


    //查看个人信息
    // TODO: 未查询头像
    @GetMapping("/info")
    private CommonResult<UserInfo> getInfo() {
        return studentService.getUserInfo();
    }

    //修改个人信息
    //TODO: 未修改头像
    @PostMapping("/info")
    private CommonResult<BlankResult> updateInfo(@RequestBody UpdateStudentRequest request) {
        return studentService.updateUserInfo(request);
    }
}
