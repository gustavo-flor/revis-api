package ogustaflor.com.github.revisapi.entity.handout;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ogustaflor.com.github.revisapi.entity.AbstractPersistableEntity;
import ogustaflor.com.github.revisapi.entity.topic.Topic;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Handout extends AbstractPersistableEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String title;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Level level;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Topic topic;
	
}
