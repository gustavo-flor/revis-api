package ogustaflor.com.github.revisapi.entity.matter;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public abstract class MatterDTO {
	
	private interface IdField { @Positive Long getId(); }
	private interface NameField { @NotBlank String getName(); }
	
	private interface ToEntityMethod { Matter toEntity(); }
	
	public abstract static class Request {
		
		@Data
		public static class MatterStore implements NameField, ToEntityMethod {
			@NotBlank String name;
			
			@Override
			public Matter toEntity() {
				return Matter.builder().name(getName()).build();
			}
		}
		
		@Data
		public static class MatterUpdate implements NameField, ToEntityMethod {
			@NotBlank String name;
			
			@Override
			public Matter toEntity() {
				return Matter.builder().name(getName()).build();
			}
		}
		
	}

}
