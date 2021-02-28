package ogustaflor.com.github.revisapi.entity.topic;

import lombok.Value;
import ogustaflor.com.github.revisapi.entity.sheet.Sheet;
import ogustaflor.com.github.revisapi.entity.matter.Matter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

public abstract class TopicDTO {
	
	private interface IdField { @Positive Long getId(); }
	private interface NameField { @NotBlank String getName(); }
	private interface MatterField { @NotNull Matter getMatter(); }
	
	private interface ToEntityMethod { Topic toEntity(); }
	
	public abstract static class Request {
		
		@Value
		public static class Store implements NameField, MatterField, ToEntityMethod {
			String name;
			Matter matter;
			Set<Sheet> sheets;
			
			@Override
			public Topic toEntity() {
				return Topic.builder()
						.name(getName())
						.matter(getMatter())
						.build();
			}
		}
		
		@Value
		public static class Update implements NameField, MatterField, ToEntityMethod {
			String name;
			Matter matter;
			Set<Sheet> sheets;
			
			@Override
			public Topic toEntity() {
				return Topic.builder()
					.name(getName())
					.matter(getMatter())
					.build();
			}
		}
		
	}
	
}
