package ogustaflor.com.github.revisapi.entity.question;

import lombok.Data;
import ogustaflor.com.github.revisapi.entity.alternative.Alternative;
import ogustaflor.com.github.revisapi.entity.alternative.AlternativeDTO;
import ogustaflor.com.github.revisapi.entity.topic.Topic;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class QuestionDTO {
	
	private interface ContentField { String getContent(); }
	private interface AlternativesField { Set<Alternative> getAlternatives(); }
	private interface TopicField { Topic getTopic(); }
	
	private interface ToEntityMethod { Question toEntity(); }
	
	public abstract static class Request {
		
		@Data
		public static class QuestionStore implements ContentField, AlternativesField, TopicField, ToEntityMethod {
			@NotBlank String content;
			@NotEmpty Set<AlternativeDTO.Request.AlternativeStore> alternatives;
			@NotNull Topic topic;
			
			public Set<Alternative> getAlternatives() {
				return alternatives.stream()
						.map(AlternativeDTO.Request.AlternativeStore::toEntity)
						.collect(Collectors.toSet());
			}
			
			@Override
			public Question toEntity() {
				return Question.builder()
						.content(getContent())
						.alternatives(getAlternatives())
						.topic(getTopic())
						.build();
			}
		}
		
		@Data
		public static class QuestionUpdate implements ContentField, AlternativesField, TopicField, ToEntityMethod {
			@NotBlank String content;
			@NotEmpty Set<AlternativeDTO.Request.AlternativeUpdate> alternatives;
			@NotNull Topic topic;
			
			public Set<Alternative> getAlternatives() {
				return alternatives.stream()
						.map(AlternativeDTO.Request.AlternativeUpdate::toEntity)
						.collect(Collectors.toSet());
			}
			
			@Override
			public Question toEntity() {
				return Question.builder()
						.content(getContent())
						.alternatives(getAlternatives())
						.topic(getTopic())
						.build();
			}
		}
		
	}
	
}
