package ogustaflor.com.github.revisapi.entity.topic;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ogustaflor.com.github.revisapi.entity.AbstractPersistableEntity;
import ogustaflor.com.github.revisapi.entity.handout.Handout;
import ogustaflor.com.github.revisapi.entity.matter.Matter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
public class Topic extends AbstractPersistableEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String name;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Matter matter;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<Handout> handouts;
	
}
