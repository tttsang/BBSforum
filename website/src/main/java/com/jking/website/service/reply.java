package com.jking.website.service;

import com.jking.website.entity.Reply;

import java.util.Map;

public interface reply {
    Map<String,Object> reply(Integer id,Integer page);

    void answer(Reply reply);

    Map<String,Object>newReply(Integer id,Integer page);

}
