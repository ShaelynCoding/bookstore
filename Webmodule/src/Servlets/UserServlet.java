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
import java.security.Principal;

/**
 * Created by lyn on 16-3-30.
 */

@WebServlet("/useraction")
public class UserServlet extends HttpServlet{
    @EJB(name="UserAction")
    private UserAction userAction;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        String name=request.getParameter("username");
//        String password=request.getParameter("password");
//        request.getSession(true);
//        request.logout();
//        request.login(name, password);
//        String role="guest";
//        if(request.isUserInRole("admin")) {
//            role="admin";
//        }
//        else if(request.isUserInRole("user"))
//        {
//            role="user";
//        }
//        request.getSession().setAttribute("user", name+"@"+role);
//        if(role.equals("admin"))
//            response.sendRedirect("manage.jsp");
//        else response.sendRedirect("bookstore.jsp");
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String op=request.getParameter("operation");
        PrintWriter writer=response.getWriter();
        if(op.equals("login"))
        {
            String name=request.getParameter("username");
            String password=request.getParameter("password");
            request.getSession(true);
            request.logout();
            request.login(name, password);
            String role="guest";
            if(request.isUserInRole("admin")) {
                role="admin";
            }
            else if(request.isUserInRole("user"))
            {
                role="user";
            }
            request.getSession().setAttribute("user", name+"@"+role);
            writer.print(name+"@"+role);
            if(role.equals("admin"))
                response.sendRedirect("manage.jsp");
            else response.sendRedirect("bookstore.jsp");
        }
        else if(op.equals("register"))
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
        else if(op.equals("getUserName")) {
            String nameAndRole = (String) request.getSession().getAttribute("user");
            if (nameAndRole == "" || nameAndRole == null)
                writer.print("guest");
            else {
                String[] tmp = nameAndRole.split("@");

                writer.print(tmp[0]);
            }
        }
        else if(op.equals("cookieSet")){
            String out=(String)request.getSession().getAttribute("user");
            writer.println(out);
        }
        writer.flush();
        writer.close();

    }
}

