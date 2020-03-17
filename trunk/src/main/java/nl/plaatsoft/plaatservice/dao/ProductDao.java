package nl.plaatsoft.plaatservice.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    public List<Product> findAll() {
        return entityManager.createQuery("from Product").getResultList();
    }
   
    /**
     * Find by name.
     *
     * @param name the name
     * @param version the version
     * @param os the os
     * @return the list
     */
    public Optional<Product> findByName(String name, String version, String os) {
    	
    	 try {    		     	
    		 Product product = entityManager.createQuery("SELECT a FROM Product a WHERE a.name=:name AND a.version=:version AND a.os=:os", Product.class)
                .setParameter("name", name)
                .setParameter("version", version)
                .setParameter("os", os)
                .getSingleResult();
   		
    		 return Optional.of(product);
    		 
    	 } catch (Exception e) {
    		 
    		 // Not found, create it!
    		 Product product = new Product(name, version, os);
    		 return save(product);
    	 }    	
    }
    
    /**
     * Save.
     *
     * @param Product the product
     * @return the optional
     */
    public Optional<Product> save(Product product) {
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
     *
     * @param entityType the entity type
     */
    public void truncate (Class<Product> entityType) {
    	String query = new StringBuilder("DELETE FROM ").append(entityType.getSimpleName()).append(" e").toString();
    	entityManager.createNativeQuery(query);
    }
}