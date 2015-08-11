package com.fisher.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Server;

public class H2Manager {
	private Server server;
	private String port = "9094";
	private String path = System.getProperty("user.dir")+"\\src";
	private String dbDir = "\\h2db\\app";
	private String user = "fisher";
	private String password = "123456";
	private final String CLASS_NAME = "org.h2.Driver";
	private ResultSet result;
	private Connection conn;
	private Statement stat;

	public static void main(String[] args) {
		H2Manager manager = new H2Manager();
//		manager.startServer();
		String sql;
		ResultSet rs = null;
		int i;
		sql = "select * from test";
		sql = "select * from userinfo where username='fisher' and password='123'";
		rs = manager.executeQuery(sql);
		i = 1;
		try {
			while (rs.next()) {
				System.out.println(i++ + ":" + rs.getString("username"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
//		sql = "insert into test values('fisher')";
//		Boolean b = manager.execute(sql);
//		if( b == false){
//			sql = "select * from test";
//			rs = manager.executeQuery(sql);
//			i = 1;
//			try {
//				while (rs.next()) {
//					System.out.println(i++ + ":" + rs.getString("name"));
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		
		manager.close();
//		manager.stopServer();
	}

	public H2Manager() {
		if(this.getClass().getClassLoader().getResource("/") != null){
			path = this.getClass().getClassLoader().getResource("/").getPath();
		}
		
		System.out.println("new path:"+path);
//		startServer();
		try {
			Class.forName(CLASS_NAME);
			System.out.println("jdbc:h2:" + path + dbDir);
			conn = DriverManager.getConnection("jdbc:h2:" + path + dbDir, user,
					password);
			stat = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startServer() {
		try {
			System.out.println("数据库H2启动中...");
			server = Server.createTcpServer(new String[] { "-tcpPort", port })
					.start();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void stopServer() {
		if (server != null) {
			System.out.println("数据库H2停止中...");
			server.stop();
			System.out.println("数据库已停止");
		}
	}

	public ResultSet executeQuery(String sql) {
		try {
			result = stat.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public Boolean execute(String sql) {
		Boolean flag = true;
		try {
			flag = stat.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public void close() {
		try {
			result.close();
			stat.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		stopServer();
	}

}
