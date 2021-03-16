package ogustaflor.com.github.revisapi.entity.topic;

import lombok.*;
import ogustaflor.com.github.revisapi.entity.AbstractAuditableEntity;
import ogustaflor.com.github.revisapi.entity.matter.Matter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@Table(name = "topics")
public class Topic extends AbstractAuditableEntity<Long> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String name;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "matter_id", nullable = false)
	private Matter matter;
	
}
