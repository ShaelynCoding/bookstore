package Servlets;

import Dao.userDAO;
import action.Cart;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created by lyn on 16-3-30.
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @EJB(name = "Cart")
    private Cart cart;

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String op=request.getParameter("operation");
        PrintWriter writer=response.getWriter();
        if(op.equals("cartInit"))
        {


            cart.initialize();
        }
        else if(op.equals("buybook"))
        {
            String name=(String)request.getSession().getAttribute("username");
            Integer id=userDAO.getId(name);
            if(cart.buy(id))
                writer.print("购买成功");
            else writer.print("库存不足，购买失败");
        }

    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String op=request.getParameter("operation");
        PrintWriter writer=response.getWriter();
        if(op.equals("showCart"))
        {
            String res=cart.getCart();
            writer.print(res);

        }
        else if(op.equals("addCart"))
        {
            String bookid=request.getParameter("bookISBN");
            Integer num=Integer.parseInt(request.getParameter("buyNum"));
            if(cart.addBook(bookid,num))
                writer.print("success :)");
            else writer.print("fail :(");
        }
        else if(op.equals("delCart"))
        {
            String bookid=request.getParameter("bookid");
            if(cart.removeBook(bookid))
                writer.print("success!");
            else writer.print("fail :(");
        }
    }
}
