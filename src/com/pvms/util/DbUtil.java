package com.pvms.util;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil  {

	public static Connection getConnection() throws Exception{
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","pvms_schema","pvms"); 
		return con;
		
	}
}

