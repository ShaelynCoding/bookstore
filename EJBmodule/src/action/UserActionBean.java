package action;


import Dao.userDAO;
import entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;


/**
 * Created by lyn on 16-3-30.
 */
@Stateless(name = "UserActionEJB")
public class UserActionBean implements UserAction {
    @PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;
    public UserActionBean() {
    }
    public boolean Login(String name, String password) {
        System.out.println("登录中");
        try {
            userDAO.setEntity(entityManager);
            int res = userDAO.checkLogin(name, password);

            if (res == 0) {
                System.out.println("登录成功");
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public  String getRole(String name,String password){
        userDAO.setEntity(entityManager);
        if(name.equals("admin")&&password.equals("admin"))
            return "admin";
        else if(Login(name,password)) return "user";
        else return "guest";
    }
    public boolean Register(String name,String password,String email)
    {
        userDAO.setEntity(entityManager);
        return userDAO.addUser(name,password,email);

    }
    public boolean modiPassword(String name,String oldP,String newP)
    {
        userDAO.setEntity(entityManager);
        return userDAO.modiPwd(name,oldP,newP);

    }
    public String queryUser(String name)
    {
        userDAO.setEntity(entityManager);
        User user=userDAO.getUserByName(name);
        JsonConfig exclude=new JsonConfig();
        String out= JSONObject.fromObject(user,exclude).toString();
        return out;
    }
    public void delUser(Integer id)
    {
        userDAO.setEntity(entityManager);
        userDAO.delUser(id);
    }

}
