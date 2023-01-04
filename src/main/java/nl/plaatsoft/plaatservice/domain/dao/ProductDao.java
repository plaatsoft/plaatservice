package nl.plaatsoft.plaatservice.domain.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nl.plaatsoft.plaatservice.domain.model.MProduct;

/**
 * The Class ProductDao.
 * 
 * @author wplaat
 */
public class ProductDao {

	/** The Constant log. */
	private static final Logger log = LogManager.getLogger( ProductDao.class);
	
    /** The entity manager. */
    private EntityManager entityManager;
    
    /**
     * Instantiates a new product dao.
     *
     * @param entityManager the entity manager
     */
    public ProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
        
    /**
     * Find all.
     *
     * @return the list
     */
    @SuppressWarnings("unchecked")
	public List<MProduct> findAll() {
        return entityManager.createQuery("from MProduct").getResultList();
    }
   
    /**
     * Find by id.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<MProduct> findById(long id) {
    	MProduct product = entityManager.find(MProduct.class, id);
        if (product != null) {
        	return Optional.of(product);
        } else {
        	return Optional.empty();
        }
    }
        
    /**
     * Find by name.
     *
     * @param name the name
     * @param version the version
     * @param os the os
     * @return single
     */
    public Optional<MProduct> findByName(String name, String version, String os) {
    	
    	 try {    		     	
    		 MProduct product = entityManager.createQuery("SELECT a FROM MProduct a WHERE a.name=:name AND a.version=:version AND a.os=:os", MProduct.class)
                .setParameter("name", name)
                .setParameter("version", version)
                .setParameter("os", os)
                .getSingleResult();
   		
    		 return Optional.of(product);
    		 
    	 } catch (NonUniqueResultException e) {
    		 
    		 log.error(e.getMessage());
    		 
    		 return Optional.empty();
    		 
    	 } catch (NoResultException e) {
    		     		 
    		 log.warn(e.getMessage());
    	 
    		 // Not found, create it!
    		 MProduct product = new MProduct(name, version, os);
    		 return save(product);
    	 }    	
    }
    
    
    /**
     * Find by name.
     *
     * @param name the name
     * @return the single
     */
    public Optional<MProduct> findByName(String name) {
    	
    	 try {    		     	
    		 MProduct product = entityManager.createQuery("SELECT a FROM MProduct a WHERE a.name=:name ORDER BY a.version desc", MProduct.class)
                .setParameter("name", name)
                .setMaxResults(1)
                .getSingleResult();
   		
    		 return Optional.of(product);
    		     		 
    	 } catch (Exception e) {
     		 return Optional.empty();
    	 }    	
    }
    
    
    /**
     * Save.
     *
     * @param product the product
     * @return the optional
     */
    public Optional<MProduct> save(MProduct product) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
            return Optional.of(product);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }
    
    /**
     * Truncate.
     */
    public void truncate() {
    	List<MProduct> products = findAll();
    	Iterator<MProduct> iter = products.iterator();
 	    while (iter.hasNext()) {
 	    	MProduct product = iter.next();
 	    	entityManager.remove(product); 
 	    }
     }
}