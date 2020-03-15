package nl.plaatsoft.plaatservice.core;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class Config.
 * 
 * @author wplaat
 */
public class Config {

	/** The Constant log. */
	private static final Logger log = LogManager.getLogger( Config.class);	
	
	/** The Constant FILENAME1. */
	private static final String FILENAME1 = "plaatservice.properties";
	
	/** The Constant FILENAME2. */
	private static final String FILENAME2 = "plaatservice.override.properties";
	
	/** The ip. */
	private String ip;
	
	/** The port. */
	private int port;
	
	/** The uri Base. */
	private String versionUri;
	
	/** The uri Product. */
	private String productUri;
	
	/**
	 * Instantiates a new config.
	 */
	public Config() {
		
		try {
			Properties prop = new Properties();
			 
			InputStream inputStream1 = getClass().getClassLoader().getResourceAsStream(FILENAME1);
 
			if (inputStream1 != null) {
				prop.load(inputStream1);
			} else {
				throw new FileNotFoundException("property file '" + FILENAME1 + "' not found in the classpath");
			}
			
			InputStream inputStream2 = getClass().getClassLoader().getResourceAsStream(FILENAME2);
			if (inputStream2 != null) {
				prop.load(inputStream2);
			}
 
			ip = prop.getProperty("ip");
			log.info("ip={}",ip);
			
			port = Integer.valueOf(prop.getProperty("port"));
			log.info("port={}",port);
			
			versionUri = prop.getProperty("versionUri");
			log.info("versionUri={}",versionUri);
			
			productUri = prop.getProperty("productUri");
			log.info("productUri={}",productUri);
			
		} catch (Exception e) {
			log.error(e.getMessage());
		} 
	}

	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Gets the version uri
	 *
	 * @return the version uri
	 */
	public String getVersionUri() {
		return versionUri;
	}
	
	/**
	 * Gets the product uri
	 *
	 * @return the product uri
	 */
	public String getProductUri() {
		return productUri;
	}
	
	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
}