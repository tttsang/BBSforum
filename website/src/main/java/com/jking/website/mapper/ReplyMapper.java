package com.jking.website.mapper;

import com.jking.website.entity.Reply;
import org.apache.ibatis.annotations.Param;


import java.util.ArrayList;

public interface ReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKey(Reply record);

    ArrayList<Reply> showReply(@Param("id") Integer id, @Param("page") Integer page);

    int totalReply(@Param("pid") Integer pid);

    ArrayList<Reply> showNewReply(@Param("id") Integer id, @Param("page") Integer page);
}