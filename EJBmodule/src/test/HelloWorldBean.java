package test;

import entity.Book;
import entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by lyn on 16-3-29.
 */
@Stateless(name = "HelloWorldEJB")
public class HelloWorldBean implements HelloWorld{
    @PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;
    public HelloWorldBean() {

    }

    @Override
    public String sayHello() {
        return "Hello";
    }
}
