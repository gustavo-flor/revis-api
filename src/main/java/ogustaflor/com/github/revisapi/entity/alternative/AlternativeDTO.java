package ogustaflor.com.github.revisapi.entity.alternative;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public abstract class AlternativeDTO {
	
	private interface ContentField { @NotBlank String getContent(); }
	private interface CorrectField { @NotEmpty boolean isCorrect(); }
	
	private interface ToEntityMethod { Alternative toEntity(); }
	
	public abstract static class Request {
		
		@Data
		public static class AlternativeStore implements ContentField, CorrectField, ToEntityMethod {
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
		
		@Data
		public static class AlternativeUpdate implements ContentField, CorrectField, ToEntityMethod {
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
