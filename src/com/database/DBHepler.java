package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBHepler {
	//数据库驱动	
	private static final String Driver="com.mysql.jdbc.Driver";
	
	private static final String URL_STRING="jdbc:mysql://118.89.231.179:3306/WeiXin?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
	private static final String USER_STRING="root";
	private static final String PASSWORD_STRING="259748";
	private static Connection conn=null;
	//静态代码块负责加载驱动
	static{
		try {
			Class.forName(Driver);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//单例模式 返回数据库链接对象
	public static Connection getConnection() throws SQLException{
		if(conn==null)
		{
			conn=DriverManager.getConnection(URL_STRING, USER_STRING, PASSWORD_STRING);
			return conn;
		}
		else {
			return conn;
		}
		
	}
	public static void main(String[] args) {
		try {
			Connection connection=getConnection();
			if (connection!=null) {
				System.out.println("数据库链接成功！");
				
			}
			else {
				System.out.println("数据库链接失败！");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

