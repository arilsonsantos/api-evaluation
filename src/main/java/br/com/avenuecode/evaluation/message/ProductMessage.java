package br.com.avenuecode.evaluation.message;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductMessage implements Serializable {
	
	private static final long serialVersionUID = -1678800972306625006L;

	Long id;
	
	public ProductMessage(Long id) {
		this.id = id;
	}
	
}
