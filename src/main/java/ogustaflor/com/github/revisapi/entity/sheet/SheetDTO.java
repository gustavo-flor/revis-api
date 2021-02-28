package ogustaflor.com.github.revisapi.entity.sheet;

import lombok.Value;
import ogustaflor.com.github.revisapi.entity.topic.Topic;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public abstract class SheetDTO {
	
	private interface IdField { @Positive Long getId(); }
	private interface TitleField { @NotBlank String getTitle(); }
	private interface LevelField { @NotNull Level getLevel(); }
	private interface TopicField { @NotNull Topic getTopic(); }
	
	private interface ToEntityMethod { Sheet toEntity(); }
	
	public abstract static class Request {
		
		@Value
		public static class Store implements TitleField, LevelField, TopicField, ToEntityMethod {
			String title;
			Level level;
			Topic topic;
			
			@Override
			public Sheet toEntity() {
				return Sheet.builder()
						.title(getTitle())
						.level(getLevel())
						.topic(getTopic())
						.build();
			}
		}
		
		@Value
		public static class Update implements TitleField, LevelField, TopicField, ToEntityMethod {
			String title;
			Level level;
			Topic topic;
			
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
