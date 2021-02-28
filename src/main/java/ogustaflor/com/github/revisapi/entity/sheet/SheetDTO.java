package ogustaflor.com.github.revisapi.entity.sheet;

import lombok.Data;
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
		
		@Data
		public static class SheetStore implements TitleField, LevelField, TopicField, ToEntityMethod {
			@NotBlank String title;
			@NotNull Level level;
			@NotNull Topic topic;
			
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
			@NotBlank String title;
			@NotNull Level level;
			@NotNull Topic topic;
			
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
