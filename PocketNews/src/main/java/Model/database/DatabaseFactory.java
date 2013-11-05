package Model.database;//package com.studentarchives.model.database;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Properties;
//
//import javax.sql.DataSource;
//
////import org.apache.commons.dbcp.BasicDataSource;
//
//public class DatabaseFactory {
////	private static BasicDataSource dataSource;
//	private static String driverClassName;
//	private static String url;
//	private static String username;
//	private static String password;
//	private static int initialSize;
//	private static int maxActive;
//	private static boolean showsql;
//
//	private DatabaseFactory(){
//
//	}
//
//	/**
//	 * ע�Ṥ��
//	 * @param driverClassName	�����
//	 * @param url	���·��ַ
//	 * @param username	�û���
//	 * @param password	����
//	 * @param initialSize	��ʼ��������
//	 * @param maxActive	���������
//	 */
//	public static synchronized void register(String driverClassName,String url,String username,String password,int initialSize,int maxActive){
//		if(dataSource==null){
//			dataSource=new BasicDataSource();
//		}
//		//�����
//		dataSource.setDriverClassName(driverClassName);
//		//��ݿ��ַ
//		dataSource.setUrl(url);
//		//�û���
//		dataSource.setUsername(username);
//		//����
//		dataSource.setPassword(password);
//		//��ʼ��������
//		dataSource.setInitialSize(initialSize);
//		//���������
//		dataSource.setMaxActive(maxActive);
//	}
//
//	/**
//	 * ��Ĭ�ϵ�src��config.properties��ע�Ṥ��
//	 * @throws IOException
//	 */
//	public static synchronized void registerFromDefaultProperties() throws IOException{
//		Properties pops=new Properties();
//		String defaulPopsPath="config.properties";
//		try {
//			pops.load(DatabaseFactory.class.getClassLoader().getResourceAsStream(defaulPopsPath));
//		} catch (IOException e) {
//			throw e;
//		}
//		driverClassName=pops.getProperty("jdbc.driverClassName");
//		url=pops.getProperty("jdbc.url");
//		username=pops.getProperty("jdbc.username");
//		password=pops.getProperty("jdbc.password");
//		initialSize=Integer.parseInt(pops.get("jdbc.initialSize").toString());
//		maxActive=Integer.parseInt(pops.getProperty("jdbc.maxActive").toString());
//		showsql=Boolean.parseBoolean(pops.getProperty("jdbc.showsql").toString());
//
//		//ע�Ṥ��
//		register(driverClassName, url, username, password, initialSize, maxActive);
//	}
//
//	/**
//	 * �رչ���
//	 * @throws SQLException
//	 */
//	public static synchronized void close() throws SQLException{
//		if(dataSource!=null){
//			try {
//				dataSource.close();
//			} catch (SQLException e) {
//				throw e;
//			}
//			dataSource=null;
//		}
//	}
//
//	/**
//	 * ��ȡ����
//	 * @return
//	 * @throws SQLException
//	 */
//	public static Connection getConnection() throws SQLException{
//		Connection conn=null;
//		try {
//			conn=dataSource.getConnection();
//			if(showsql){
//				ConnectionProxy connProxy=new ConnectionProxy(conn);
//				conn=connProxy.newInstance();
//			}
//		} catch (SQLException e) {
//			throw e;
//		}
//		return conn;
//	}
//
//	/**
//	 * ��ȡ���ӳ�
//	 * @return
//	 */
//	public static DataSource getDataSource(){
//		return dataSource;
//	}
//
//}
