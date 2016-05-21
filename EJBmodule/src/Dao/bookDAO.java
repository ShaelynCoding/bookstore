package Dao;

import entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by lyn on 16-4-30.
 */
public class bookDAO {
    @PersistenceContext(unitName = "JPADB")
    private static EntityManager entityManager;
    public bookDAO() {

    }
    public static void setEntity(EntityManager entity)
    {
        entityManager=entity;
    }
    public static List<Book> getBooks()
    {

        String jbql="select b from book as b";
        Query query=entityManager.createQuery(jbql);
        List<Book> outline=query.getResultList();
        //Book r=outline.get(0);
        return outline;

    }
    public static List<Book> getBySearch(String choice,String search)
    {
        String sql="";
        switch (choice)
        {
            case "all":
               sql="select b from book as b where b.bookIsdn like '%"+search+"%' or b.bookAuth like '%"
                       +search+"%' or b.bookName like '%"+search+"%' or b.bookType like '%"+search+"%'";
                break;
            case "Isbn":
                sql="select b from book as b where b.bookIsdn like '%"+search+"%'";
                break;
            case "name":
                sql="select b from book as b where b.bookName like '%"+search+"%'";
                break;
            case "auth":
                sql="select b from book as b where b.bookAuth like '%"+search+"%'";
                break;
            case "type":
                sql="select b from book as b where b.bookType like '%"+search+"%'";
                break;
            default:
                break;
        }
        Query query=entityManager.createQuery(sql);
        List<Book> books=query.getResultList();
        return books;

    }

    public static Book getByISBN(String id)
    {
        String jbql="select b from book as b where b.bookIsdn='"+id+"'";
        Query query=entityManager.createQuery(jbql);
        List<Book> book = query.getResultList();
        return book.get(0);


    }
    public static Integer getRemain(String bookId)
    {
        String sql="select b.bookNum from book as b where b.bookISDN='"+bookId+"'";
        Query query=entityManager.createNativeQuery(sql);
        Integer num=(Integer)query.getSingleResult();
        return num;
    }
    public static boolean modiRemain(String bookId,Integer newNum) {
        try {
            String sql = "update book set bookNum=? where bookISDN=?";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1, newNum);
            query.setParameter(2, bookId);
            query.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void addBook(String id,String name,String auth,String type,Integer num,Double price)
    {
        String sql="insert into book (bookISDN,bookName,bookAuth,bookNum,bookPrice,bookType) values (?,?,?,?,?,?)";
        Query query=entityManager.createNativeQuery(sql);
        query.setParameter(1,id);
        query.setParameter(2,name);
        query.setParameter(3,auth);
        query.setParameter(4,num);
        query.setParameter(5,price);
        query.setParameter(6,type);
        query.executeUpdate();
    }
    public static List<Book> queryBook(String bookISDN,String bookName)
    {
        String hql="";
        if( bookISDN.equals("") && bookName.equals("") )
        {
            System.out.println("1");
            hql = "select b from book as b ";
        }
        else if(bookISDN.equals(""))
        {
            System.out.println("2");
            hql = "select b from book b WHERE b.bookName like '%"+bookName+"%'";
        }
        else if(bookName.equals(""))
        {
            System.out.println("3");
            hql = "select b from book b WHERE b.bookIsdn='"+bookISDN+"'";
        }
        else
        {
            System.out.println("4");
            hql  = "select b from book b WHERE b.bookName like '%"+bookName+"%' and b.bookIsdn ='"+bookISDN+"'";
        }
        Query query=entityManager.createQuery(hql);
        List<Book> res=query.getResultList();
        return res;

    }
    public static void deleteBook(String id)
    {
        String sql="delete from book where bookISDN='"+id+"'";
        Query query=entityManager.createNativeQuery(sql);
        query.executeUpdate();
    }
    public static void modiBook(String id,Double price,Integer num,String type)
    {
        String sql="update book set bookPrice=?,bookNum=?,bookType=? where bookISDN=?";
        Query query=entityManager.createNativeQuery(sql);
        query.setParameter(1,price);
        query.setParameter(2,num);
        query.setParameter(3,type);
        query.setParameter(4,id);
        query.executeUpdate();
    }
}
