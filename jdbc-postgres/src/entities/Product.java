package entities;

public class Product {
	
	private Long id;
	private String name;
	private Double price;
	private String description;
	private String imageUri;
	
	// Contrutor - operação especial que roda quando instaciiamos o objeto
	// Por padrão, o construtor deve ficar vazio - sem argumentos
	// Mas, podemos gerar também o construtor com os argumentos
	
	public Product() {
	}
	
	public Product(Long id, String name, Double price, String description, String imageUri) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.imageUri = imageUri;
	}

	// private = usado para proteger o atributo, para que ele não possa ser acessado de qlqr jeito
	// Por esse motivo, faz-se o encapsulamento e dá-se, seletivamente o acesso aos dados através dos getters e setters
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImageUri() {
		return imageUri;
	}
	
	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", imageUri=" + imageUri + "]";
	}
	
	

	
}
