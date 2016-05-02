package Dao;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.PreparedStatement;

/**
 * Created by lyn on 16-5-1.
 */
public class InfoDAO {
    @PersistenceContext(unitName = "JPADB")
    private static EntityManager entityManager;

    public InfoDAO() {
    }

    public static void setEntityManager(EntityManager entity) {
        entityManager = entity;
    }

    public static void addInfo(Integer userid, String id, Integer num) {
        String sql = "Insert into Information (userId,bookISBN,buyNum) values (?,?,?)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, userid);
        query.setParameter(2, id);
        query.setParameter(3, num);
        query.executeUpdate();

    }
}