package com.sipc.clockin.service;

import com.sipc.clockin.pojo.domain.DO.TeacherInfo;
import com.sipc.clockin.pojo.domain.DO.UserSimple;
import com.sipc.clockin.pojo.model.CommonResult;
import com.sipc.clockin.pojo.model.request.ClassRequest;
import com.sipc.clockin.pojo.model.request.ManageRequest;
import com.sipc.clockin.pojo.model.request.UpdateTeacherRequest;
import com.sipc.clockin.pojo.model.result.BlankResult;

import java.util.List;

public interface TeacherService {
    CommonResult<TeacherInfo> queryTeacherInfo();
    CommonResult<BlankResult> updateTeacherInfo(UpdateTeacherRequest request);
    CommonResult<BlankResult> addClass(ClassRequest request);
    CommonResult<BlankResult> deleteClass(ClassRequest request);
    CommonResult<BlankResult> addManager(ManageRequest request);
    CommonResult<BlankResult> deleteManager(ManageRequest request);
    CommonResult<List<UserSimple>> selectStudents(int classId);
}
