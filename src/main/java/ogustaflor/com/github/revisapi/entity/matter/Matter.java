package ogustaflor.com.github.revisapi.entity.matter;

import lombok.*;
import ogustaflor.com.github.revisapi.entity.AbstractPersistableEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
public class Matter extends AbstractPersistableEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String name;
	
}
