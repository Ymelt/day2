package com.items.demo.Interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.items.demo.common.Constants;
import com.items.demo.entity.User;
import com.items.demo.exception.ServiceException;
import com.items.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginTeacherInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String token = request.getHeader("Authorization");
        //如果不是映射到方法直接跳过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        String userId ;
        try {

            userId = JWT.decode(token).getAudience().get(0);
//            System.out.println(JWT.decode(token).getAudience().get(1));
        } catch (JWTDecodeException e) {
            throw new ServiceException(Constants.CODE_400,"token验证失败,请重新登录");
        }
        User user = userService.getById(userId);
        if(user == null){
            throw new ServiceException(Constants.CODE_400,"用户不存在");
        }
        //验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new ServiceException(Constants.CODE_400,"token验证失败,请重新登录");
//            response.setContentType("application/json;charset=utf-8");
//            response.getWriter().write(String.valueOf(new ServiceException(Constants.CODE_400,"token验证失败,请重新登录")));
        }
//        return JWT.decode(token).getAudience().get(2).equals("老师");
        if(!JWT.decode(token).getAudience().get(2).equals("老师")){
            throw new ServiceException(Constants.CODE_400,"不是教师账户登录");
        }
        return true;
    }
}
