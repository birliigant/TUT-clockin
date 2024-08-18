package com.sipc.clockin.service;

import com.sipc.clockin.pojo.model.CommonResult;
import com.sipc.clockin.pojo.model.request.EmailRequest;
import com.sipc.clockin.pojo.model.request.LoginRequest;
import com.sipc.clockin.pojo.model.request.RegisterRequest;
import com.sipc.clockin.pojo.model.request.ResetRequest;
import com.sipc.clockin.pojo.model.result.BlankResult;
import com.sipc.clockin.pojo.model.result.TokenResult;

public interface UserService {
    CommonResult<TokenResult> login(LoginRequest request);
    CommonResult<TokenResult> register(RegisterRequest request);
    CommonResult<BlankResult> reset(ResetRequest request);
    CommonResult<BlankResult> verifyCode(EmailRequest request);
}
