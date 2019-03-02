package com.jking.website.service.Impl;

import com.jking.website.entity.Post;
import com.jking.website.entity.Reply;
import com.jking.website.event.socketEvent;
import com.jking.website.mapper.PostMapper;
import com.jking.website.mapper.ReplyMapper;
import com.jking.website.service.reply;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReplyService implements reply ,ApplicationContextAware{
    @Autowired
    ReplyMapper replyMapper;
    @Autowired
    PostMapper postMapper;

    private ApplicationContext applicationContext;

    private static final int pagesize=6;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)throws BeansException
    {
        this.applicationContext=applicationContext;
    }
    @Override
    public Map<String,Object> reply(Integer id,Integer page)
    {   int pages=(page-1)*pagesize;
        List<Reply>replies=replyMapper.showReply(id,pages);
        int count=replyMapper.totalReply(id);
        Map<String,Object>map=new LinkedHashMap<>();
        int totalPage=count/6;
        if (count%6!=0){
            totalPage++;
        }
        map.put("reply",replies);
        map.put("count",count);
        map.put("totalpage",totalPage);
        return  map;
    }
   @Override
   public Map<String,Object>newReply(Integer id,Integer page)
   {    int pages=(page-1)*pagesize;
       Map<String,Object>map=new LinkedHashMap<>();
       int count=replyMapper.totalReply(id);
       int totalPage=count/6;
       if (count%6!=0){
           totalPage++;
       }
       List<Reply>replyList=replyMapper.showNewReply(id,pages);
       map.put("reply",replyList);
       map.put("count",count);
       map.put("totalpage",totalPage);
       return  map;
   }

    @Override
    @Transactional
    public void answer(Reply reply)
    {
        replyMapper.insertSelective(reply);
        Post post=postMapper.selectPoster(reply.getPostId());
        int useid=post.getUseId();
        System.out.println(useid);
        System.out.println("上传id和postid"+post.getUseId()+post.getId());
        applicationContext.publishEvent(new socketEvent(this,post));
    }
}
