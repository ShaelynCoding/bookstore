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
    public static void modiRemain(String bookId,Integer newNum)
    {

        String sql="update book set bookNum=? where bookISDN=?";
        Query query=entityManager.createNativeQuery(sql);
        query.setParameter(1,newNum);
        query.setParameter(2,bookId);
        query.executeUpdate();

    }

}
