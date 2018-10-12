package com.item.utils;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Configuration {

	private static final Logger logger = LoggerFactory.getLogger(Configuration.class);
	private static final Properties properties = new Properties();
	private static final Configuration INSTANCE = new Configuration();

	private Configuration() {
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
		} catch (IOException e) {
			logger.error("load config file application.properties exception", e);	
		}
	}
	
	public static Configuration getInstance() {
		return INSTANCE;
	}

	private String get(String key) {
		return properties.getProperty(key);
	}
	
	public String getRedisHost(){
		return get("redis.host");
	}
	
	public Integer getRedisPort(){
		return Integer.valueOf(get("redis.port"));
	}
	
	public Integer getRedisMaxIdle(){
		return Integer.valueOf(get("redis.maxIdle"));
	}
	
	public Integer getRedisMaxActive(){
		return Integer.valueOf(get("redis.maxActive"));
	}
	
	public Integer getRedisMaxWait(){
		return Integer.valueOf(get("redis.maxWait"));
	}

	public String getRedisPass(){
		return  get("redis.pass");
	}
	public Integer getRedisSelect() {
		return Integer.valueOf(get("redis.select"));
	}
}
