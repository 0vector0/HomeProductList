package main;

import database.DataBaseController;
import gui.MainWindow;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ProductList productList = new ProductList();
		ProductList buyProductList = new ProductList();

		DataBaseController.CreateTable();
		// DataBaseController.WriteTable();

		// DataBaseController.WriteTable(productList);

		productList = DataBaseController.ReadTable();

		productList.addProduct(new Product("Бублики", "Бакалия", 20, 100));
		productList.addProduct(new Product("Сухарири", "Бакалия", 20, 100));
		productList.addProduct(new Product("Млеко", "Молочка", 20, 100));

		DataBaseController.WriteTable(productList);

		MainWindow mainWindow = new MainWindow(productList, buyProductList);

	}

}
