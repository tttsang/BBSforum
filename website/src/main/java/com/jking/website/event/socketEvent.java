package com.jking.website.event;

import com.jking.website.entity.Post;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;



public class socketEvent extends ApplicationEvent {

    private Post post;

    public socketEvent(Object source,Post post)
    {
        super(source);
        this.post=post;
      //  System.out.println("事件已初始化");
    }
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
