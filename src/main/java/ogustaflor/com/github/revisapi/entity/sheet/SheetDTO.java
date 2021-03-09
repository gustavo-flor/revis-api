package ogustaflor.com.github.revisapi.entity.sheet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ogustaflor.com.github.revisapi.entity.AbstractRequest;
import ogustaflor.com.github.revisapi.entity.PersistableEntityDetail;
import ogustaflor.com.github.revisapi.entity.topic.Topic;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public abstract class SheetDTO {
	
	private interface TitleField { String getTitle(); }
	private interface LevelField { Level getLevel(); }
	private interface TopicField { Topic getTopic(); }
	
	public abstract static class Request {
		
		@EqualsAndHashCode(callSuper = true)
		@Data
		public static class SheetStore extends AbstractRequest<Sheet> implements TitleField, LevelField, TopicField {
			@NotBlank private String title;
			@NotNull private Level level;
			@NotNull private PersistableEntityDetail topic;
			
			public Topic getTopic() {
				return Topic.builder().id(topic.getId()).build();
			}
			
			@Override
			public Sheet toEntity() {
				return Sheet.builder()
						.title(getTitle())
						.level(getLevel())
						.topic(getTopic())
						.build();
			}
		}
		
		@EqualsAndHashCode(callSuper = true)
		@Data
		public static class SheetUpdate extends AbstractRequest<Sheet> implements TitleField, LevelField, TopicField {
			@NotBlank private String title;
			@NotNull private Level level;
			@NotNull private PersistableEntityDetail topic;
			
			public Topic getTopic() {
				return Topic.builder().id(topic.getId()).build();
			}
			
			@Override
			public Sheet toEntity() {
				return Sheet.builder()
					.title(getTitle())
					.level(getLevel())
					.topic(getTopic())
					.build();
			}
		}
		
	}
	
}
