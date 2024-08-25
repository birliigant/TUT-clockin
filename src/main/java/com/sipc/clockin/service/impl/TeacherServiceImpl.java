package com.sipc.clockin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.sipc.clockin.enums.RoleEnum;
import com.sipc.clockin.handler.interceptor.Role;
import com.sipc.clockin.handler.token.TokenHandler;
import com.sipc.clockin.mapper.ClazzMapper;
import com.sipc.clockin.mapper.StudentMapper;
import com.sipc.clockin.mapper.UserMapper;
import com.sipc.clockin.pojo.domain.DO.Teacher;
import com.sipc.clockin.pojo.domain.DO.TeacherInfo;
import com.sipc.clockin.pojo.domain.DO.UserSimple;
import com.sipc.clockin.pojo.domain.PO.User;
import com.sipc.clockin.pojo.model.CommonResult;
import com.sipc.clockin.pojo.model.TokenModel;
import com.sipc.clockin.pojo.model.request.ClassRequest;
import com.sipc.clockin.pojo.model.request.ManageRequest;
import com.sipc.clockin.pojo.model.request.UpdateTeacherRequest;
import com.sipc.clockin.pojo.model.result.*;
import com.sipc.clockin.service.TeacherService;
import com.sipc.clockin.utils.DataParseUtils;
import com.sipc.clockin.utils.DateTimeParseUtils;
import com.sipc.clockin.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private ClazzMapper clazzMapper;
    private UserMapper userMapper;
    private StudentMapper studentMapper;
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
    //老师获取首页
    @Override
    public CommonResult<HomePageResult> getTeacherHomePage(String className,String type) {
        Integer classId = clazzMapper.selectIdByName(className);
        //因为和学生的界面长得都差不多,拿一个当前班级的学生id,然后用学生管理看首页的逻辑
        if(classId == null) return CommonResult.fail("未查询到当前班级,请检查输入内容");
        List<Integer> aClass = studentMapper.getClass(classId);
        Integer id = aClass.get(0);
        DateTime date = new DateTime();
        //获取班级所有人的详细打卡记录
        List<StudentClockDetail> classClock = studentMapper.getClassClock(id, DateTime.of(DateTimeParseUtils.getStartOfDay(date)));
        if (classClock == null || classClock.isEmpty()) return CommonResult.fail("未查询到相关打卡信息");
        //统计当前打卡轮次中参与人数
        Integer completionNum = 0;
        List<StudentClockDetail> typeClock = new ArrayList<>();
        for (StudentClockDetail tem : classClock) {
            if (tem.getIsPass() != 0) completionNum++;
            //筛选要单独显示的type类型记录
            if (type.equals("打卡") && tem.getIsPass() == 0) typeClock.add(tem);
            else if (type.equals("请假") && StringUtils.isNotEmpty(tem.getType()) && tem.getType().equals(type)) typeClock.add(tem);
        }
        //将共有部分提取到Result中
        StudentClockDetail studentClockDetail = classClock.get(0);
        FLAG:
        for (StudentClockDetail tem : classClock){
            studentClockDetail = tem;
            //检查当前数据中是否有空字段(防止单个数据存储错误导致返回结果为空)
            try {
                for (Field f : studentClockDetail.getClass().getDeclaredFields()) {
                    f.setAccessible(true);
                    if (f.get(studentClockDetail) == null || StringUtils.isEmpty(f.get(studentClockDetail).toString())) {
                        continue FLAG;
                    }
                }
            }catch (Exception e){
                continue;
            }
            break;//(os:应该不会一个班没有一条记录是正确的吧...
        }
        HomePageResult result = new HomePageResult(
                studentClockDetail.getClassId(),studentClockDetail.getClassName(),
                studentClockDetail.getStudentNum(),studentClockDetail.getMessageId(),
                studentClockDetail.getStartTime(),studentClockDetail.getEndTime(),
                completionNum,classClock,typeClock
        );
        return CommonResult.success(result);
    }
    //老师查看打卡记录
    @Override
    public CommonResult<HomePageResult> getTeacherRecord(Integer classId,DateTime date,String type) {
        //获取班里人的学号
        List<Integer> aClass = studentMapper.getClass(classId);
        if (aClass == null || aClass.isEmpty()) return CommonResult.fail("未查询到当前班级,请检查输入内容");
        Integer workId = aClass.get(0);
        if(date == null) date = new DateTime();
        if(date.isBefore(DateUtil.offsetDay(new Date(),-7))) return CommonResult.fail("仅显示最近一周打卡记录");
        //只保留日期
        List<StudentClockDetail> classClock = studentMapper.getClassClock(workId, DateTime.of(DateTimeParseUtils.getStartOfDay(date)));
        if(classClock == null || classClock.isEmpty()) return CommonResult.fail("未查询到相关打卡记录");
        List<StudentClockDetail> typeClock = new ArrayList<>();
        //筛选要单独显示的type类型记录
        for (StudentClockDetail tem : classClock) {
            if (type.equals("打卡") && tem.getIsPass() == 0) typeClock.add(tem);
            else if (type.equals("请假") && StringUtils.isNotEmpty(tem.getType()) && tem.getType().equals(type)) typeClock.add(tem);
        }
        HomePageResult result = new HomePageResult(
                null,null,
                null,null,
                null,null,
                null,classClock,typeClock
        );
        return CommonResult.success(result);
    }
    //获取请假详情
    @Override
    public CommonResult<RestResult> getRestInfo(Integer clockId){
        RestResult restInfo = studentMapper.getRestInfo(clockId);
        if(restInfo == null) return CommonResult.fail("未查询到该打卡信息");
        if(StringUtils.isEmpty(restInfo.getReason())) return  CommonResult.fail("未查询到当前打卡记录请假原因");
        return CommonResult.success(restInfo);
    }

    @Override
    public CommonResult<List<GetClazzResult>> getClazz(Boolean needAll) {
        if (needAll == null) needAll = false;
        Integer workId = TokenHandler.getTokenModelThreadLocal().getWorkId();
        //获取负责班级
        String clazzIds = clazzMapper.getClazzIds(workId);
        List<Integer> list = null;
        if (!StringUtils.isEmpty(clazzIds))
            list = DataParseUtils.parseJsonToList(clazzIds, Integer.class);
        //需要全部班级
        if (needAll){
            List<GetClazzResult> allClazz = clazzMapper.getAllClazz();
            //标记负责班级
            for (GetClazzResult tem : allClazz){
                Integer id = tem.getClazzId();
                if (list != null && list.contains(id)) tem.setResponsible(true);
                else tem.setResponsible(false);
            }
            return CommonResult.success(allClazz);
        }
        if (list == null) return CommonResult.fail("未查询到负责班级信息");
        List<GetClazzResult> clazz = clazzMapper.getClazz(list);
        if (clazz == null || clazz.isEmpty()) return CommonResult.fail("未查询到负责班级信息");
        for (GetClazzResult tem : clazz) tem.setResponsible(true);
        return CommonResult.success(clazz);
    }
}
