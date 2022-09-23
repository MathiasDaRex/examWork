package carRentElements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {
	private Connection con;
	public Db() {
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:13306/car_rent?autoReconnect=true&useSSL=false",
					"car_rent",
					"car_rent"
					);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getCon() {
		return con;
	}
	
	public ResultSet lekerdez(String sql) {
		ResultSet rs = null;
		Statement stm = null;	
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
