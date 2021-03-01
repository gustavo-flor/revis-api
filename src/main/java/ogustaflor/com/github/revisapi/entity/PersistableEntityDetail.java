package ogustaflor.com.github.revisapi.entity;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class PersistableEntityDetail {
	
	@Positive
	private Long id;
	
}
