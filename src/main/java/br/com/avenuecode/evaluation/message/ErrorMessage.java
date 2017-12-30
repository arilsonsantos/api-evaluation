package br.com.avenuecode.evaluation.message;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ErrorMessage implements Serializable {

	private static final long serialVersionUID = -9131407975158303029L;
	
	@JsonProperty("Errors")
	@JsonInclude(Include.NON_EMPTY)
	List<String> errors;

}
