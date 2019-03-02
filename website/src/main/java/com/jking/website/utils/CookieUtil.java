package com.jking.website.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static void  set(HttpServletResponse response,String key,String value ,int maxAge)
    {
        Cookie cookie=new Cookie("token",value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);

    }

   public static Cookie get(HttpServletRequest request, String key)
   {
       Cookie [] cookies=request.getCookies();
       if (cookies ==null)
       {return null;}

       for (Cookie cookie:cookies)
       {
           if (cookie.getName()!=null&&cookie.getName().equals(key))
           {
               return cookie;
           }
       }
       return  null;
   }
}
