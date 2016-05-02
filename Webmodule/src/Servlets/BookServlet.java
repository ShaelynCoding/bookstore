package Servlets;

import action.BookAction;
import entity.Book;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;


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

    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{

    }

}
