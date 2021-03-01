package ogustaflor.com.github.revisapi.entity.question;

import lombok.*;
import ogustaflor.com.github.revisapi.entity.AbstractPersistableEntity;
import ogustaflor.com.github.revisapi.entity.alternative.Alternative;

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
public class Question extends AbstractPersistableEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@NotEmpty
	@OneToMany(cascade = { CascadeType.PERSIST })
	private Set<Alternative> alternatives;
	
	@NotNull
	@Column(nullable = false)
	private boolean reviewed;
	
}
