package action;

import javax.ejb.Remote;

/**
 * Created by lyn on 16-3-30.
 */

@Remote
public interface Login {

    boolean Login(String name, String password);
    String getRole(String name, String password);
}
