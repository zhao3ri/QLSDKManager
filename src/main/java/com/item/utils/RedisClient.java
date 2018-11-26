package com.item.utils;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClient {
	private static JedisPool jedisPool;

	static {
		int maxActive = Configuration.getInstance().getRedisMaxActive();
		int maxIdle = Configuration.getInstance().getRedisMaxIdle();
		int maxWait = Configuration.getInstance().getRedisMaxWait();
		String host = Configuration.getInstance().getRedisHost();
		int port = Configuration.getInstance().getRedisPort();
		
		JedisPoolConfig config = new JedisPoolConfig(); 
		config.setMaxActive(maxActive);
		config.setMaxIdle(maxIdle);
		config.setMaxWait(maxWait);
		jedisPool = new JedisPool(config, host, port);
	}
	
	/**
	 * 向缓存中设置字符串内容
	 * @param key key
	 * @param value value
	 * @return
	 * @throws Exception
	 */
	public static boolean  set(String key,String value) throws Exception{
		Jedis jedis = null;
		try {
			String pass = Configuration.getInstance().getRedisPass();
			jedis = jedisPool.getResource();
			jedis.auth(pass) ;
			jedis.select(Configuration.getInstance().getRedisSelect());
			jedis.setex(key, 60 * 60 * 12, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			jedisPool.returnResource(jedis);
		}
	}
	
	/**
	 * 向缓存中设置字符串内容
	 * @param key key
	 * @param value value
	 * @return
	 * @throws Exception
	 */
	public static boolean lpush(String key,String value) throws Exception{
		Jedis jedis = null;
		try {
			String pass = Configuration.getInstance().getRedisPass();
			jedis = jedisPool.getResource();
			jedis.auth(pass) ;
			jedis.select(Configuration.getInstance().getRedisSelect());
			jedis.lpush(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			jedisPool.returnResource(jedis);
		}
	}
	
	public static String rpop(String key){
		Jedis jedis = null;
		try {
			String pass = Configuration.getInstance().getRedisPass();
			jedis = jedisPool.getResource();
			jedis.auth(pass) ;
			jedis.select(Configuration.getInstance().getRedisSelect());
			return jedis.rpop(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			jedisPool.returnResource(jedis);
		}
	}
	
	/**
	 * 向缓存中设置对象
	 * @param key 
	 * @param value
	 * @return
	 */
	public static boolean  set(String key,Object value){
		Jedis jedis = null;
		try {
			String objectJson = JsonUtil.toJsonString(value);
			String pass = Configuration.getInstance().getRedisPass();
			jedis = jedisPool.getResource();
			jedis.auth(pass) ;
			jedis.select(Configuration.getInstance().getRedisSelect());
			jedis.setex(key, 60 * 60 * 12, objectJson);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			jedisPool.returnResource(jedis);
		}
	}
	
	/**
	 * 删除缓存中得对象，根据key
	 * @param key
	 * @return
	 */
	public static boolean del(String key){
		Jedis jedis = null;
		try {
			String pass = Configuration.getInstance().getRedisPass();
			jedis = jedisPool.getResource();
			jedis.auth(pass) ;
			jedis.select(Configuration.getInstance().getRedisSelect());
			jedis.del(key);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			jedisPool.returnResource(jedis);
		}
	}
	
	/**
	 * 根据key 获取内容
	 * @param key
	 * @return
	 */
	public static Object get(String key){
		Jedis jedis = null;
		try {
			String pass = Configuration.getInstance().getRedisPass();
			jedis = jedisPool.getResource();
			jedis.auth(pass) ;
			jedis.select(Configuration.getInstance().getRedisSelect());
			Object value = jedis.get(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			jedisPool.returnResource(jedis);
		}
	}
	

	/**
	 * 根据key 获取对象
	 * @param key
	 * @return
	 */
	public static <T> T get(String key,Class<T> clazz){
		Jedis jedis = null;
		try {
			String pass = Configuration.getInstance().getRedisPass();
			jedis = jedisPool.getResource();
			jedis.auth(pass) ;
			jedis.select(Configuration.getInstance().getRedisSelect());
			String value = jedis.get(key);
			if (StringUtils.isNotBlank(value)) {
				return JsonUtil.stringToObject(value, clazz);
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			jedisPool.returnResource(jedis);
		}
	}
	
	public static void main(String[] args) {
		del("rs_pg_1017_150212661932");
		System.out.println(get("rs_pg_1001_150204878739"));
	}
}