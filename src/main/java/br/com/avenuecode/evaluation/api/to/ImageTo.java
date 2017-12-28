package br.com.avenuecode.evaluation.api.to;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class ImageTo implements Serializable {

	private static final long serialVersionUID = -4272183660721155802L;

	private Long id;
	
	private String type;
	
	@JsonIgnore
	private ProductTo productTo;
	
}
