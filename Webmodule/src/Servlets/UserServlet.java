package Servlets;


import action.UserAction;



import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by lyn on 16-3-30.
 */

@WebServlet("/useraction")
public class UserServlet extends HttpServlet{
    @EJB(name="UserAction")
    private UserAction userAction;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost starting ");
        String name = request.getParameter("username");
        String password = request.getParameter("password");
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
        String op=request.getParameter("operation");
        PrintWriter writer=response.getWriter();
        if(op.equals("register"))
        {
            String name=request.getParameter("regName");
            String password=request.getParameter("regPwd");
            String email=request.getParameter("regEmail");
            if(userAction.Register(name,password,email))
                writer.print("注册成功");
            else writer.print("用户名已注册");
        }
        else if(op.equals("modiPwd"))
        {
            String name=request.getParameter("modiName");
            String password1=request.getParameter("oldPwd");
            String password2=request.getParameter("newPwd");
            if(userAction.modiPassword(name,password1,password2))
                writer.print("用户名或原密码错误");
            else writer.print("密码修改成功");
        }
        else if(op.equals("queryuser"))
        {
            String userName=request.getParameter("userName");
            writer.print(userAction.queryUser(userName));

        }
        else if(op.equals("delUser"))
        {
            Integer userid=Integer.parseInt(request.getParameter("userId"));
            userAction.delUser(userid);
        }

    }
}

