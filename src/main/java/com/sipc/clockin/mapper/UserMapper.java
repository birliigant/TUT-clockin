package com.sipc.clockin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sipc.clockin.pojo.domain.DO.UserSimple;
import com.sipc.clockin.pojo.domain.DO.Teacher;
import com.sipc.clockin.pojo.domain.PO.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectByEmail(String email);
    User selectByWorkId(Integer workId);
    Teacher selectTeacher(Integer workId);
    int updatePassword(String email, String password);
    int updateTeacherInfo(int collegeId, String icon, int workId);
    int updateClassIds(String classIds, int workId);
    int updateRole(@Param("ids") int[] studentIds, String role);
    List<User> selectManager(int classId);
    List<UserSimple> selectStudent(int classId);
}
