package service;

import action.BookAction;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by lyn on 16-5-20.
 */

@WebService(
        name="BookServiceSOAP",
        targetNamespace = "service"
)
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class BookServiceSOAP {

    @EJB(name = "BookAction")
    private BookAction bookAction;

    @WebMethod(operationName = "showDetail",action="showDetail")
    public String showDetail(@WebParam(name="bookISBN") String bookISBN)
    {
        //System.out.println("BookServiceSOAP showDetail");
        String out=bookAction.getBookDetail(bookISBN);
        return out;
    }


}
