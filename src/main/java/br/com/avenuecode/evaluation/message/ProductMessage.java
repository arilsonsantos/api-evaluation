package br.com.avenuecode.evaluation.message;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductMessage implements Serializable {
	
	private static final long serialVersionUID = -1678800972306625006L;


	@JsonProperty("idNewProduct")
	Long id;
	
	public ProductMessage(Long id) {
		this.id = id;
	}
	
}
