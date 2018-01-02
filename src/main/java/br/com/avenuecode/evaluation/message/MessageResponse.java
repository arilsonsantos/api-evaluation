package br.com.avenuecode.evaluation.message;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.avenuecode.evaluation.api.to.ImageTo;
import lombok.Data;

@Data
public class MessageResponse implements Serializable {
	
	private static final long serialVersionUID = -1678800972306625006L;

	Long id;
	
	@JsonProperty("images")
	@JsonInclude(Include.NON_EMPTY)
	Set<ImageTo> imagesTo = new HashSet<>();
	
	public MessageResponse(Long id) {
		this.id = id;
	}
	
	public MessageResponse(Long id, Set<ImageTo> imagesTo) {
		this.id = id;
		this.imagesTo = imagesTo;
	}
	
}
