package action;

import Dao.InfoDAO;
import Dao.userDAO;
import entity.Information;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by lyn on 16-5-2.
 */
@Stateless(name = "InfoActionEJB")
public class InfoActionBean implements InfoAction{
    @PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;
    public InfoActionBean() {
    }
    public String showInfo(String username)
    {
        userDAO.setEntity(entityManager);
        InfoDAO.setEntityManager(entityManager);
        Integer userid= userDAO.getId(username);
        List<Information> informations= InfoDAO.getUserInfo(userid);
        JsonConfig exclude=new JsonConfig();
        String out= JSONArray.fromObject(informations,exclude).toString();
        return out;
    }
    public String dataByTime(String begin,String end)
    {
        InfoDAO.setEntityManager(entityManager);
        List<Information> informations=InfoDAO.getTimeInfo(begin, end);
        JsonConfig exclude=new JsonConfig();
        String out= JSONArray.fromObject(informations,exclude).toString();
        return out;

    }
    public String showStatic(String username,String begin,String end,String bookType)
    {
        InfoDAO.setEntityManager(entityManager);
        List<Information> informations=InfoDAO.getStatic(username,begin,end,bookType);

        JsonConfig exclude=new JsonConfig();
        String out= JSONArray.fromObject(informations,exclude).toString();
        return out;

    }

}
