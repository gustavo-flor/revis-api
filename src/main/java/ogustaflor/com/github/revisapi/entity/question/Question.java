package ogustaflor.com.github.revisapi.entity.question;

import lombok.*;
import ogustaflor.com.github.revisapi.entity.AbstractAuditableEntity;
import ogustaflor.com.github.revisapi.entity.alternative.Alternative;
import ogustaflor.com.github.revisapi.entity.topic.Topic;
import ogustaflor.com.github.revisapi.entity.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@Table(name = "questions")
public class Question extends AbstractAuditableEntity<Long> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@NotEmpty
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, orphanRemoval = true)
	@JoinColumn(name = "question_id")
	private Set<Alternative> alternatives;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "topic_id", nullable = false)
	private Topic topic;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reviewed_by", nullable = false)
	private User reviewedBy;
	
}
