package action;

import entity.Book;

import javax.ejb.Remote;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by lyn on 16-4-30.
 */
@Remote
public interface BookAction {

    String getBookInfo();
    String getBookDetail(String id);
    void addBook(String id,String name,String auth,String type,Integer num,Double price);
    String queryBook(String id,String name);
    void delBook(String bookid);
    void modiBook(String id,Double price,Integer num,String type);
}
