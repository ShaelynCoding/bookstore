package cache;

import redis.clients.jedis.Jedis;

/**
 * Created by lyn on 16-5-20.
 */
public class cacheManager {
    Jedis jedis=null;
    public cacheManager()
    {
        try
        {
            jedis=new Jedis("localhost");

        }catch (Exception e)
        {
            jedis=null;
            e.printStackTrace();
        }
    }

    public void set(String key,String value)
    {

        jedis.set(key,value);
    }

    public String get(String key)
    {
        String temp=jedis.get(key);
        return temp;
    }

    public void clear()
    {
        jedis.flushDB();
    }

}
