package nl.plaatsoft.plaatservice.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nl.plaatsoft.plaatservice.model.MUser;

/**
 * The Class UserDao.
 * 
 * @author wplaat
 */
public class UserDao {

	/** The Constant log. */
	private static final Logger log = LogManager.getLogger( UserDao.class);
	
    /** The entity manager. */
    private EntityManager entityManager;
    
    /**
     * Instantiates a new user dao.
     *
     * @param entityManager the entity manager
     */
    public UserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
        
    /**
     * Find by id.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<MUser> findById(long id) {
    	MUser user = entityManager.find(MUser.class, id);
        if (user != null) {
        	return Optional.of(user);
        } else {
        	return Optional.empty();
        }
    }
    /**
     * Find all.
     *
     * @return the list
     */
    @SuppressWarnings("unchecked")
	public List<MUser> findAll() {
        return entityManager.createQuery("from MUser").getResultList();
    }
   
    /**
     * Find by name.
     *
     * @param ip the ip
     * @param username the username
     * @return the optional
     */
     public Optional<MUser> findByName(String ip, String username) {
    	   	   	
    	 try {
    		 MUser user = entityManager.createQuery("SELECT a FROM MUser a WHERE a.username=:username AND a.ip=:ip", MUser.class)
               .setParameter("username", username)
               .setParameter("ip", ip).getSingleResult();
  		   		 
    		return Optional.of(user);
    	 } catch (Exception e) {
     		 return Optional.empty();
   		 }
     }
    
    
    /**
     * Find by name.
     *
     * @param ip the ip
     * @param username the username
     * @param nickname the nickname
     * @param country the country
     * @param city the city
     * @return the list
     */
    public Optional<MUser> findByName(String ip, String username, String nickname, String country, String city) {
    	
    	 try {    		     	
    		 MUser user = entityManager.createQuery("SELECT a FROM MUser a WHERE a.username=:username AND a.ip=:ip", MUser.class)
                .setParameter("username", username)
                .setParameter("ip", ip)
                .getSingleResult();
   		
    		 return Optional.of(user);
    		     		
    	 } catch (NonUniqueResultException e) {
    		 
    		 log.error(e.getMessage());
    		 
    		 return Optional.empty();
    		 
    	 } catch (NoResultException e) {
    		     		 
    		 log.warn(e.getMessage());
    	 
    		 // Not found, create it!
    		 MUser user = new MUser(ip, username, nickname, country, city);
    		 return save(user);
    	 }    		
    }
    
    /**
     * Save.
     *
     * @param user the user
     * @return the optional
     */
    public Optional<MUser> save(MUser user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return Optional.of(user);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }
    
    /**
     * Truncate.
     */
    public void truncate() {
    	List<MUser> users = findAll();
    	Iterator<MUser> iter = users.iterator();
 	    while (iter.hasNext()) {
 	    	MUser user = iter.next();
 	    	entityManager.remove(user); 
 	    }
     }
}