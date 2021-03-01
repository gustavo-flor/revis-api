package ogustaflor.com.github.revisapi.entity.alternative;

import lombok.*;
import ogustaflor.com.github.revisapi.entity.AbstractPersistableEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
public class Alternative extends AbstractPersistableEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@NotNull
	@Column(nullable = false)
	private Boolean correct;
	
}
