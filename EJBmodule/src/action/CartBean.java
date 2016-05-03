package action;

import Dao.InfoDAO;
import Dao.bookDAO;
import Dao.userDAO;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

/**
 * Created by lyn on 16-3-30.
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateful(name = "CartEJB")
public class CartBean implements Cart {
    @PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;

    private static HashMap<String,Integer> contents=new HashMap<>();

    @Resource
    private SessionContext context;

    public CartBean() {

    }
    public void initialize(){

        contents = new HashMap<>();

    }

    public boolean addBook(String bIsbn,Integer num){
        Integer remain=bookDAO.getByISBN(bIsbn).getBookNum();


        if(num<=remain) {
            contents.put(bIsbn, num);
            return true;
        }
        return false;
    }

    public boolean removeBook(String bIsbn){


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
        bookDAO.setEntity(entityManager);
        InfoDAO.setEntityManager(entityManager);
        try
        {
            Iterator iter = contents.entrySet().iterator();
            while(iter.hasNext())
            {
                HashMap.Entry entry=(HashMap.Entry)iter.next();
                String ISBN=(String)entry.getKey();
                Integer num=(Integer)entry.getValue();
                Integer oldNum=bookDAO.getRemain(ISBN);
                if(oldNum>=num) {
                    if(!bookDAO.modiRemain(ISBN, oldNum - num) || !InfoDAO.addInfo(customerId, ISBN, num))
                        throw new Exception("bad deal");

                }
                else  return false;
            }
            contents.clear();
            return true;
        }catch (Exception e)
        {
            context.setRollbackOnly();
            e.printStackTrace();
            return false;
        }

    }
    public boolean buyByMsg(String name, String cartStr)
    {
        System.out.println("cart: "+cartStr);
        bookDAO.setEntity(entityManager);
        userDAO.setEntity(entityManager);
        InfoDAO.setEntityManager(entityManager);
        Integer id=userDAO.getId(name);
        JSONArray jsonArr=JSONArray.fromObject(cartStr);
        for(int i=0;i<jsonArr.size();i++)
        {
            String ISBN=jsonArr.getJSONObject(i).getString("bookISBN");
            Integer num=jsonArr.getJSONObject(i).getInt("buyNum");
            Integer oldNum=bookDAO.getRemain(ISBN);
            if(oldNum>=num) {
                bookDAO.modiRemain(ISBN, oldNum - num);
                InfoDAO.addInfo(id, ISBN, num);
            }
            else  return false;

        }
        //clear();
        return true;

    }
}
