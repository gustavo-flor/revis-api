package ogustaflor.com.github.revisapi.entity.matter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ogustaflor.com.github.revisapi.entity.AbstractPersistableEntity;
import ogustaflor.com.github.revisapi.entity.StoreEntity;
import ogustaflor.com.github.revisapi.entity.UpdateEntity;
import ogustaflor.com.github.revisapi.entity.topic.Topic;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Matter extends AbstractPersistableEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(groups = { StoreEntity.class, UpdateEntity.class })
	@Column(nullable = false, unique = true)
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<Topic> topics;
	
}
