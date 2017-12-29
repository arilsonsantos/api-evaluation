package br.com.avenuecode.evaluation.message;

import java.util.List;

import lombok.Data;

@Data
public class AbstractMessage <T> {

	protected T message(T object) {
		return object;
	}
	
	protected List<String> errors(List<String> erros){
		return erros;						
	}
	
}
