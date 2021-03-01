package ogustaflor.com.github.revisapi.entity.topic;

import lombok.Data;
import ogustaflor.com.github.revisapi.entity.matter.Matter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public abstract class TopicDTO {
	
	private interface NameField { String getName(); }
	private interface MatterField { Matter getMatter(); }
	
	private interface ToEntityMethod { Topic toEntity(); }
	
	public abstract static class Request {
		
		@Data
		public static class TopicStore implements NameField, MatterField, ToEntityMethod {
			@NotBlank String name;
			@NotNull Matter matter;
			
			@Override
			public Topic toEntity() {
				return Topic.builder()
						.name(getName())
						.matter(getMatter())
						.build();
			}
		}
		
		@Data
		public static class TopicUpdate implements NameField, MatterField, ToEntityMethod {
			@NotBlank String name;
			@NotNull Matter matter;
			
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
