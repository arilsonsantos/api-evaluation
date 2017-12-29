package br.com.avenuecode.evaluation.message;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.avenuecode.evaluation.api.to.ProductTo;
import lombok.Data;

@Data
public class ProductMessage implements Serializable {
	
	private static final long serialVersionUID = -1678800972306625006L;

	@JsonProperty("Product")
	ProductTo productTo;
	
	@JsonProperty("Errors")
	List<String> errors;
	
}
