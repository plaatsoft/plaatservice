package nl.plaatsoft.plaatservice.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nl.plaatsoft.plaatservice.model.MProduct;
import nl.plaatsoft.plaatservice.model.MScore;
import nl.plaatsoft.plaatservice.model.MUser;

/**
 * The Class ScoreDao.
 * 
 * @author wplaat
 */
public class ScoreDao {

	private static final Logger log = LogManager.getLogger( ScoreDao.class);
	
    private EntityManager entityManager;
    
    public ScoreDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
        
	public List<MScore> findAll() {
        return entityManager.createQuery("from MScore").getResultList();
    }
   
    public List<MScore> findByUserScore(MUser user, MProduct product) {
    	
    	 return entityManager.createQuery("SELECT a FROM MScore a WHERE a.user=:user AND a.product=:product ORDER BY a.score desc", MScore.class)
                .setParameter("user", user)
                .setParameter("product", product)
                .setMaxResults(15)
                .getResultList();
    }
    
    public List<MScore> findByTopScore(MProduct product) {
    	
    	 return entityManager.createQuery("SELECT a FROM MScore a WHERE a.product=:product ORDER BY a.score desc", MScore.class)
                .setParameter("product", product)
                .setMaxResults(15)
                .getResultList();
    }
    
    public Optional<MScore> save(MScore score) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(score);
            entityManager.getTransaction().commit();
            return Optional.of(score);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }
    
    public void truncate() {
    	List<MScore> scores = findAll();
    	Iterator<MScore> iter = scores.iterator();
 	    while (iter.hasNext()) {
 	    	MScore score = iter.next();
 	    	entityManager.remove(score); 
 	    }
     }
}