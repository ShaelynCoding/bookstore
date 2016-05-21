package action;

import Dao.bookDAO;
import cache.cacheManager;
import entity.Book;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyn on 16-4-30.
 */
@Stateless(name = "BookActionEJB")
public class BookActionBean implements BookAction{
    @PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;

    private static cacheManager cache=new cacheManager();

    public BookActionBean() {
    }

    public String getBookInfo()
    {
        String bookStr="";
        String tmp=cache.get("books");
        if(tmp==null)
        {
            bookDAO.setEntity(entityManager);
            List<Book> books = bookDAO.getBooks();
            JsonConfig exclude=new JsonConfig();
            bookStr= JSONArray.fromObject(books,exclude).toString();
            cache.set("books",bookStr);
        }
        else
        {
            System.out.println("hit books");
            bookStr=tmp;
        }

        return bookStr;

    }
    public String getBookByGuest(String[] types,List<Book> books)
    {
        bookDAO.setEntity(entityManager);
//        List<Book> books = bookDAO.getBooks();
        List<Book> guestBooks=new ArrayList<Book>();
        for(int i=0;i<books.size();i++)
        {
            for(int j=0;j<types.length;j++)
            {
                if(books.get(i).getBookType().matches(".*"+types[j]+".*"))
                {
                    guestBooks.add(books.get(i));
                    break;
                }

            }
        }
        JsonConfig exclude=new JsonConfig();
        String out= JSONArray.fromObject(guestBooks,exclude).toString();
        return out;

    }
    public String getBookByGuest(String[] types)
    {
        String tmp=cache.get("guestBooks");
        String bookStr="";
        if(tmp==null)
        {
            bookDAO.setEntity(entityManager);
            List<Book> books = bookDAO.getBooks();
            List<Book> guestBooks=new ArrayList<Book>();
            for(int i=0;i<books.size();i++)
            {
                for(int j=0;j<types.length;j++)
                {
                    if(books.get(i).getBookType().matches(".*"+types[j]+".*"))
                    {
                        guestBooks.add(books.get(i));
                        break;
                    }

                }
            }
            JsonConfig exclude=new JsonConfig();
            bookStr= JSONArray.fromObject(guestBooks,exclude).toString();
            cache.set("guestBooks",bookStr);
        }
        else
        {
            System.out.println("hit guestBooks");
            bookStr=tmp;

        }

        return bookStr;

    }
    public String getSearchBook(String choice,String search)
    {
        bookDAO.setEntity(entityManager);
        List<Book> books=bookDAO.getBySearch(choice,search);
        JsonConfig exclude=new JsonConfig();
        String out=JSONArray.fromObject(books,exclude).toString();
        return out;
    }
    public String getSearchBook(String choice,String search,String types[])
    {
        bookDAO.setEntity(entityManager);
        List<Book> books=bookDAO.getBySearch(choice,search);

        String out=getBookByGuest(types,books);
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
    public void addBook(String id,String name,String auth,String type,Integer num,Double price)
    {
        bookDAO.setEntity(entityManager);
        bookDAO.addBook(id,name,auth,type,num,price);
        cache.clear();
    }
    public String queryBook(String bookid,String name)
    {
        bookDAO.setEntity(entityManager);
        List<Book>books = bookDAO.queryBook(bookid,name);
        JsonConfig exclude=new JsonConfig();
        String out= JSONArray.fromObject(books,exclude).toString();
        return out;

    }
    public void delBook(String id)
    {
        bookDAO.setEntity(entityManager);
        bookDAO.deleteBook(id);
        cache.clear();
    }
    public void modiBook(String id,Double price,Integer num,String type)
    {
        bookDAO.setEntity(entityManager);
        bookDAO.modiBook(id,price,num,type);
        cache.clear();
    }

}
