package nl.plaatsoft.plaatservice.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class Score.
 * 
 * @author wplaat
 */
@Entity
@Table(name = "scores")
public class MScore {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long sid;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private MProduct product;

    @ManyToOne(fetch = FetchType.EAGER)
    private MUser user;

	private long dt;
	private long score;
	private long level;
	
	public MScore() {
	}
	  
	public MScore(MUser user, MProduct product, long dt, long score, long level) {
		super();
		this.user = user;
		this.product = product;		
		this.dt = dt;
		this.score = score;
		this.level = level;
	}
	
	@Override
	public String toString() {
		return "Score [sid=" + sid + ", user=" + user+ ", product=" + product + ", dt=" + dt + ", score=" + score + ", level="+ level + "]";
	}
	
	public long getSid() {
		return sid;
	}
	
	public void setId(Integer sid) {
		this.sid = sid;
	}
		
	public long getDt() {
		return dt;
	}

	public MProduct getProduct() {
		return product;
	}

	public void setProduct(MProduct product) {
		this.product = product;
	}

	public MUser getUser() {
		return user;
	}

	public void setUser(MUser user) { this.user = user; }

	public void setDt(long dt) {
		this.dt = dt;
	}
	
	public long getScore() {
		return score;
	}
	
	public void setScore(long score) {
		this.score = score;
	}
	
	public long getLevel() {
		return level;
	}
	
	public void setLevel(long level) {
		this.level = level;
	}
}
