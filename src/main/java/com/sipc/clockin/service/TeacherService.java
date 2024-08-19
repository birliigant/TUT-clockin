package com.sipc.clockin.service;

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

import java.util.List;

public interface TeacherService {
    CommonResult<TeacherInfo> queryTeacherInfo();
    CommonResult<BlankResult> updateTeacherInfo(UpdateTeacherRequest request);
    CommonResult<BlankResult> addClass(ClassRequest request);
    CommonResult<BlankResult> deleteClass(ClassRequest request);
    CommonResult<BlankResult> addManager(ManageRequest request);
    CommonResult<BlankResult> deleteManager(ManageRequest request);
    CommonResult<List<UserSimple>> selectStudents(int classId);
    CommonResult<HomePageResult> getTeacherHomePage(String className);
    CommonResult<List<StudentClockDetail>> getTeacherRecord(Integer classId, DateTime date);
    CommonResult<RestResult> getRestInfo(Integer clockId);
}
