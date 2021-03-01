package ogustaflor.com.github.revisapi.entity.question;

import lombok.Data;
import ogustaflor.com.github.revisapi.entity.alternative.Alternative;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.Set;

public abstract class QuestionDTO {
	
	private interface IdField { @Positive Long getId(); }
	private interface ContentField { @NotBlank String getContent(); }
	private interface AlternativesField { @NotEmpty Set<Alternative> getAlternatives(); }
	
	private interface ToEntityMethod { Question toEntity(); }
	
	public abstract static class Request {
		
		@Data
		public static class QuestionStore implements ContentField, AlternativesField, ToEntityMethod {
			@NotBlank String content;
			@NotEmpty Set<Alternative> alternatives;
			
			@Override
			public Question toEntity() {
				return Question.builder()
						.content(getContent())
						.alternatives(getAlternatives())
						.build();
			}
		}
		
		@Data
		public static class QuestionUpdate implements ContentField, AlternativesField, ToEntityMethod {
			@NotBlank String content;
			@NotEmpty Set<Alternative> alternatives;
			
			@Override
			public Question toEntity() {
				return Question.builder()
						.content(getContent())
						.alternatives(getAlternatives())
						.build();
			}
		}
		
	}
	
}
