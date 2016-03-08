package gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Product;

/** @see http://stackoverflow.com/a/3002830/230513 */
class JOptionPaneProduct {

	public static Product updatePriceProduct(Product product) {
		// String[] items = { "One", "Two", "Three", "Four", "Five" };
		// JComboBox combo = new JComboBox(items);
		// JTextField name = new JTextField("");
		// JTextField type = new JTextField("");
		JTextField price = new JTextField(String.valueOf(product.getPrice()));
		// System.out.println(product.getPrice());
		// Product product = null;
		JPanel panel = new JPanel(new GridLayout(0, 1));
		// panel.add(combo);
		// panel.add(new JLabel("Название"));
		// panel.add(name);
		// panel.add(new JLabel("Тип"));
		// panel.add(type);
		panel.add(new JLabel("Цена"));
		panel.add(price);

		int result = JOptionPane.showConfirmDialog(null, panel, "Test", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			// System.out.println(combo.getSelectedItem() + " " +
			// field1.getText() + " " + field2.getText());
			System.out.println("Ok");

			product = new Product(product.getName(), product.getType(), product.getQuantity(),
					Integer.parseInt(price.getText()));
			return product;
		} else {
			System.out.println("Cancelled");
		}
		return product;
	}

	public static Product addNewProduct() {
		// String[] items = { "One", "Two", "Three", "Four", "Five" };
		// JComboBox combo = new JComboBox(items);
		JTextField name = new JTextField("");
		JTextField type = new JTextField("");
		JTextField price = new JTextField("");
		Product product = null;
		JPanel panel = new JPanel(new GridLayout(0, 1));
		// panel.add(combo);
		panel.add(new JLabel("Название"));
		panel.add(name);
		panel.add(new JLabel("Тип"));
		panel.add(type);
		panel.add(new JLabel("Цена"));
		panel.add(price);

		int result = JOptionPane.showConfirmDialog(null, panel, "Test", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			// System.out.println(combo.getSelectedItem() + " " +
			// field1.getText() + " " + field2.getText());
			System.out.println("Ok");

			product = new Product(name.getText(), type.getText(), 1, Integer.parseInt(price.getText()));
			return product;
		} else {
			System.out.println("Cancelled");
		}
		return product;
	}

	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	//
	// @Override
	// public void run() {
	// display();
	// }
	// });
	// }
}
