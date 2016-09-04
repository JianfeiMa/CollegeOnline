package com.tomo.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 带缓存的Dao工厂
 * @author yhx
 */
public class DaoFactory {
	private static Properties props = new Properties();
	
	/**用于缓存dao实例的Map*/
	private static Map<String,Object> cache = new HashMap<String, Object>();
	
	private DaoFactory(){}
	
	static {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("dao.properties");
		try {
			props.load(is);
			
		} catch (IOException e) {
			System.err.println("在classpath下没有找到dao.properties文件，请检查!");
			e.printStackTrace();
		}
	}
	
	public static Object getInstance(String daoName){
		Object o = null;
		
		o = cache.get(daoName); //先根据dao名称去缓存Map中取对应的实例
		
		if(null == o){ //缓存中还不存在，就新创建，并存放到缓存中。
			String className = props.getProperty(daoName);
			if(null != className && !"".equals(className)){
				try {
					o = Class.forName(className).newInstance();
					
					cache.put(daoName, o);
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		
		return o;
	}
	
	//泛型方法
	public static <T> T getInstance(String daoName, Class<T> clazz){
		T t = null;
		
		Object temp = cache.get(daoName);
		if(null == temp){
			String className = props.getProperty(daoName);
			if(null != className && !"".equals(className)){
				try {
					Object o = Class.forName(className).newInstance();
					
					t = clazz.cast(o);
					//t = (T)o;
					
					cache.put(daoName, t);
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}else{
			t = clazz.cast(temp);
		}
		return t;
	}
}
