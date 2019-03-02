package com.jking.website.controller;

import com.jking.website.VO.ResultVO;
import com.jking.website.constant.RedisConstant;
import com.jking.website.entity.User;
import com.jking.website.enums.UserEnums;
import com.jking.website.exceptions.MyException;
import com.jking.website.service.Impl.RedisService;
import com.jking.website.service.Impl.UserSerivce;
import com.jking.website.utils.CookieUtil;
import com.jking.website.utils.ResultVoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
@RestController
@CrossOrigin
public class UseController {
    @Autowired
    private UserSerivce userSerivce;
   @Autowired
   private RedisService redisService;
    @PostMapping(value = "/vertify")
    public ResultVO vertify(@RequestParam String phonenum)
    {
        String code =userSerivce.GetRandom();
        System.out.println(code);
     //  userSerivce.Verification(phonenum,code);
        redisService.setcode(phonenum,code);
        return ResultVoUtil.succees();
    }

    @PostMapping(value = "/login")
    public ResultVO login(@RequestParam String phonenum , @RequestParam String code ,HttpServletResponse response)
    {  System.out.println(phonenum+"a");
       System.out.println(code+"b");
       System.out.println(redisService.getcode(phonenum)+"c");
      if (code.equals(redisService.getcode(phonenum)))
      {
          String token = UUID.randomUUID().toString();
          Integer expire = RedisConstant.EXPIRE;
         User user= userSerivce.AddUser(phonenum);
          redisService.set(String.format(RedisConstant.TOKEN_PRFIX, token), phonenum);
          CookieUtil.set(response, RedisConstant.TOKEN_PRFIX, token, expire);
       //   response.setHeader("Access-Control-Allow-Credentials","true");
         // response.setHeader("Access-Control-Allow-Origin","http://www.shuangying.ac.cn");
          return ResultVoUtil.succees(user);
      }
      else {
          throw new MyException(UserEnums.VERTIFY_ERROR);
      }
    }


}
