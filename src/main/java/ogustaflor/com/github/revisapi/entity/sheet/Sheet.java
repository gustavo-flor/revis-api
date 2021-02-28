package ogustaflor.com.github.revisapi.entity.sheet;

import lombok.*;
import ogustaflor.com.github.revisapi.entity.AbstractPersistableEntity;
import ogustaflor.com.github.revisapi.entity.topic.Topic;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
public class Sheet extends AbstractPersistableEntity {
	
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
