package gui;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import main.Product;
import main.ProductList;

public class TableModelAll extends AbstractTableModel implements TableModel {

	private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

	private List<Product> products;

	public TableModelAll(ProductList productList) {
		this.products = productList.getProducts();
	}

	@Override
	public void addTableModelListener(TableModelListener listener) {
		listeners.add(listener);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Название";
		case 1:
			return "Тип";
		case 2:
			return "Количество";
		case 3:
			return "Цена за штуку";

		case 4:
			return "Общая цена";
		}
		return "";
	}

	@Override
	public int getRowCount() {

		return products.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Product product = products.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return product.getName();
		case 1:
			return product.getType();
		case 2:
			return product.getQuantity();
		case 3:
			return product.getPrice();
		case 4:
			return product.getProductPrice();
		}
		return "";

	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener listener) {
		listeners.remove(listener);
	}

	public void setProducts(ProductList products) {
		this.products = products.getProducts();
		fireTableDataChanged();
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		products.add((Product) value);
		fireTableDataChanged();
	}

}
