package com.sipc.clockin.controller;

import cn.hutool.core.date.DateTime;
import com.sipc.clockin.pojo.domain.DO.TeacherInfo;
import com.sipc.clockin.pojo.domain.DO.UserSimple;
import com.sipc.clockin.pojo.model.CommonResult;
import com.sipc.clockin.pojo.model.request.ClassRequest;
import com.sipc.clockin.pojo.model.request.ManageRequest;
import com.sipc.clockin.pojo.model.request.UpdateTeacherRequest;
import com.sipc.clockin.pojo.model.result.BlankResult;
import com.sipc.clockin.pojo.model.result.HomePageResult;
import com.sipc.clockin.pojo.model.result.RestResult;
import com.sipc.clockin.pojo.model.result.StudentClockDetail;
import com.sipc.clockin.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {
    private TeacherService teacherService;
    @GetMapping("/myself")
    CommonResult<TeacherInfo> queryTeacherInfo(){
        return teacherService.queryTeacherInfo();
    }
    @PutMapping("/update")
    CommonResult<BlankResult> updateTeacherInfo(@RequestBody UpdateTeacherRequest request){
        return teacherService.updateTeacherInfo(request);
    }
    @PostMapping("/class/add")
    CommonResult<BlankResult> addClass(@RequestBody ClassRequest request){
        return teacherService.addClass(request);
    }
    @PostMapping("/class/delete")
    CommonResult<BlankResult> deleteClass(@RequestBody ClassRequest request){
        return teacherService.deleteClass(request);
    }
    @PostMapping("/manage/add")
    CommonResult<BlankResult> addManager(@RequestBody ManageRequest request){
        return teacherService.addManager(request);
    }
    @PostMapping("/manage/delete")
    CommonResult<BlankResult> deleteManager(@RequestBody ManageRequest request){
        return teacherService.deleteManager(request);
    }
    @GetMapping("/class")
    CommonResult<List<UserSimple>> selectStudents(@RequestParam("class_id") int classId){
        return teacherService.selectStudents(classId);
    }
    //老师查看打卡记录
    @GetMapping("/record")
    CommonResult<List<StudentClockDetail>> getTeacherRecord(@RequestParam("class_id") Integer classId,
                                                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") DateTime date){
        return teacherService.getTeacherRecord(classId,date);
    }
    //获取首页信息
    @GetMapping("/home")
    private CommonResult<HomePageResult> getHomePage(@RequestParam("class_name") String className){
        return teacherService.getTeacherHomePage(className);
    }
    //获取请假详情
    @GetMapping("/leave")
    private CommonResult<RestResult> getRestInfo(@RequestParam("clock_id") Integer clockId){
        return teacherService.getRestInfo(clockId);
    }
}
