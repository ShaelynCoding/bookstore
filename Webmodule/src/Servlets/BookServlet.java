package Servlets;

import action.BookAction;
import entity.Book;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import javax.ejb.EJB;
import javax.management.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.Queue;


/**
 * Created by lyn on 16-3-30.
 */
@WebServlet("/bookaction")
public class BookServlet extends HttpServlet {
@EJB(name = "BookAction")
    private BookAction baction;

    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String op=request.getParameter("operation");
        PrintWriter writer=response.getWriter();
        if(op.equals("showBooks")){
            String out=baction.getBookInfo();

            System.out.println(out);
            writer.print(out);

        }
        else if(op.equals("showDetail")){

            String bookISBN=request.getParameter("bookISBN");
            System.out.println(bookISBN);
            String out=baction.getBookDetail(bookISBN);
            System.out.println(out);
            writer.print(out);
        }
        else if(op.equals("querybook"))
        {
            String bookISDN=request.getParameter("bookISDN");
            String bookName=request.getParameter("bookName");
            String out=baction.queryBook(bookISDN,bookName);
            writer.print(out);

        }


    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String op=request.getParameter("operation");
        if(op.equals("addbook"))
        {
            String id=request.getParameter("id");
            String name=request.getParameter("name");
            String auth=request.getParameter("auth");
            Integer num=Integer.parseInt(request.getParameter("num"));
            Double price=Double.parseDouble(request.getParameter("price"));
            String type=request.getParameter("type");
            baction.addBook(id,name,auth,type,num,price);
        }
        else if(op.equals("delBook"))
        {
            String id=request.getParameter("bookISDN");
            baction.delBook(id);
        }
        else if(op.equals("modiBook"))
        {
            String id=request.getParameter("bookid");
            Double price=Double.parseDouble(request.getParameter("price"));
            Integer num=Integer.parseInt(request.getParameter("num"));
            String type=request.getParameter("type");
            baction.modiBook(id,price,num,type);
        }

    }

}
