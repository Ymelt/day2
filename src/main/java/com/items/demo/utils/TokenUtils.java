package com.items.demo.utils;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.items.demo.entity.User;
import com.items.demo.service.UserService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class TokenUtils {

    private static UserService staticUserService;

    @Resource
    private UserService userService;

    @PostConstruct
    public void setUserService() {
        staticUserService = userService;
    }




    /**
     * 设置过期时间5分钟
     */
    private static final long EXPIRE_TIME = 10 * 60 * 1000;

    public static String getToken(User user){
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        String token;
        token = JWT.create().withAudience(String.valueOf(user.getId()),user.getName(),user.getRole())
                .withExpiresAt(date)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;

    }


    /**
     * 获取当前登录的用户信息
     *
     * @return user对象
     */
    public static User getCurrentUser() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("Authorization");
            if (StrUtil.isNotBlank(token)) {
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserService.getById(Integer.valueOf(userId));
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
