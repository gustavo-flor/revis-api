package ogustaflor.com.github.revisapi.entity.alternative;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ogustaflor.com.github.revisapi.entity.AbstractRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public abstract class AlternativeDTO {
	
	private interface ContentField { @NotBlank String getContent(); }
	private interface CorrectField { @NotEmpty boolean isCorrect(); }

	public abstract static class Request {
		
		@EqualsAndHashCode(callSuper = true)
		@Data
		public static class AlternativeStore extends AbstractRequest<Alternative> implements ContentField, CorrectField {
			@NotBlank private String content;
			@NotNull private boolean correct;
			
			@Override
			public Alternative toEntity() {
				return Alternative.builder()
						.content(getContent())
						.correct(isCorrect())
						.build();
			}
		}
		
		@EqualsAndHashCode(callSuper = true)
		@Data
		public static class AlternativeUpdate extends AbstractRequest<Alternative> implements ContentField, CorrectField {
			@NotBlank private String content;
			@NotNull private boolean correct;
			
			@Override
			public Alternative toEntity() {
				return Alternative.builder()
						.content(getContent())
						.correct(isCorrect())
						.build();
			}
		}
		
	}
	
}
