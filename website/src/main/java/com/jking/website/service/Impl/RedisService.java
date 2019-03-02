package com.jking.website.service.Impl;

import com.jking.website.constant.RedisConstant;
import com.jking.website.service.Redis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService implements Redis{
@Autowired
   private StringRedisTemplate stringRedisTemplate;

@Override
public void set(String key, String value){
     stringRedisTemplate.opsForValue().set(key, value, RedisConstant.EXPIRE, TimeUnit.SECONDS);
 }
 @Override
public String get(String key) {
    String value=stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PRFIX,key));
    return value;
}

@Override
    public void setcode(String key,String value)
{
    stringRedisTemplate.opsForValue().set(key,value,RedisConstant.CODEEXPIRE,TimeUnit.SECONDS);
}

@Override
    public  String getcode(String key)
{
    return stringRedisTemplate.opsForValue().get(key);
}
}
