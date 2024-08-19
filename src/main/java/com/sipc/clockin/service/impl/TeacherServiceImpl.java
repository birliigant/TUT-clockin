package com.sipc.clockin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.sipc.clockin.enums.RoleEnum;
import com.sipc.clockin.handler.interceptor.Role;
import com.sipc.clockin.handler.token.TokenHandler;
import com.sipc.clockin.mapper.ClazzMapper;
import com.sipc.clockin.mapper.UserMapper;
import com.sipc.clockin.pojo.domain.DO.TeacherInfo;
import com.sipc.clockin.pojo.domain.DO.UserSimple;
import com.sipc.clockin.pojo.domain.DO.Teacher;
import com.sipc.clockin.pojo.domain.PO.User;
import com.sipc.clockin.pojo.model.CommonResult;
import com.sipc.clockin.pojo.model.TokenModel;
import com.sipc.clockin.pojo.model.request.ClassRequest;
import com.sipc.clockin.pojo.model.request.ManageRequest;
import com.sipc.clockin.pojo.model.request.UpdateTeacherRequest;
import com.sipc.clockin.pojo.model.result.BlankResult;
import com.sipc.clockin.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private ClazzMapper clazzMapper;
    private UserMapper userMapper;
    @Role(identities = {RoleEnum.MANAGER})
    @Override
    public CommonResult<TeacherInfo> queryTeacherInfo() {
        TokenModel token = TokenHandler.getTokenModelThreadLocal();
        Teacher teacher = userMapper.selectTeacher(token.getWorkId());
        if (ObjectUtil.isEmpty(teacher)){
            return CommonResult.fail("查询失败");
        }
        TeacherInfo teacherInfo = new TeacherInfo();
        BeanUtil.copyProperties(teacher, teacherInfo);
        List<Integer> ids = new ArrayList<>();
        for (String s : teacher.getClassIds().substring(1, teacher.getClassIds().length() - 1).split(",")) {
            ids.add(Integer.valueOf(s));
        }
        if (ObjectUtil.isEmpty(ids)){
            return CommonResult.fail("查询失败");
        }
        List<String> classNames = clazzMapper.selectNameByIds(ids);
        teacherInfo.setClassNames(classNames);
        return CommonResult.success("查询成功", teacherInfo);
    }

    @Role(identities = {RoleEnum.MANAGER})
    @Override
    public CommonResult<BlankResult> updateTeacherInfo(UpdateTeacherRequest request) {
        TokenModel token = TokenHandler.getTokenModelThreadLocal();
        if (userMapper.updateTeacherInfo(request.getCollegeId(), request.getIcon(), token.getWorkId()) == 0){
            return CommonResult.fail("修改失败");
        }
        return CommonResult.success("修改成功");
    }

    @Role(identities = {RoleEnum.MANAGER})
    @Override
    public CommonResult<BlankResult> addClass(ClassRequest request) {
        if (ObjectUtil.isEmpty(request.getClassIds())){
            return CommonResult.fail("ClassID数组为空");
        }
        TokenModel token = TokenHandler.getTokenModelThreadLocal();
        User user = userMapper.selectByWorkId(token.getWorkId());
        if (ObjectUtil.isEmpty(user)){
            return CommonResult.fail("查询失败");
        }
        String[] strIds = user.getClassIds().substring(1, user.getClassIds().length()-1).split(",");
        List<Integer> ids = new ArrayList<>();
        for (String id : strIds) {
            StrUtil.trim(id);
            ids.add(Integer.valueOf(id));
        }
        for (int classId : request.getClassIds()) {
            ids.add(classId);
        }
        String classIds = ids.toString();
        if (userMapper.updateClassIds(classIds, token.getWorkId()) == 0){
            return CommonResult.fail("修改失败");
        }
        return CommonResult.success("修改成功");
    }

    @Role(identities = {RoleEnum.MANAGER})
    @Override
    public CommonResult<BlankResult> deleteClass(ClassRequest request) {
        if (ObjectUtil.isEmpty(request.getClassIds())){
            return CommonResult.fail("ClassID数组为空");
        }
        TokenModel token = TokenHandler.getTokenModelThreadLocal();
        User user = userMapper.selectByWorkId(token.getWorkId());
        if (ObjectUtil.isEmpty(user)){
            return CommonResult.fail("查询失败");
        }
        String[] strIds = user.getClassIds().substring(1, user.getClassIds().length()-1).split(",");
        List<Integer> ids = new ArrayList<>();
        for (String id : strIds) {
            StrUtil.trim(id);
            ids.add(Integer.valueOf(id));
        }
        for (int classId : request.getClassIds()) {
            ids.remove(classId);
        }
        String classIds = ids.toString();
        if (userMapper.updateClassIds(classIds, token.getWorkId()) == 0){
            return CommonResult.fail("修改失败");
        }
        return CommonResult.success("修改成功");
    }

    @Role(identities = {RoleEnum.MANAGER})
    @Override
    public CommonResult<BlankResult> addManager(ManageRequest request) {
        if (ObjectUtil.isEmpty(request.getStudentIds())){
            return CommonResult.fail("StudentID数组为空");
        }
        int num = userMapper.selectManager(request.getClassId()).size();
        if (num + request.getStudentIds().length > 6){
            return CommonResult.fail("设置学生管理员数量超出上限，上限为6");
        }
        userMapper.updateRole(request.getStudentIds(), RoleEnum.CENTRE);
        return CommonResult.success("添加学生管理员成功");
    }

    @Role(identities = {RoleEnum.MANAGER})
    @Override
    public CommonResult<BlankResult> deleteManager(ManageRequest request) {
        if (ObjectUtil.isEmpty(request.getStudentIds())){
            return CommonResult.fail("StudentID数组为空");
        }
        userMapper.updateRole(request.getStudentIds(), RoleEnum.ORDINARY);
        return CommonResult.success("删除学生管理员成功");
    }

    @Role(identities = {RoleEnum.MANAGER})
    @Override
    public CommonResult<List<UserSimple>> selectStudents(int classId) {
        if (ObjectUtil.isEmpty(classId)){
            return CommonResult.fail("请求错误");
        }
        List<UserSimple> users = userMapper.selectStudent(classId);
        if (ObjectUtil.isEmpty(users)){
            return CommonResult.fail("未查询到学生");
        }
        return CommonResult.success("查询成功", users);
    }
}
