package com.jking.website.aspect;

import com.jking.website.constant.CookieConstant;
import com.jking.website.constant.RedisConstant;
import com.jking.website.enums.UserEnums;
import com.jking.website.exceptions.MyException;
import com.jking.website.utils.CookieUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
/**
 * 1. 要在状态码为200时才进入该页面
 */
public class LoginAspect {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Before("execution(public * com.jking.website.controller.*.*(..))")
    public void login()
    {
        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        HttpServletResponse response=attributes.getResponse();
        if(request.getMethod().equals("GET")||request.getServletPath().equals("/login")||request.getServletPath().equals("/vertify"))
        {
            return;
        }
        Cookie cookie= CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie==null)
        {
            throw new MyException(UserEnums.LOGIN_ERROR);
        }
        String redis_token=stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PRFIX,cookie.getValue()));
        if (StringUtils.isEmpty(redis_token)){
            throw new MyException(UserEnums.LOGIN_ERROR);
        }

    }
}
