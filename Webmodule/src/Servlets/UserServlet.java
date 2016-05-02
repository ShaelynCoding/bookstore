package Servlets;

import action.Login;
import entity.User;


import javax.ejb.EJB;
import javax.ejb.EntityContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lyn on 16-3-30.
 */

@WebServlet("/Login")
public class UserServlet extends HttpServlet{
    @EJB(name="Login")
    private Login login;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost starting ");

        String name = request.getParameter("userName");
        String password = request.getParameter("userPwd");
        System.out.print("name: ");
        System.out.println(name);
        System.out.print("password: ");
        System.out.println(password);
        try {
            request.getSession(true);
            request.logout();
            request.login(name, password);
            request.getSession().setAttribute("username", name);
            System.out.println(request.getUserPrincipal());
            response.sendRedirect("bookstore.jsp");
        } catch (Exception e) {

            e.printStackTrace();
            response.getWriter().println("fail...");
        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get");


    }
}

