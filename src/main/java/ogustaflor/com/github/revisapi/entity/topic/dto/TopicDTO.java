package ogustaflor.com.github.revisapi.entity.topic.dto;

import lombok.Data;
import ogustaflor.com.github.revisapi.entity.handout.Handout;
import ogustaflor.com.github.revisapi.entity.matter.Matter;
import ogustaflor.com.github.revisapi.entity.topic.Topic;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class TopicDTO {
	
	@NotBlank
	private String name;
	
	@NotNull
	private Matter matter;
	
	private Set<Handout> handouts;
	
	public Topic toEntity() {
		return Topic.builder()
				.name(getName())
				.matter(getMatter())
				.handouts(getHandouts())
				.build();
	}
	
}
