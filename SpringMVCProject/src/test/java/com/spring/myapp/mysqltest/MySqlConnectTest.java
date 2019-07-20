package com.spring.myapp.mysqltest;

import java.sql.*;

import org.junit.Test;

public class MySqlConnectTest {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/spring?useSSL=false&serverTimezone=Asia/Seoul";
	private static final String UID = "root";
	private static final String UPW = "0814";
	
	@Test
	public void connectTest() throws Exception{
		Class.forName(DRIVER);
		Connection conn = null;
		try {
			//연결에 성공했을떄 conn으로 객체를 리턴해줌, 그 객체를 print함
			conn = DriverManager.getConnection(URL, UID, UPW);
			System.out.println(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		
		
	}
	
}
