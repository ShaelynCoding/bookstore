package Dao;

import entity.Information;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lyn on 16-5-1.
 */
public class InfoDAO {
    @PersistenceContext(unitName = "JPADB")
    private static EntityManager entityManager;

    public InfoDAO() {
    }

    public static void setEntityManager(EntityManager entity) {
        entityManager = entity;
    }

    public static boolean addInfo(Integer userid, String id, Integer num) {
        try {
            Date dt = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String nowTime = df.format(dt);
            String sql = "Insert into Information (userId,bookISDN,buyNum,time) values (?,?,?,?)";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1, userid);
            query.setParameter(2, id);
            query.setParameter(3, num);
            query.setParameter(4, nowTime);
            query.executeUpdate();
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public static List<Information> getUserInfo(Integer userid){
        String sql="select info from Information as info where info.userId="+userid;
        Query query=entityManager.createQuery(sql);
        List<Information> informations=query.getResultList();
        return informations;


    }
    public static List<Information> getTimeInfo(String begin,String end)
    {
        String sql="select info from Information as info where info.time>= '"+end+"' and info.time <= '"+begin+"'";
        Query query=entityManager.createQuery(sql);
        List<Information> informations=query.getResultList();
        return informations;
    }
    public static List<Information> getStatic(String username,String begin,String end,String bookType)
    {
        String sql="select info from Information as info";
        String condition[]=new String[]{"","","",""};
        if(username!="")
        {
            userDAO.setEntity(entityManager);
            Integer userid=userDAO.getId(username);
            condition[0]="info.userId = "+userid;
        }
        if(begin!="")
        {
            condition[1]="info.time >= '"+begin+"'";
        }
        if(end!="")
        {
            condition[2]="info.time <= '"+end+"'";
        }
        if(bookType!="")
        {
            condition[3]="info.bookType= '"+bookType+"'";
        }
        String cond="";
        for(int i=0;i<4;i++)
        {
            if(condition[i]=="")
                continue;
            else {
                if(cond!="")
                    cond+=" and ";
                cond+=condition[i];
            }
        }
        if(cond!="")
            sql+=(" where "+cond);
        Query query=entityManager.createQuery(sql);
        List<Information> informations=query.getResultList();
        return informations;
    }
}