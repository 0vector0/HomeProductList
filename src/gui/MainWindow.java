package gui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import main.Product;
import main.ProductList;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTable tableAllList;
	private JTable tableBuyList;
	ProductList productList = new ProductList();
	ProductList buyProductList = new ProductList();
	TableModelAll modelAll;
	TableModelAll modelBuy;

	JPanel panelBuyList;
	JLabel lblNewLabel;
	JLabel lblNewLabel_1;
	private JButton newProductButton;

	// /**
	// * Launch the application.
	// */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// MainWindow frame = new MainWindow();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	public MainWindow(ProductList productList, ProductList buyProductList) {
		this.productList = productList;
		this.buyProductList = buyProductList;

		// buyProductList.addProduct(new Product(1, "1", 1, 1));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JLabel label = new JLabel("Список товаров");
		contentPane.add(label);

		modelAll = new TableModelAll(productList);

		modelBuy = new TableModelAll(buyProductList);

		newProductButton = new JButton("Добавить товар");
		newProductButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				productList.addProduct(JOptionPaneProduct.addNewProduct());
				tableAllList.columnAdded(null);
				tableAllList.revalidate();

			}
		});
		contentPane.add(newProductButton);

		JPanel panelAllList = new JPanel();
		contentPane.add(panelAllList);
		panelAllList.setLayout(new BorderLayout(0, 0));

		tableAllList = new JTable(modelAll);
		tableAllList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Point point = e.getPoint();
					int column = tableAllList.columnAtPoint(point);
					int row = tableAllList.rowAtPoint(point);
					tableAllList.setColumnSelectionInterval(column, column);
					tableAllList.setRowSelectionInterval(row, row);

					String name = (String) tableAllList.getModel().getValueAt(row, 0);
					String type = (String) tableAllList.getModel().getValueAt(row, 1);
					int quantity = (int) tableAllList.getModel().getValueAt(row, 2);
					int price = (int) tableAllList.getModel().getValueAt(row, 3);

					// System.out.println(tableAllList.getModel().getClass().getName());
					if (column != 3) {
						Product productBuy = new Product(name, type, quantity, price);
						buyProduct(productBuy);
					} else {
						Product product = JOptionPaneProduct
								.updatePriceProduct(new Product(name, type, quantity, price));
						productList.addProduct(product);
						tableAllList.columnAdded(null);
						tableAllList.revalidate();
					}

				}
			}
		});
		panelAllList.add(new JScrollPane(tableAllList), BorderLayout.CENTER);

		lblNewLabel = new JLabel("Общий список");
		panelAllList.add(lblNewLabel, BorderLayout.NORTH);

		panelBuyList = new JPanel();
		contentPane.add(panelBuyList);
		panelBuyList.setLayout(new BorderLayout(0, 0));

		tableBuyList = new JTable(modelBuy);
		panelBuyList.add(new JScrollPane(tableBuyList), BorderLayout.CENTER);

		lblNewLabel_1 = new JLabel("Список покупок");
		panelBuyList.add(lblNewLabel_1, BorderLayout.NORTH);

		setVisible(true);
		// System.out.println(productList);
	}

	protected void buyProduct(Product productBuy) {
		buyProductList.buyProduct(productBuy);
		modelBuy.setProducts(buyProductList);
		// System.out.println(buyProductList);
		tableBuyList.columnAdded(null);
		tableBuyList.revalidate();
		lblNewLabel_1.setText("Сумма покупок - " + Integer.toString(buyProductList.getFullPrice()) + " грн.");
	}
}
