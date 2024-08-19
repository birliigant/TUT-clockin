package com.sipc.clockin.controller;

import com.sipc.clockin.pojo.model.CommonResult;
import com.sipc.clockin.pojo.model.request.EmailRequest;
import com.sipc.clockin.pojo.model.request.LoginRequest;
import com.sipc.clockin.pojo.model.request.RegisterRequest;
import com.sipc.clockin.pojo.model.request.ResetRequest;
import com.sipc.clockin.pojo.model.result.BlankResult;
import com.sipc.clockin.pojo.model.result.TokenResult;
import com.sipc.clockin.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @PostMapping("/login")
    private CommonResult<TokenResult> login(@RequestBody LoginRequest request){
        return userService.login(request);
    }
    @PostMapping("/reset")
    private CommonResult<BlankResult> reset(@RequestBody ResetRequest request){
        return userService.reset(request);
    }
    @PostMapping("/register")
    private CommonResult<TokenResult> register(@RequestBody RegisterRequest request){
        return userService.register(request);
    }
    @PostMapping("/code")
    private CommonResult<BlankResult> code(@RequestBody EmailRequest request){
        return userService.verifyCode(request);
    }
}
