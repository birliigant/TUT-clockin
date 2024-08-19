package com.sipc.clockin.controller;

import cn.hutool.core.date.DateTime;
import com.sipc.clockin.pojo.domain.PO.Clock;
import com.sipc.clockin.pojo.model.CommonResult;
import com.sipc.clockin.pojo.model.request.ClockRequest;
import com.sipc.clockin.pojo.model.result.BlankResult;
import com.sipc.clockin.pojo.model.result.HomePageResult;
import com.sipc.clockin.pojo.model.result.StudentClockDetail;
import com.sipc.clockin.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    //获取首页信息
    @GetMapping("/home")
    private CommonResult<HomePageResult> getHomePage(){
        return studentService.getHomePage();
    }
    //普通学生查看历史打卡记录
    @GetMapping("/clockin")
    private CommonResult<List<Clock>> getClockRecord(){
        return studentService.getClockRecord();
    }
    //管理员查看历史打卡记录
    @GetMapping("record")
    private CommonResult<List<StudentClockDetail>>getAdminRecord(DateTime date){
        return studentService.getAdminRecord(date);
    }
    //打卡
    @PostMapping("/clockin")
    private CommonResult<BlankResult> clockIn(ClockRequest request){
        return studentService.clockIn(request);
    }
    //管理员新建打卡message
    @PostMapping("/create")
    private CommonResult<BlankResult> createMessage(){
        return studentService.createMessage();
    }
}
