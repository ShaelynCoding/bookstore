package test;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lyn on 16-3-30.
 */
@WebServlet("/testSecurity")
public class testSecurity extends HttpServlet{
    @EJB
    HelloWorld helloWorld;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("test security get");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("test security post");

        String user = request.getParameter("userName");
        String pwd = request.getParameter("userPwd");

        System.out.println(user);
        System.out.println(pwd);
        try
        {

            request.getSession(true);
            request.login(user, pwd);

            System.out.println(request.getUserPrincipal());

            response.sendRedirect("Hello");

        } catch (Exception e)
        {

            e.printStackTrace();
            response.getWriter().println("fail...");
        }

    }
}
