package com.sipc.clockin.service;

import cn.hutool.core.date.DateTime;
import com.sipc.clockin.pojo.domain.DO.UserInfo;
import com.sipc.clockin.pojo.domain.PO.Clock;
import com.sipc.clockin.pojo.model.CommonResult;
import com.sipc.clockin.pojo.model.request.ClockRequest;
import com.sipc.clockin.pojo.model.request.UpdateStudentRequest;
import com.sipc.clockin.pojo.model.result.BlankResult;
import com.sipc.clockin.pojo.model.result.HomePageResult;
import com.sipc.clockin.pojo.model.result.RestResult;

import java.util.List;

public interface StudentService {
    CommonResult<HomePageResult> getHomePage(String type);

    CommonResult<List<Clock>> getClockRecord();

    CommonResult<BlankResult> clockIn(ClockRequest request);

    CommonResult<HomePageResult> getAdminRecord(String type,DateTime date);
    CommonResult<RestResult> getRestInfo(Integer classId);
    //查询用户信息
    CommonResult<UserInfo> getUserInfo();

    //修改个人信息
    CommonResult<BlankResult> updateUserInfo(UpdateStudentRequest request);

    CommonResult<HomePageResult> getOrdinaryHomePage();
}
