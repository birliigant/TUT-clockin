package com.sipc.clockin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sipc.clockin.pojo.domain.PO.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectByEmail(String email);
    User selectByWorkId(Integer workId);
    int updatePassword(String email, String password);
}
