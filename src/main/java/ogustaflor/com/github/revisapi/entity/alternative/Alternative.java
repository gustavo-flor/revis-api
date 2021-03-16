package ogustaflor.com.github.revisapi.entity.alternative;

import lombok.*;
import ogustaflor.com.github.revisapi.entity.AbstractAuditableEntity;
import ogustaflor.com.github.revisapi.entity.question.Question;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@Table(name = "alternatives")
public class Alternative extends AbstractAuditableEntity<Long> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@NotNull
	@Column(nullable = false)
	private boolean correct;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id", nullable = false)
	private Question question;
	
}
