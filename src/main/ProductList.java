package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class ProductList {

	private ArrayList<Product> productsArrayList = new ArrayList<Product>();

	public void addProduct(Product product) {

		Product temp = product;
		// temp.setQuantity(1);

		int index = productsArrayList.indexOf(temp);

		if (index == -1) {

			temp.setId(productsArrayList.size());

			productsArrayList.add(temp);
		} else {

			// productsArrayList.get(index).setQuantity(productsArrayList.get(index).getQuantity()
			// + temp.getQuantity());

			// обновляет цену
			productsArrayList.get(index).setPrice((temp.getPrice()));
		}

		Collections.sort(productsArrayList, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				return o1.compareTo(o2);
			}
		});
	}

	public void buyProduct(Product product) {

		Product temp = product;
		temp.setQuantity(1);

		int index = productsArrayList.indexOf(temp);

		if (index == -1) {
			productsArrayList.add(temp);
		} else {
			//
			// int q = productsArrayList.get(index).getQuantity();
			productsArrayList.get(index).setQuantity(productsArrayList.get(index).getQuantity() + 1);
		}

		Collections.sort(productsArrayList, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				return o1.compareTo(o2);
			}
		});
	}

	public int getFullPrice() {
		int fullPrice = 0;
		for (Iterator iterator = productsArrayList.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			fullPrice = fullPrice + product.getQuantity() * product.getPrice();
		}
		// productsArrayList
		return fullPrice;
	}

	public void setProducts(ArrayList<Product> productsArrayList) {
		this.productsArrayList = productsArrayList;
	}

	public ArrayList<Product> getProducts() {
		return productsArrayList;
	}

	@Override
	public String toString() {
		return "ProductList [productsArrayList=" + productsArrayList + "]";
	}

}
