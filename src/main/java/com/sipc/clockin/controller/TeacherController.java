package com.sipc.clockin.controller;

import com.sipc.clockin.pojo.domain.DO.TeacherInfo;
import com.sipc.clockin.pojo.domain.DO.UserSimple;
import com.sipc.clockin.pojo.model.CommonResult;
import com.sipc.clockin.pojo.model.request.ClassRequest;
import com.sipc.clockin.pojo.model.request.ManageRequest;
import com.sipc.clockin.pojo.model.request.UpdateTeacherRequest;
import com.sipc.clockin.pojo.model.result.BlankResult;
import com.sipc.clockin.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {
    private TeacherService teacherService;
    CommonResult<TeacherInfo> queryTeacherInfo(){
        return teacherService.queryTeacherInfo();
    }
    CommonResult<BlankResult> updateTeacherInfo(UpdateTeacherRequest request){
        return teacherService.updateTeacherInfo(request);
    }
    CommonResult<BlankResult> addClass(ClassRequest request){
        return teacherService.addClass(request);
    }
    CommonResult<BlankResult> deleteClass(ClassRequest request){
        return teacherService.deleteClass(request);
    }
    CommonResult<BlankResult> addManager(ManageRequest request){
        return teacherService.addManager(request);
    }
    CommonResult<BlankResult> deleteManager(ManageRequest request){
        return teacherService.deleteManager(request);
    }
    CommonResult<List<UserSimple>> selectStudents(@RequestParam("class_id") int classId){
        return teacherService.selectStudents(classId);
    }
}
