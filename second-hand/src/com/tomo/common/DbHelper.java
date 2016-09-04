package com.tomo.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.DataSources;

/**
 * 
 * @author qiujy
 */
public class DbHelper {
	private static Properties props = new Properties();
	private static DataSource pooled;
	
	private DbHelper(){}
	
	static{
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
		try {
			props.load(is);
			
			Class.forName(props.getProperty("driverClass"));
			
			//带c3p0连接池的数据源
			DataSource unpooled = DataSources.unpooledDataSource(props.getProperty("url"),
					props.getProperty("user"), 
					props.getProperty("password"));
			pooled = DataSources.pooledDataSource( unpooled );
		} catch (IOException e) {
			System.err.println("在classpath下没有找到jdbc.properties文件，请检查!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("在classpath下没有找到数据库驱动类，请检查！");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("初始化带c3p0连接池的数据源失败!");
			e.printStackTrace();
		}
	}
	
	public static Connection getConn() throws SQLException{
		Connection conn = null;
		try {
			conn = pooled.getConnection();
		} catch (SQLException e) {
			throw e;
		}
		return conn;
	}
	
	public static void close(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
