package com.sipc.clockin.controller;

import cn.hutool.core.date.DateTime;
import com.sipc.clockin.pojo.domain.PO.Clock;
import com.sipc.clockin.pojo.model.CommonResult;
import com.sipc.clockin.pojo.model.request.ClockRequest;
import com.sipc.clockin.pojo.model.result.BlankResult;
import com.sipc.clockin.pojo.model.result.HomePageResult;
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
    @GetMapping("/home")
    private CommonResult<HomePageResult> getHomePage(DateTime date){
        return studentService.getHomePage(date);
    }
    @GetMapping("/record")
    private CommonResult<List<Clock>> getClockRecord(){
        return studentService.getClockRecord();
    }
    @PostMapping("/clock")
    private CommonResult<BlankResult> clockIn(ClockRequest request){
        return studentService.clockIn(request);
    }

}
