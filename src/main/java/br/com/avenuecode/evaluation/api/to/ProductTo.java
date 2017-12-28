package br.com.avenuecode.evaluation.api.to;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude= {"products", "images"})
@EqualsAndHashCode(exclude= {"products", "images"})
@JsonInclude(Include.NON_EMPTY)
@NoArgsConstructor
public class ProductTo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private Long parentProductId;
	
	@JsonIgnore
	private ProductTo parentProduct;
	
	private Set<ImageTo> images = new HashSet<>();
	
	private Set<ProductTo> products = new HashSet<>();
	
	public ProductTo(Long id) {
		this.id = id;
	}
	
	public void add(ProductTo productTo) {
		this.products.add(productTo);
		productTo.setParentProduct(this);
	}
	
	public void add(ImageTo imageTo) {
		this.images.add(imageTo);
	}
}