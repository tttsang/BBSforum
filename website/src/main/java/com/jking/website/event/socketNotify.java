package com.jking.website.event;

import com.jking.website.config.WebSocketServer;
import com.jking.website.entity.Post;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class socketNotify {
@EventListener
    public void event(socketEvent socketEvent)
{
    Post post=socketEvent.getPost();
    String message=post.getId()+"";
    String UseId=post.getUseId()+"";
    WebSocketServer.sentMessage(message,UseId);
}
}
