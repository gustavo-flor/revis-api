package ogustaflor.com.github.revisapi.entity.matter;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public abstract class MatterDTO {
	
	private interface IdField { @Positive Long getId(); }
	private interface NameField { @NotBlank String getName(); }
	
	private interface ToEntityMethod { Matter toEntity(); }
	
	public abstract static class Request {
		
		@Value
		public static class Store implements NameField, ToEntityMethod {
			String name;
			
			@Override
			public Matter toEntity() {
				return Matter.builder().name(getName()).build();
			}
		}
		
		@Value
		public static class Update implements NameField, ToEntityMethod {
			String name;
			
			@Override
			public Matter toEntity() {
				return Matter.builder().name(getName()).build();
			}
		}
		
	}

}
