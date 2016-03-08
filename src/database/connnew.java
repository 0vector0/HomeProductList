package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connnew {
	public static Connection conn;
	public static Statement statmt;
	public static ResultSet resSet;

	// --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
	public static void Conn() throws ClassNotFoundException, SQLException {
		conn = null;
		// Properties prop = new Properties();
		// prop.put("characterEncoding", "UTF-8");
		// prop.put("useUnicode", "true");
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:products.s3db");

		System.out.println("База Подключена!");
		statmt = conn.createStatement();
	}

	// --------Создание таблицы--------
	public static void CreateDB() throws ClassNotFoundException, SQLException {
		// statmt = conn.createStatement();
		statmt.execute(
				"CREATE TABLE if not exists 'productslist' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'type' text, 'price' INTEGER, 'productPrice' INTEGER"
						+ ");");
		// String name, String type, String manufacturer, double price

		System.out.println("Таблица создана или уже существует.");
	}

	// --------Заполнение таблицы--------
	public static void WriteDB() throws SQLException {
		statmt.execute(
				"INSERT INTO 'productslist' ('name' , 'type' , 'price', 'productPrice') VALUES ('молоко', 'молочка', '11', 120); ");

		System.out.println("Таблица заполнена");
	}

	// -------- Вывод таблицы--------
	public static void ReadDB() throws ClassNotFoundException, SQLException {
		resSet = statmt.executeQuery("SELECT * FROM productslist");

		while (resSet.next()) {
			String name = resSet.getString("name");
			String type = resSet.getString("type");
			int price = resSet.getInt("price");
			System.out.println("name = " + name);
			System.out.println("type = " + type);
			System.out.println("price = " + price);
			System.out.println();
		}

		System.out.println("Таблица выведена");
	}

	// --------Закрытие--------
	public static void CloseDB() throws ClassNotFoundException, SQLException {
		conn.close();
		// statmt.close();
		resSet.close();

		System.out.println("Соединения закрыты");
	}

}
