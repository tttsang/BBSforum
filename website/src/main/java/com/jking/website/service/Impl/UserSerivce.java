package com.jking.website.service.Impl;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.jking.website.entity.User;
import com.jking.website.mapper.UserMapper;
import com.jking.website.service.user;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

@Service
public class UserSerivce implements user {
  @Autowired UserMapper userMapper;

    @Override
    @Transactional
    public User AddUser(String phonenum)
    {
        User user=new User();
        userMapper.AddUser(phonenum);
        int useId=userMapper.findUid(phonenum);
        user.setId(useId);
        user.setPhonenum(phonenum);
        return user;
    }

   @Override
  public String GetRandom()
    {
      Random random=new Random();
      String fourrandom=random.nextInt(10000)+"";
      int length=fourrandom.length();
      if(length<4)
      {
        for(int i=1;i<=4-length;i++)
        {
          fourrandom="0"+fourrandom;

        }
      }

      return fourrandom;
    }

    @Override
  public void  Verification(String number,String code) {
        System.out.println("收到的验证码"+number);
      int appid = 1400118125;
      String appkey = "1855d0ac43f0d5e8a870e1dae44cc34f";
      int templatedId = 155638;
      String smsSign = "小曾说事";
      try {
        ArrayList<String> params = new ArrayList<String>();
        params.add(code);
        params.add("1");
        SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
        SmsSingleSenderResult result = ssender.sendWithParam("86", number, templatedId, params, smsSign, "", "");
      } catch (HTTPException | JSONException | IOException e) {
        e.printStackTrace();
      }
    }
}
