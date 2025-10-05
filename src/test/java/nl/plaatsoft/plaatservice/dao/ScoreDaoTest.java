package nl.plaatsoft.plaatservice.dao;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.Test;

import nl.plaatsoft.plaatservice.model.MProduct;
import nl.plaatsoft.plaatservice.model.MScore;
import nl.plaatsoft.plaatservice.model.MUser;


/**
 * The Class ScoreDaoTest.
 * 
 * @author wplaat
 */
public class ScoreDaoTest extends GeneralDaoTest  {

	/** The Constant log. */
	private static final Logger log = LogManager.getLogger( ScoreDaoTest.class);
	
	/**
	 * Find by user score.
	 */
	@Test
	public void findByUserScore() {
		
		MUser user1 = userDao.save(new MUser("127.0.0.1", "wplaat", "willie", "Netherlands", "Gouda")).get();
		
		MProduct product1 = productDao.save(new MProduct("PlaatService", "0.1.0", "Windows10")).get();
						
		MScore score1 = scoreDao.save(new MScore(user1, product1, 1, 10, 1)).get();		
		MScore score2 = scoreDao.save(new MScore(user1, product1, 2, 10, 1)).get();
	               
		// Check Generated id's
	    assertEquals(1, user1.getUid());
	    assertEquals(2, product1.getPid()); 
	    assertEquals(3, score1.getSid()); 
	    assertEquals(4, score2.getSid());
	    
	    List<MScore> scores =  scoreDao.findByUserScore(user1, product1);
	    
	    assertEquals(2, scores.size());   
	}
	
	/**
	 * Find by top score.
	 */
	@Test
	public void findByTopScore() {
		
		MUser user1 = userDao.save(new MUser("127.0.0.1", "wplaat", "willie", "Netherlands", "Gouda")).get();
		MUser user2 = userDao.save(new MUser("127.0.0.1", "bplaat", "bassie", "Netherlands", "Gouda")).get();
		MUser user3 = userDao.save(new MUser("127.0.0.1", "lplaat", "leo", "Netherlands", "Gouda")).get();
		
		MProduct product1 = productDao.save(new MProduct("PlaatService", "0.2.0", "Windows10")).get();
		MProduct product2 = productDao.save(new MProduct("PlaatService", "0.3.0", "Windows10")).get();
		   		
		scoreDao.save(new MScore(user1, product1, 1, 1, 1));
		scoreDao.save(new MScore(user1, product1, 2, 2, 1));
		scoreDao.save(new MScore(user1, product1, 2, 3, 1));
		scoreDao.save(new MScore(user2, product2, 2, 4, 2));
		scoreDao.save(new MScore(user2, product2, 2, 5, 3));
		scoreDao.save(new MScore(user2, product2, 2, 6, 4));
		scoreDao.save(new MScore(user3, product2, 2, 7, 5));
		scoreDao.save(new MScore(user3, product2, 2, 8, 6));
		scoreDao.save(new MScore(user3, product2, 2, 9, 7));
		scoreDao.save(new MScore(user3, product2, 2, 10, 8));
		scoreDao.save(new MScore(user3, product2, 2, 11, 9));
		scoreDao.save(new MScore(user3, product2, 2, 12, 10));
		scoreDao.save(new MScore(user3, product2, 2, 13, 11));
		scoreDao.save(new MScore(user3, product2, 2, 14, 12));
		scoreDao.save(new MScore(user3, product2, 2, 15, 13));
		scoreDao.save(new MScore(user3, product2, 2, 16, 13));
		scoreDao.save(new MScore(user3, product2, 2, 17, 13));
		scoreDao.save(new MScore(user3, product2, 2, 18, 13));
		
	               	            	              
	    List<MScore> scores =  scoreDao.findByTopScore(product2);
	    
	    Iterator<MScore> iter = scores.iterator();
	    while (iter.hasNext()) {
	    	MScore score = (MScore) iter.next();
	    	log.debug(score);
	    }
	    assertEquals(15, scores.size());   
	}
}
