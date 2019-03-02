package com.jking.website.service;

import java.util.Map;

public interface Post {
    Map<String,Object> index(int page);
    com.jking.website.entity.Post content(int uid);
    Map<String,Object>Search(String title,Integer page);
    void post(com.jking.website.entity.Post post, String phonenum);
}
