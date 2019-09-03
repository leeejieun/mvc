package config;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBCP {
	public static Connection dbConn() {
		Connection conn = null;
		DataSource ds = null;
		
		try {
			Context initCtx = new InitialContext();
			ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/oracle");
			conn = ds.getConnection();
			System.out.print("DBCP 연결 성공...");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
