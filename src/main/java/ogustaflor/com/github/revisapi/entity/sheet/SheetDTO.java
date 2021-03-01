package ogustaflor.com.github.revisapi.entity.sheet;

import lombok.Data;
import ogustaflor.com.github.revisapi.entity.PersistableEntityDetail;
import ogustaflor.com.github.revisapi.entity.topic.Topic;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public abstract class SheetDTO {
	
	private interface TitleField { String getTitle(); }
	private interface LevelField { Level getLevel(); }
	private interface TopicField { Topic getTopic(); }
	
	private interface ToEntityMethod { Sheet toEntity(); }
	
	public abstract static class Request {
		
		@Data
		public static class SheetStore implements TitleField, LevelField, TopicField, ToEntityMethod {
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
		
		@Data
		public static class SheetUpdate implements TitleField, LevelField, TopicField, ToEntityMethod {
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
