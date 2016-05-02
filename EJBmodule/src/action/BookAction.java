package action;

import entity.Book;

import javax.ejb.Remote;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by lyn on 16-4-30.
 */
@Remote
public interface BookAction {

    String getBookInfo();
    String getBookDetail(String id);

}
