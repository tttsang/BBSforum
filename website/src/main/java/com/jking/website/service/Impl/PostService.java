package com.jking.website.service.Impl;

import com.jking.website.mapper.PostMapper;


import com.jking.website.entity.Post;
import com.jking.website.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService implements com.jking.website.service.Post {
@Autowired
    private PostMapper mapper;
@Autowired
    private UserMapper userMapper;

    private static  final int pagesize=6;
   public Map<String,Object> index(int page)
   {
      int pages=(page-1)*pagesize;
       List<Post> posts=mapper.index(pages);
       for (Post p:posts)
       {
          //SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           System.out.println(p.getTime());
           Date now=new Date();
           Date before=p.getTime();
           long diff=now.getTime()-before.getTime();
           long days=diff/(1000*60*60*24);
           long hours=(diff/(1000*60*60)-days*24);
           long minute=(diff/(1000*60)-days*24*60-hours*60);
      //   System.out.println(minute);
           String date=String.format("%d天%d时%d分前发帖",days,hours,minute);
          p.setDate(date);
       }
       int count=mapper.count();
       Map<String,Object>map=new LinkedHashMap<>();
       int totalPage=count/6;
       if (count%6!=0){
           totalPage++;
       }
       System.out.println(posts);
       map.put("post",posts);
       map.put("count",count);
       map.put("totalPage",totalPage);
       return  map;
   }
   @Override
   public Post content(int id)
   {
       Post content=mapper.showContent(id);
       System.out.println(content);
       return content;
   }
  @Override
    public Map<String,Object>Search(String title,Integer page)
  {   int pages=(page-1)*pagesize;
      List<Post>posts=mapper.findPost(title,pages);
      int count=mapper.countSearch(title);
      Map<String,Object>map=new LinkedHashMap<>();
      int totalPage=count/6;
      if (count%6!=0){
          totalPage++;
      }
      System.out.println(posts);
      System.out.println(count);
      map.put("result",posts);
      map.put("count",count);
      map.put("totalpage",totalPage);
      return map;
  }
  @Override
    public  void post(Post post,String phonenum)
  {
      int userId=userMapper.findUid(phonenum);
      post.setUseId(userId);
      mapper.insertSelective(post);
  }
}
