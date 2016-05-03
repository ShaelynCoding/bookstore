package action;

import javax.ejb.Remote;

/**
 * Created by lyn on 16-3-30.
 */

@Remote
public interface UserAction {

    boolean Login(String name, String password);
    String getRole(String name, String password);
    boolean Register(String name,String password, String email);
    boolean modiPassword(String name,String oldP,String newP);
    String queryUser(String name);
    void delUser(Integer id);
}
