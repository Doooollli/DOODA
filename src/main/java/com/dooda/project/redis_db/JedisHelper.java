package com.dooda.project.redis_db;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


	public class JedisHelper {
		private static final String REDIS_HOET = "localhost"; // TODO
		private static final int REDIS_PORT = 6379;
		private final Set<Jedis> connectionList = new HashSet<Jedis>();
		private static JedisPool jedisPool;
		
		/**
		 * Jedis 연결풀 생성을 위한 헬퍼 클래스 내부 생성자
		 */
		private JedisHelper() {
			this.jedisPool = new JedisPool(new JedisPoolConfig(), REDIS_HOET, REDIS_PORT, 5000);
		}
		
		/**
		 * 싱글톤 처리를 위한 Holder Class, Jedis 연결풀이 포함된 헬퍼 객체를 반환함
		 */
		private static class LazyHolder {
			private static final JedisHelper INSTANCE = new JedisHelper();
		}
		
		/**
		 * 싱글톤 객체를 가져옴
		 * @return Jedis Helper Object
		 */
		public static JedisHelper getInstance() {
			return LazyHolder.INSTANCE;
		}
		
		/**
		 * Get Jedis client connection
		 * @return Jedis Object
		 */
		final public Jedis getResource() {
			Jedis jedis = this.jedisPool.getResource();
			this.connectionList.add(jedis);
			
			return jedis;
		}
		
		/**
		 * 사용이 완료된 제디스 객체를 회수
		 * @param jedis 사용 완료된 Jedis Object
		 */
		final public void returnResource(Jedis jedis) {
			this.jedisPool.returnResource(jedis);
		}
		
		/**
		 * Jedis 연결풀 제거
		 */
		final public void destroyPool() {
			Iterator<Jedis> jedisList = this.connectionList.iterator();
			while (jedisList.hasNext()) {
				Jedis jedis = jedisList.next();
				this.jedisPool.returnResource(jedis);
			}
			
			this.jedisPool.destroy();
		}
		
	}



