package com.sipc.clockin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.sipc.clockin.mapper.UserMapper;
import com.sipc.clockin.pojo.domain.PO.User;
import com.sipc.clockin.pojo.model.CommonResult;
import com.sipc.clockin.pojo.model.enumeration.RedisFlags;
import com.sipc.clockin.pojo.model.request.EmailRequest;
import com.sipc.clockin.pojo.model.request.LoginRequest;
import com.sipc.clockin.pojo.model.request.RegisterRequest;
import com.sipc.clockin.pojo.model.request.ResetRequest;
import com.sipc.clockin.pojo.model.result.BlankResult;
import com.sipc.clockin.pojo.model.result.TokenResult;
import com.sipc.clockin.service.UserService;
import com.sipc.clockin.utils.JwtUtils;
import com.sipc.clockin.utils.MD5Utils;
import com.sipc.clockin.utils.MailUtil;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private RedisTemplate<String, Object> redisTemplate;

    private MailUtil mailUtil;

    private UserMapper userMapper;
    @Override
    public CommonResult<TokenResult> login(LoginRequest request) {
        User user = userMapper.selectByWorkId(request.getWorkId());
        if (ObjectUtil.isEmpty(user)){
            return CommonResult.fail("用户不存在!");
        }
        if (!user.getPassword().equals(MD5Utils.encrypt(request.getPassword()))){
            return CommonResult.fail("用户名或密码错误");
        }
        String token = JwtUtils.sign(user);
        redisTemplate.opsForValue().set(RedisFlags.TOKEN_PREFIX+user.getEmail(), token, 30, TimeUnit.DAYS);
        TokenResult result = new TokenResult();
        result.setToken(JwtUtils.sign(user));
        return CommonResult.success("登录成功",result);
    }

    @Override
    public CommonResult<TokenResult> register(RegisterRequest request) {
        if (!request.getIsAgreed()){
            return CommonResult.fail("未同意相关协议无法注册");
        }
        if (ObjectUtil.isEmpty(request) || request.getEmail() == null
                || request.getCode() == null || request.getPassword() == null
                || request.getName() == null || request.getWorkId() == null
        ){
            return CommonResult.fail("注册信息不完善");
        }
        try {
            String email = request.getEmail();
            Object cachedCode = redisTemplate.opsForValue().get(RedisFlags.VERIFY_PREFIX+email);
            if (cachedCode == null || !cachedCode.toString().equals(request.getCode())) {
                return CommonResult.fail("验证码错误");
            }
            if (ObjectUtil.isNotEmpty(userMapper.selectByEmail(email))){
                return CommonResult.fail("该邮箱已被注册");
            }
            User user = new User();
            BeanUtil.copyProperties(request, user);
            user.setCreateTime(new Date());
            user.setPassword(MD5Utils.encrypt(request.getPassword()));
            if (userMapper.insert(user) == 0){
                return CommonResult.fail("注册失败");
            }
            redisTemplate.delete(RedisFlags.VERIFY_PREFIX+email);
            if (request.getIsRemembered()){
                TokenResult result = new TokenResult();
                result.setToken(JwtUtils.sign(user));
                return CommonResult.success("注册成功",result);
            }
            return CommonResult.success("注册成功");
        } catch (DataAccessException e) {
            e.printStackTrace();
            return CommonResult.fail("注册失败，数据库操作异常");
        }
    }

    @Override
    public CommonResult<BlankResult> reset(ResetRequest request) {
        if (ObjectUtil.isEmpty(request)  || request.getEmail() == null  || request.getWorkId() == null
                || request.getCode() == null || request.getPassword() == null) {
            return CommonResult.fail("密码重置信息不完整");
        }
        try {
            Object cachedCode = redisTemplate.opsForValue().get(RedisFlags.VERIFY_PREFIX+request.getEmail());
            if (cachedCode == null || !cachedCode.toString().equals(request.getCode())) {
                return CommonResult.fail("验证码错误");
            }
            if (ObjectUtil.isEmpty(userMapper.selectByEmail(request.getEmail()))){
                return CommonResult.fail("用户不存在");
            }
            if (userMapper.updatePassword(request.getEmail(), MD5Utils.encrypt(request.getPassword())) == 0){
                return CommonResult.fail("修改密码失败");
            }
            return CommonResult.success("修改密码成功");
        }  catch (DataAccessException e) {
            return CommonResult.fail("密码重置失败，数据库操作异常");
        }
    }

    @Override
    public CommonResult<BlankResult> verifyCode(EmailRequest request) {
        if (ObjectUtil.isEmpty(request.getEmail())){
            return CommonResult.fail("邮箱为空");
        }
        String email = request.getEmail();
        String code = mailUtil.sendEmail(email);
        if(Boolean.FALSE.equals(redisTemplate.opsForValue().setIfAbsent(RedisFlags.VERIFY_PREFIX + email, code, 2, TimeUnit.MINUTES))){
            return CommonResult.fail("验证码已发送，请稍后再试");
        }
        return CommonResult.success("验证码已发送");
    }
}
