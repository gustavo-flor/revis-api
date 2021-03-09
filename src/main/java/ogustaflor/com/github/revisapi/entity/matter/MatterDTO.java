package ogustaflor.com.github.revisapi.entity.matter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ogustaflor.com.github.revisapi.entity.AbstractRequest;

import javax.validation.constraints.NotBlank;

public abstract class MatterDTO {
	
	private interface NameField { String getName(); }
	
	public abstract static class Request {
		
		@EqualsAndHashCode(callSuper = true)
		@Data
		public static class MatterStore extends AbstractRequest<Matter> implements NameField {
			@NotBlank private String name;
			
			@Override
			public Matter toEntity() {
				return Matter.builder().name(getName()).build();
			}
		}
		
		@EqualsAndHashCode(callSuper = true)
		@Data
		public static class MatterUpdate extends AbstractRequest<Matter> implements NameField {
			@NotBlank private String name;
			
			@Override
			public Matter toEntity() {
				return Matter.builder().name(getName()).build();
			}
		}
		
	}

}
