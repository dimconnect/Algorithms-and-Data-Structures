package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnect {
	
	public static String dbDriver = "com.mysql.jdbc.Driver";
	public static String dbURL = "jdbc:mysql://localhost:3306/Banks";
	public static String user = "root";
	public static String password = "root";
	
	public static Connection connect() throws ClassNotFoundException, IllegalAccessException, SQLException{
		Connection connection = null;
		try{
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(dbURL, user, password);
		}catch(Exception e){			
			e.printStackTrace();
		}
		return connection;
	}
}
