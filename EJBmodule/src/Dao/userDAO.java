
package Dao;


import entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by lyn on 16-4-30.
 */

public class userDAO{
    @PersistenceContext(unitName = "JPADB")
    private static  EntityManager entityManager;
    public userDAO() {

    }
    public static void setEntity(EntityManager entity)
    {
        entityManager=entity;
    }
    public static Integer getId(String name){
        String sql="select userId from user where userName='"+name+"'";
        Query query=entityManager.createNativeQuery(sql);
        List<Integer> userId=query.getResultList();
        if(!userId.isEmpty()){
            return userId.get(0);
        }
        return -1;
    }
    public static int checkLogin(String name, String pwd) throws ClassNotFoundException, SQLException {
        System.out.println("checkLogin.....");
        String sql = "select userPwd from user where userName = '" + name + "'";
        Query query = entityManager.createNativeQuery(sql);
        List<String> password = query.getResultList();
        System.out.println("");
        if (!password.isEmpty()) {
            if (pwd.equals(password.get(0))) {
                return 0;
            }
        }

        return 1;
    }
    public static boolean modiPwd(String name,String oldPwd,String newPwd)
    {
        String sql="select userPwd from user where userName='"+name+"'";
        Query query=entityManager.createNativeQuery(sql);
        List<String> password=query.getResultList();
        if(!password.isEmpty())
        {
            if(oldPwd.equals(password.get(0)))
            {
                sql="update user set userPwd=? where userName=?";
                query=entityManager.createNativeQuery(sql);
                query.setParameter(1,newPwd);
                query.setParameter(2,name);
                query.executeUpdate();
                return true;

            }
        }
        return false;
    }

    public static boolean addUser(String name,String pwd,String email)
    {
        String sql="select userId from user where userName='"+name+"'";
        Query query=entityManager.createNativeQuery(sql);
        List<Integer> res=query.getResultList();
        if(!res.isEmpty())
            return false;
        else
        {
            String ss ="insert into user (userName,userPwd,userEmail) values (?,?,?)";
            query=entityManager.createNativeQuery(ss);
            query.setParameter(1,name);
            query.setParameter(2,pwd);
            query.setParameter(3,email);
            query.executeUpdate();
            return true;
        }
    }
    public static User getUserByName(String name)
    {
        String sql="select u from user as u where u.userName='"+name+"'";
        Query query=entityManager.createQuery(sql);
        List<User> res=query.getResultList();
        if(!res.isEmpty())
        {
            return res.get(0);
        }
        return null;
    }
    public static void delUser(Integer id)
    {
        String sql="delete from user where userId='"+id.toString()+"'";
        Query query=entityManager.createNativeQuery(sql);
        query.executeUpdate();

    }
}



