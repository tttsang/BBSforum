package com.jking.website.config;


import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.stereotype.Component;
import javax.websocket.server.PathParam;
import org.springframework.web.socket.server.standard.SpringConfigurator;
import javax.websocket.*;

import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint(value = "/socketserver/{uid}",configurator = MyEndpointConfigure.class)
@Component
public class WebSocketServer {
 private Session session;
 private static Map<String,Session>sessionPool=new HashMap<>();
 private static Map<String,String>sessionId=new HashMap<>();

 @OnOpen
    public void onOpen(Session session, @PathParam("uid") String uid)
 {
     this.session=session;
     sessionPool.put(uid,session);
     sessionId.put(session.getId(),uid);
     System.out.println("用户"+uid+"已加入");
 }

 @OnMessage
    public void onMessage(String message)
 {
     System.out.println("当前用户session—id："+session.getId()+"发送信息"+message);
 }

// @OnError
//    public void onError(Session session,Error error)
// {
//     error.printStackTrace();
//     sessionPool.remove(sessionId.get(session.getId()));
//     sessionId.remove(session.getId());
// }

 @OnClose
    public  void onClose()
 {      System.out.println("当前用户session—id："+session.getId()+"已退出");
     sessionPool.remove(sessionId.get(session.getId()));
     sessionId.remove(session.getId());
 }

 public static void sentMessage(String message,String uid)
 { Session s=sessionPool.get(uid);
     if (s!=null)
     {
         try {
             s.getBasicRemote().sendText(message);
         }
         catch (IOException e)
         {
             e.printStackTrace();
         }
     }
 }

  public static int count()
 {
     return sessionPool.size();
 }

  public static String getAllUsers()
  {
      StringBuffer users=new StringBuffer();
      for (String key:sessionId.keySet())
      {
          users.append(sessionId.get(key)+",");
      }
      return users.toString();
  }

  public static void SendAll(String message)
  {
      for(String key:sessionId.keySet())
      {
          sentMessage(message,sessionId.get(key));
      }
  }
}
