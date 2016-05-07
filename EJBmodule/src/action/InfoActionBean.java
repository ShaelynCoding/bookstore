package action;

import Dao.InfoDAO;
import Dao.userDAO;
import entity.Information;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        String out= JSONObject.fromObject(informations,exclude).toString();
        return out;
    }
}
