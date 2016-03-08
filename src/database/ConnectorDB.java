package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorDB {

	public static Connection getConnection() throws SQLException {

		String nameDataBase = "products.s3db";
		String url = "jdbc:sqlite:" + nameDataBase;

		return DriverManager.getConnection(url);

	}

}
