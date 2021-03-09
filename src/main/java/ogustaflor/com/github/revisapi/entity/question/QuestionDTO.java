package ogustaflor.com.github.revisapi.entity.question;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ogustaflor.com.github.revisapi.entity.AbstractRequest;
import ogustaflor.com.github.revisapi.entity.PersistableEntityDetail;
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
	
	public abstract static class Request {
		
		@EqualsAndHashCode(callSuper = true)
		@Data
		public static class QuestionStore extends AbstractRequest<Question> implements ContentField, AlternativesField, TopicField {
			@NotBlank private String content;
			@NotEmpty private Set<AlternativeDTO.Request.AlternativeStore> alternatives;
			@NotNull private PersistableEntityDetail topic;
			
			public Set<Alternative> getAlternatives() {
				return alternatives.stream()
						.map(AlternativeDTO.Request.AlternativeStore::toEntity)
						.collect(Collectors.toSet());
			}
			
			public Topic getTopic() {
				return Topic.builder().id(topic.getId()).build();
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
		
		@EqualsAndHashCode(callSuper = true)
		@Data
		public static class QuestionUpdate extends AbstractRequest<Question> implements ContentField, AlternativesField, TopicField {
			@NotBlank private String content;
			@NotEmpty private Set<AlternativeDTO.Request.AlternativeUpdate> alternatives;
			@NotNull private PersistableEntityDetail topic;
			
			public Set<Alternative> getAlternatives() {
				return alternatives.stream()
						.map(AlternativeDTO.Request.AlternativeUpdate::toEntity)
						.collect(Collectors.toSet());
			}
			
			public Topic getTopic() {
				return Topic.builder().id(topic.getId()).build();
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
