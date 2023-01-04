package nl.plaatsoft.plaatservice.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

import nl.plaatsoft.plaatservice.domain.model.MProduct;

/**
 * The Class ProductDaoTest.
 * 
 * @author wplaat
 */
public class ProductDaoTest extends GeneralDaoTest {
	
	/**
	 * Find By Id.
	 */
	@Test
	public void findById() {
		        						
		productDao.save(new MProduct("PlaatService", "0.1.0", "Windows10"));
               
        MProduct product = productDao.findById(1).get();
        
        assertEquals(1, product.getPid());        
	}
	
	
	/**
	 * Find All.
	 */
	@Test
	public void findAll() {
		        		
        productDao.save(new MProduct("PlaatService", "0.2.0", "Windows10"));
        productDao.save(new MProduct("PlaatService", "0.3.0", "Windows10"));
        productDao.save(new MProduct("PlaatService", "0.4.0", "Windows10"));
               
        List<MProduct> products = productDao.findAll();
        
        assertEquals(3, products.size());        
	}
		
	/**
	 * Find by name 1.
	 */
	@Test
	public void findByName1() {
		    	        	   		
	    productDao.save(new MProduct("PlaatService", "0.5.0", "Windows10"));
	    productDao.save(new MProduct("PlaatService", "0.6.0", "Windows10"));
	               
	    Optional<MProduct> product =  productDao.findByName("PlaatService", "0.7.0", "Windows10");
	    assertEquals("0.7.0", product.get().getVersion());   
	    
	    List<MProduct> products = productDao.findAll();        
        assertEquals(3, products.size());      
	}
		
	/**
	 * Find by name 2
	 */
	@Test
	public void findByName2() {
		    	        	        		
	    productDao.save(new MProduct("PlaatService", "1.3.0", "Windows10"));
	    productDao.save(new MProduct("PlaatService", "1.4.0", "Windows10"));
	               
	    // New entry is created
	    Optional<MProduct> product =  productDao.findByName("PlaatService", "1.4.0", "Windows10");
	    assertTrue(product.isPresent()==true);   
	    assertEquals("1.4.0", product.get().getVersion().toString());
	    
	    List<MProduct> products = productDao.findAll();        
        assertEquals(2, products.size());      
	}
		
	/**
	 * Find by name 3
	 */
	@Test
	public void findByName3() {
			        	        	               
        Optional<MProduct> product =  productDao.findByName("PlaatService", "1.5.0", "Windows10");
        assertEquals("1.5.0", product.get().getVersion().toString());   
	}
		
	/**
	 * Find by name 4.
	 */
	@Test
	public void findByName4() {
		    	 		
	    productDao.save(new MProduct("PlaatService", "0.7.0", "Windows10"));
	    productDao.save(new MProduct("PlaatService", "0.8.0", "Windows10"));
	    productDao.save(new MProduct("PlaatService", "0.9.0", "Windows10"));
	    productDao.save(new MProduct("PlaatService", "1.0.0", "Windows10"));
	    productDao.save(new MProduct("PlaatService", "1.1.0", "Windows10"));
	    productDao.save(new MProduct("PlaatService", "1.2.0", "Windows10"));
	               
	    Optional<MProduct> product =  productDao.findByName("PlaatService");
	    assertEquals("1.2.0", product.get().getVersion());   
	}
	
	/**
	 * Find by name Error.
	 */
	@Test
	public void findByNameError() {
		    	        	   		
	    productDao.save(new MProduct("PlaatService", "0.5.0", "Windows10"));
	    productDao.save(new MProduct("PlaatService", "0.5.0", "Windows10"));
	               
	    Optional<MProduct> product =  productDao.findByName("PlaatService", "0.5.0", "Windows10");
	    assertTrue(product.isPresent()==false);     
	}

}
