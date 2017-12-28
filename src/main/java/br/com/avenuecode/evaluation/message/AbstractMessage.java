package br.com.avenuecode.evaluation.message;

import java.util.List;

public class AbstractMessage <T> {

	protected T message(T object) {
		return object;
	}
	
	protected List<String> error(List<String> erros){
		return erros;						
	}
	
}
