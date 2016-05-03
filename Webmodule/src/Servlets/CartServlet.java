package Servlets;

import Dao.userDAO;
import action.Cart;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.ejb.EJB;
import javax.ejb.Init;
import javax.jws.WebService;
import javax.naming.InitialContext;
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
    private void sendBuyMsg(String userName)
    {
        try {
            InitialContext context=new InitialContext();
            QueueConnectionFactory factory=(QueueConnectionFactory)context.lookup("ConnectionFactory");
            QueueConnection connection = factory.createQueueConnection();
            QueueSession session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
            Queue queue=(Queue)context.lookup("queue/myQueue");
            String cartMsg=cart.getCart();
            TextMessage msg=session.createTextMessage(userName+"@"+cartMsg);
            QueueSender sender=session.createSender(queue);
            sender.send(msg);
            session.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }





    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String op=request.getParameter("operation");
        PrintWriter writer=response.getWriter();
        if(op.equals("showCart"))
        {
            String res=cart.getCart();
            writer.print(res);

        }

        else if(op.equals("delCart"))
        {
            String bookid=request.getParameter("bookid");
            if(cart.removeBook(bookid))
                writer.print("success!");
            else writer.print("fail :(");
        }
        else if(op.equals("buybook"))
        {
            String name=(String)request.getParameter("username");
            sendBuyMsg(name);
            cart.clear();

        }
        else if(op.equals("addCart"))
        {
            String bookid=request.getParameter("bookISBN");
            Integer num=Integer.parseInt(request.getParameter("buyNum"));
            cart.addBook(bookid,num);


        }
        writer.flush();
        writer.close();
    }
}
