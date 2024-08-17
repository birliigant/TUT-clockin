package com.sipc.clockin.handler.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sipc.clockin.handler.token.TokenHandler;
import com.sipc.clockin.pojo.model.CommonResult;
import com.sipc.clockin.pojo.model.TokenModel;
import com.sipc.clockin.pojo.domain.enumeration.RedisFlags;
import com.sipc.clockin.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
@CrossOrigin
@AllArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws IOException {
        //todo： 实现token拦截器逻辑
        String token =null;
        token = request.getHeader("Authorization");
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }
        if (null == token || token.isEmpty() || !JwtUtils.verify(token)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            ObjectMapper objectMapper = new ObjectMapper();
            if (!(null == token || token.isEmpty())) {
                response.getWriter().println(objectMapper.writeValueAsString(CommonResult.tokenWrong()));
            } else {
                response.getWriter().println(objectMapper.writeValueAsString(CommonResult.tokenNull()));
            }
            return false;
        }
        TokenModel tokenModel = JwtUtils.getTokenModelByToken(token);

        String tokenFromRedis = (String) redisTemplate.opsForValue().get(RedisFlags.TOKEN_PREFIX+tokenModel.getEmail());
        if (tokenFromRedis == null || !tokenFromRedis.equals(token))   {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json); charset=utf-8");
            ObjectMapper objectMapper = new ObjectMapper();
            response.getWriter().println(objectMapper.writeValueAsString(CommonResult.fail("token错误")));
            return false;
        }
        TokenHandler.setTokenModelThreadLocal(tokenModel);
        Role role = handlerMethod.getMethodAnnotation(Role.class);
//        if(role == null){
//            return true;
//        }
//        String[] identities = role.identities();
//        for (String identity : identities) {
//            if (identity.equals(tokenModel.getRole())) {
//                TokenHandler.setTokenModelThreadLocal(tokenModel);
//                return true;
//            }
//        }
//        prepareResponse(response, CommonResult.tokenWrong());
        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {
        // 清除 ThreadLocal 中的数据
        TokenHandler.remove();
    }




    private void prepareResponse(HttpServletResponse response, Object responseData) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().println(objectMapper.writeValueAsString(responseData));
    }
}

