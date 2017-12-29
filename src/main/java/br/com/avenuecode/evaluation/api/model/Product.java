package br.com.avenuecode.evaluation.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude= {"products", "parentProduct", "images"})
@EqualsAndHashCode(exclude= {"products", "parentProduct", "images"})
public class Product  implements Serializable{

	private static final long serialVersionUID = 926619562765735806L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="{product.name.size}")
	@Size(min=5, max=100, message="{product.name.size}")
	private String name;
	
	@NotNull(message="{product.description.size}")
	@Size(min=5, max=100, message="{product.description.size}")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "parent_product_id", referencedColumnName = "id")
	private Product parentProduct;
	
	@OneToMany(mappedBy = "parentProduct", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},   fetch = FetchType.LAZY)
	private Set<Product> products;
	
	@Transient
	private Long parentProductId;
	
	@OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private List<Image> images = new ArrayList<>();
	
	public Product(Long id) {
		this.id = id;
	}
	
	
	public Product(String description, Product productFather) {
		this.parentProduct = productFather;
	}
	
	public Long getParentProductId() {
		return parentProduct.getId();
	}


	public Product(String name) {
		this.name = name;
	}
	
	public void add(Product product) {
		this.products.add(product);
		product.setParentProduct(this);
	}
	
	public void addImage(Image image) {
		this.images.add(image);
	}
	
		
	
}
