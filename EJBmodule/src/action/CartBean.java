package action;

import Dao.InfoDAO;
import Dao.bookDAO;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.ejb.*;
import java.util.*;

/**
 * Created by lyn on 16-3-30.
 */

@Stateful(name = "CartEJB")
public class CartBean implements Cart {

    private HashMap<String,Integer> contents;
    public CartBean() {

    }
    public void initialize(){

        contents = new HashMap<>();

    }

    public boolean addBook(String bIsbn,Integer num){
        Integer remain=bookDAO.getByISBN(bIsbn).getBookNum();
        if(contents==null) initialize();

        if(num<=remain) {
            contents.put(bIsbn, num);
            return true;
        }
        return false;
    }

    public boolean removeBook(String bIsbn){

        if(contents==null) initialize();
        if(contents.containsKey(bIsbn))
        {
            contents.remove(bIsbn);
            return true;
        }
        else return false;
    }
    public void clear(){
        contents.clear();
    }

    public String getCart()
    {
        if(contents==null) initialize();
        Map map=new HashMap<String,Object>() ;
        JSONArray jsonArr = new JSONArray();
        Iterator iter = contents.entrySet().iterator();
        while(iter.hasNext()) {
            HashMap.Entry entry=(HashMap.Entry)iter.next();
            String ISBN=(String)entry.getKey();
            Integer num=(Integer)entry.getValue();
            map.put("bookISBN",ISBN);
            map.put("bookName",bookDAO.getByISBN(ISBN).getBookName());
            map.put("buyNum",num);
            JSONObject jsonobject=JSONObject.fromObject(map);
            map.clear();
            jsonArr.add(jsonobject);
        }
        return jsonArr.toString();
    }
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public boolean buy(Integer customerId)
    {
        Iterator iter = contents.entrySet().iterator();

        while(iter.hasNext())
        {
            HashMap.Entry entry=(HashMap.Entry)iter.next();
            String ISBN=(String)entry.getKey();
            Integer num=(Integer)entry.getValue();
            Integer oldNum=bookDAO.getRemain(ISBN);
            if(oldNum>=num) {
                bookDAO.modiRemain(ISBN, oldNum - num);
                InfoDAO.addInfo(customerId, ISBN, num);

            }
            else  return false;
        }
        contents.clear();
        return true;

    }
}
