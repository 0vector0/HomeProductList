package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import main.Product;
import main.ProductList;

public class DataBaseController {

	public static void CreateTable() {

		Connection cn = null;
		try { // 1 блок
			cn = ConnectorDB.getConnection();
			Statement st = null;

			try { // 2 блок
				st = cn.createStatement();
				ResultSet rs = null;

				DatabaseMetaData meta = cn.getMetaData();
				rs = meta.getTables(null, null, "productslist", null);
				if (!rs.next()) {
					try { // 3 блок

						st.execute("CREATE TABLE if not exists 'productslist' "
								+ "('id' INTEGER PRIMARY KEY AUTOINCREMENT, "
								+ "'name' text, 'type' text, 'quantity' INTEGER, 'price' INTEGER, 'productPrice' INTEGER"
								+ ");");

						System.out.println("Таблица создана.");

					} finally { // для 3-го блока try
						/*
						 * закрыть ResultSet, если он был открыт или ошибка
						 * произошла во время чтения из него данных
						 */
						if (rs != null) { // был ли создан ResultSet
							rs.close();
						} else {
							System.err.println("ошибка во время чтения из БД");
						}
					}
				} else {
					System.out.println("Таблица уже существует.");
				}

			} finally {
				/*
				 * закрыть Statement, если он был открыт или ошибка произошла во
				 * время создания Statement
				 */
				if (st != null) { // для 2-го блока try

					st.close();
				} else

				{
					System.err.println("Statement не создан");
				}
			}
		} catch (

		SQLException e)

		{ // для 1-го блока try
			System.err.println("DB connection error: " + e);
			/*
			 * вывод сообщения о всех SQLException
			 */
		} finally

		{
			/*
			 * закрыть Connection, если он был открыт
			 */
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					System.err.println("Сonnection close error: " + e);
				}
			}
		}
	}

	public static void WriteTable(ProductList productList) {
		Connection cn = null;

		try { // 1 блок
			cn = ConnectorDB.getConnection();
			Statement st = null;

			try { // 2 блок
				st = cn.createStatement();
				// ResultSet rs = null;

				try { // 3 блок

					ArrayList<Product> productsArrayList = productList.getProducts();

					for (Iterator<Product> iterator = productsArrayList.iterator(); iterator.hasNext();) {
						Product product = iterator.next();
						// String name = product.getName();
						// String type = product.getType();
						// int price = product.getPrice();
						// int productPrice = product.getProductPrice();
						System.out.println(product);
						String query = " INSERT OR REPLACE into productslist (id, name, type, quantity, price, productPrice)"
								+ " values (?, ?, ?, ?, ?, ?)";

						PreparedStatement preparedStmt = cn.prepareStatement(query);
						preparedStmt.setInt(1, product.getId());
						preparedStmt.setString(2, product.getName());
						preparedStmt.setString(3, product.getType());
						preparedStmt.setInt(4, product.getQuantity());
						preparedStmt.setInt(5, product.getPrice());
						preparedStmt.setInt(6, product.getProductPrice());

						preparedStmt.execute();
					}

				} finally { // для 3-го блока try
					/*
					 * закрыть ResultSet, если он был открыт или ошибка
					 * произошла во время чтения из него данных
					 */
					// if (rs != null) { // был ли создан ResultSet
					// rs.close();
					// } else {
					// System.err.println("ошибка во время чтения из БД");
					// }
				}
			} finally {
				/*
				 * закрыть Statement, если он был открыт или ошибка произошла во
				 * время создания Statement
				 */
				if (st != null) { // для 2-го блока try

					st.close();
				} else

				{
					System.err.println("Statement не создан");
				}
			}
		} catch (

		SQLException e)

		{ // для 1-го блока try
			System.err.println("DB connection error: " + e);
			/*
			 * вывод сообщения о всех SQLException
			 */
		} finally

		{
			/*
			 * закрыть Connection, если он был открыт
			 */
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					System.err.println("Сonnection close error: " + e);
				}
			}
		}
	}

	public static ProductList ReadTable() {
		Connection cn = null;
		ProductList productList = new ProductList();
		ArrayList<Product> productsArrayList;
		try { // 1 блок
			cn = ConnectorDB.getConnection();
			Statement st = null;

			try { // 2 блок
				st = cn.createStatement();
				ResultSet rs = null;

				try { // 3 блок

					rs = st.executeQuery("SELECT * FROM productslist");

					productsArrayList = productList.getProducts();

					while (rs.next()) {
						int id = rs.getInt(1);
						String name = rs.getString(2);
						String type = rs.getString(3);
						int quantity = rs.getInt(4);
						int price = rs.getInt(5);
						// int productPrice = rs.getInt(5);

						productsArrayList.add(new Product(id, name, type, quantity, price));
					}
					// System.out.println(productsArrayList);
					if (productsArrayList.size() > 0) {
						// System.out.println(productsArrayList);
						productList.setProducts(productsArrayList);
						// return productList;
					} else {
						System.out.println("Not found");
					}
				} finally { // для 3-го блока try
					/*
					 * закрыть ResultSet, если он был открыт или ошибка
					 * произошла во время чтения из него данных
					 */
					if (rs != null) { // был ли создан ResultSet
						rs.close();
					} else {
						System.err.println("ошибка во время чтения из БД");
					}
				}
			} finally {
				/*
				 * закрыть Statement, если он был открыт или ошибка произошла во
				 * время создания Statement
				 */
				if (st != null) { // для 2-го блока try

					st.close();
				} else

				{
					System.err.println("Statement не создан");
				}
			}
		} catch (

		SQLException e)

		{ // для 1-го блока try
			System.err.println("DB connection error: " + e);
			/*
			 * вывод сообщения о всех SQLException
			 */
		} finally

		{
			/*
			 * закрыть Connection, если он был открыт
			 */
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					System.err.println("Сonnection close error: " + e);
				}
			}
		}
		return productList;
	}
}
