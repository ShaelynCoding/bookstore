package action;

import javax.ejb.Remote;
import java.util.HashMap;

/**
 * Created by lyn on 16-3-30.
 */
@Remote
public interface Cart {

    void initialize();
    boolean addBook(String bIsbn,Integer num);
    boolean removeBook(String bIsbn);
    void clear();
    boolean buy(Integer id);
    boolean buyByMsg(String name,String cartStr);
    String getCart();
}
