package ogustaflor.com.github.revisapi.entity.sheet;

import lombok.*;
import ogustaflor.com.github.revisapi.entity.AbstractAuditableEntity;
import ogustaflor.com.github.revisapi.entity.question.Question;
import ogustaflor.com.github.revisapi.entity.topic.Topic;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@Table(name = "sheets")
public class Sheet extends AbstractAuditableEntity<Long> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String title;
	
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Level level;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "topic_id", nullable = false)
	private Topic topic;

	@ManyToMany
	@JoinTable(name = "sheets_questions", joinColumns = @JoinColumn(name = "sheet_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
	private Set<Question> questions;
	
}
