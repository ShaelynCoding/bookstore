package action;


import Dao.userDAO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;


/**
 * Created by lyn on 16-3-30.
 */
@Stateless(name = "LoginEJB")
public class LoginBean implements Login {
    @PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;
    public LoginBean() {
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
        if(name.equals("admin")&&password.equals("admin"))
            return "admin";
        else if(Login(name,password)) return "user";
        else return "guest";
    }
}
