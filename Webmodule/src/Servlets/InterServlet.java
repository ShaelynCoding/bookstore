package Servlets;

import net.sf.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by lyn on 16-5-18.
 */
@WebServlet("/International")
public class InterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {

            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain");
            String op = request.getParameter("operation");
            PrintWriter writer = response.getWriter();
            switch (op) {
                case "switchLanguage":
                {
                    Locale locale = null;
                    String lang = request.getParameter("Language");
                    if (lang.equals("zh"))
                        locale = new Locale("zh", "CN");
                    else if (lang.equals("en"))
                        locale = new Locale("en", "US");
                    ResourceBundle rb = ResourceBundle.getBundle("Resource/resource", locale);
                    if (rb == null || rb.keySet().isEmpty())
                        throw new Exception("Language Error");
                    JSONObject json = new JSONObject();
                    for (String key : rb.keySet()) {
                        json.put(key, rb.getString(key));
                    }
                    String tmp = json.toString();
                    writer.print(tmp);

                }

            }
            writer.flush();
            writer.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {

    }
}
