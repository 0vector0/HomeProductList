package main;

public class Product implements Comparable<Product> {

	private int id;
	private String name;
	private String type;
	private int quantity;
	private int price;
	private int productPrice;

	public Product(int id, String name, String type, int quantity, int price) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.quantity = quantity;
		this.price = price;
	}

	public Product(String name, String type, int quantity, int price) {
		super();
		// this.id = id;
		this.name = name;
		this.type = type;
		this.quantity = quantity;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public int getProductPrice() {
		return price * quantity;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", type=" + type + ", quantity=" + quantity + ", price=" + price
				+ ", productPrice=" + productPrice + "]";
	}

	@Override

	public int compareTo(Product p) {

		String str1 = this.getName();
		String str2 = p.getName();

		int result = str1.compareTo(str2);

		// int result = this.getId() - p.getId();
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
