package action;

import Dao.bookDAO;
import entity.Book;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by lyn on 16-4-30.
 */
@Stateless(name = "BookActionEJB")
public class BookActionBean implements BookAction{
    @PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;
    public BookActionBean() {
    }

    public String getBookInfo()
    {
        String out="";
        bookDAO.setEntity(entityManager);
        List<Book> books = bookDAO.getBooks();
        JsonConfig exclude=new JsonConfig();
        out= JSONArray.fromObject(books,exclude).toString();
        return out;

    }
    public String getBookDetail(String id)
    {

        String out="";
        bookDAO.setEntity(entityManager);
        Book b=bookDAO.getByISBN(id);
        JsonConfig exclude=new JsonConfig();
        out= JSONObject.fromObject(b,exclude).toString();
        return out;
    }


}
