package webSocket;



import net.sf.json.JSONObject;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by lyn on 16-5-3.
 */
@ServerEndpoint("/chat")
public class ChatEndPoint {
    private static final Set<ChatEndPoint> connections=new CopyOnWriteArraySet<>();
    private Session session;
    private static String user;
    private  static String getUsers()
    {
        JSONObject object = new JSONObject();
        object.put("action","users");
        Set<String> tmp=new HashSet<>();
        for(ChatEndPoint client : connections)
        {
            tmp.add(client.user);
        }
        object.put("users",tmp.toArray());
        return object.toString();
    }
    private static void broadcast(String msg)
    {
        for(ChatEndPoint client:connections)
        {
            try {
                synchronized (client)
                {
                    if(client.session.isOpen())
                        client.session.getBasicRemote().sendText(msg);

                }
            }catch (IOException e)
            {
                System.out.println(client.user+" quit!");
                connections.remove(client);
                try
                {
                    client.session.close();
                }catch (Exception ee)
                {
                  ee.printStackTrace();
                }
                broadcast(getUsers());
                break;
            }
        }
    }
    @OnOpen
    public void start(Session session)
    {
        System.out.println("...............start...........");
        this.session=session;
        connections.add(this);
    }

    @OnMessage
    public void onMessage(String msg)
    {
        System.out.println("...............onMessage...........");
        try {
            JSONObject object=JSONObject.fromObject(msg);
            String action=(String)object.get("action");
            switch (action)
            {
                case "setUser":
                {
                    user = (String) object.get("username");
                    broadcast(getUsers());
                    break;
                }
                case "chat":
                {
                    JSONObject reply= new JSONObject();
                    reply.put("action","chat");
                    reply.put("user",user);
                    reply.put("text",(String)object.get("value"));
                    broadcast(reply.toString());
                    break;
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @OnClose
    public void end()
    {
        System.out.println("...............end...........");
        connections.remove(this);
        broadcast(getUsers());
    }
}

