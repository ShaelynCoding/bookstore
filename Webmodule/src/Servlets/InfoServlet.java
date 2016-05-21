package Servlets;

import action.InfoAction;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by lyn on 16-5-7.
 */
@WebServlet("/infoaction")
public class InfoServlet extends HttpServlet{
    @EJB(name="InfoActionEJB")
    private InfoAction infoAction;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try{
            String op=request.getParameter("operation");
            PrintWriter writer=response.getWriter();
            switch (op)
            {
                case "showData":{
                    String username=request.getParameter("username");
                    String out=infoAction.showInfo(username);
                    writer.print(out);
                    break;
                }
                case "showStatic":{
                    String username=request.getParameter("username");
                    String beginTime=request.getParameter("beginTime");
                    String endTime=request.getParameter("endTime");
                    String bookType=request.getParameter("bookType");
                    String out=infoAction.showStatic(username,beginTime,endTime,bookType);
                    writer.print(out);
                    break;
                }

                default:
                    break;
            }
            writer.flush();
            writer.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response){

    }

}
