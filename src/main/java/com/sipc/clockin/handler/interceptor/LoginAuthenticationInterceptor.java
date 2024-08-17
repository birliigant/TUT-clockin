package com.sipc.clockin.handler.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Component
@CrossOrigin
@AllArgsConstructor
public class LoginAuthenticationInterceptor implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        TokenModel tokenModel = TokenHandler.getTokenModelThreadLocal();
//        if (!(handler instanceof HandlerMethod handlerMethod)) {
//            return true;
//        }
//        Role role = handlerMethod.getMethodAnnotation(Role.class);
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
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    private void prepareResponse(HttpServletResponse response, Object responseData) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().println(objectMapper.writeValueAsString(responseData));
    }
}
