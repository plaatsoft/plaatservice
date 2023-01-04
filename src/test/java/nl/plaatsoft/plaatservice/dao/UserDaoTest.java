package nl.plaatsoft.plaatservice.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

import nl.plaatsoft.plaatservice.domain.model.MUser;

/**
 * The Class ProductDaoTest.
 * 
 * @author wplaat
 */
public class UserDaoTest extends GeneralDaoTest  {

	/**
	 * Find By Id.
	 */
	@Test
	public void findById() {
		        				
		MUser user1 = userDao.save(new MUser("127.0.0.1", "lplaat", "leo", "Netherlands", "Gouda")).get();              
		assertEquals(1, user1.getUid());    
		
		MUser user2 = userDao.save(new MUser("127.0.0.1", "bplaat", "leo", "Netherlands", "Gouda")).get();              
		assertEquals(2, user2.getUid());    
		
        MUser user3 = userDao.findById(1).get();        
        assertEquals(1, user3.getUid());        
	}
	
	/**
	 * Find All.
	 */
	@Test
	public void findAll() {
		        				
		userDao.save(new MUser("127.0.0.1", "wplaat", "willie", "Netherlands", "Gouda"));
		userDao.save(new MUser("127.0.0.1", "bplaat", "bassie", "Netherlands", "Gouda"));
		userDao.save(new MUser("127.0.0.1", "lplaat", "leo", "Netherlands", "Gouda"));
               
        List<MUser> users = userDao.findAll();
        
        assertEquals(3, users.size());        
	}
		
	/**
	 * Find existing user
	 */
	@Test
	public void findExistingUser() {
		    	        	        
		MUser user1 = userDao.save(new MUser("127.0.0.1", "wplaat", "willie", "Netherlands", "Gouda")).get();
		MUser user2 = userDao.save(new MUser("127.0.0.1", "bplaat", "bassie", "Netherlands", "Gouda")).get();
	               
		assertEquals(1, user1.getUid());
		assertEquals(2, user2.getUid());  
		
	    Optional<MUser> user =  userDao.findByName("127.0.0.1", "wplaat", "willie", "Netherlands", "Gouda");
	    
	    assertEquals(user1.getUid(), user.get().getUid());   
	    
	    List<MUser> users = userDao.findAll();
        
        assertEquals(2, users.size());      
	}
		
	/**
	 * Find new user
	 */
	@Test
	public void findNewUser() {
		    	        	        
		MUser user1 = userDao.save(new MUser("127.0.0.1", "wplaat", "willie", "Netherlands", "Gouda")).get();
		MUser user2 = userDao.save(new MUser("127.0.0.1", "bplaat", "bassie", "Netherlands", "Gouda")).get();
	               
		assertEquals(1, user1.getUid());
		assertEquals(2, user2.getUid()); 
	               
	    // New entry is created
		Optional<MUser> user =  userDao.findByName("127.0.0.1", "wplaat1", "willie", "Netherlands", "Gouda");   
	    
	    assertTrue(user.isPresent()==true);   
	    assertEquals(3, user.get().getUid());   
	    
	    List<MUser> users = userDao.findAll();
        
        assertEquals(3, users.size());      
	}
	
	/**
	 * Find existing user
	 */
	@Test
	public void findbyNameError() {
		    	        	        
		userDao.save(new MUser("127.0.0.1", "wplaat", "willie", "Netherlands", "Gouda"));
		userDao.save(new MUser("127.0.0.1", "wplaat", "willie", "Netherlands", "Gouda"));
	               		
	    Optional<MUser> user =  userDao.findByName("127.0.0.1", "wplaat", "willie", "Netherlands", "Gouda");
	    
	    assertTrue(user.isPresent()==false);       
	}
}
