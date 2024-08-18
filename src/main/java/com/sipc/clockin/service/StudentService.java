package com.sipc.clockin.service;

import cn.hutool.core.date.DateTime;
import com.sipc.clockin.pojo.domain.PO.Clock;
import com.sipc.clockin.pojo.model.CommonResult;
import com.sipc.clockin.pojo.model.request.ClockRequest;
import com.sipc.clockin.pojo.model.result.BlankResult;
import com.sipc.clockin.pojo.model.result.HomePageResult;

import java.util.List;

public interface StudentService {
    CommonResult<HomePageResult> getHomePage(DateTime date);
    CommonResult<List<Clock>> getClockRecord();
    CommonResult<BlankResult> clockIn(ClockRequest request);
}
