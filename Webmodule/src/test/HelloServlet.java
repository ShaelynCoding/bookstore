package test;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lyn on 16-3-29.
 */
@WebServlet("/Hello")
public class HelloServlet extends HttpServlet {

    @EJB(name = "HelloWorld")
    private HelloWorld helloWorld;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // helloWorld.setUser();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println(helloWorld.sayHello());
        //helloWorld.setUser();
    }
}
