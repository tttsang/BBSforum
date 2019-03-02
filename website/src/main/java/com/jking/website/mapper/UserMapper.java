package com.jking.website.mapper;

import com.jking.website.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int AddUser(@Param("phonenum") String phonenum);

    int findUid(@Param("phonenum") String phoneum);

}