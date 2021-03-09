package ogustaflor.com.github.revisapi.entity.topic;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ogustaflor.com.github.revisapi.entity.AbstractRequest;
import ogustaflor.com.github.revisapi.entity.PersistableEntityDetail;
import ogustaflor.com.github.revisapi.entity.matter.Matter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public abstract class TopicDTO {
	
	private interface NameField { String getName(); }
	private interface MatterField { Matter getMatter(); }
	
	public abstract static class Request {
		
		@EqualsAndHashCode(callSuper = true)
		@Data
		public static class TopicStore extends AbstractRequest<Topic> implements NameField, MatterField {
			@NotBlank private String name;
			@NotNull private PersistableEntityDetail matter;
			
			public Matter getMatter() {
				return Matter.builder().id(matter.getId()).build();
			}
			
			@Override
			public Topic toEntity() {
				return Topic.builder()
						.name(getName())
						.matter(getMatter())
						.build();
			}
		}
		
		@EqualsAndHashCode(callSuper = true)
		@Data
		public static class TopicUpdate extends AbstractRequest<Topic> implements NameField, MatterField {
			@NotBlank private String name;
			@NotNull private PersistableEntityDetail matter;
			
			public Matter getMatter() {
				return Matter.builder().id(matter.getId()).build();
			}
			
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
