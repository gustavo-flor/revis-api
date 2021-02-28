package ogustaflor.com.github.revisapi.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogustaflor.com.github.revisapi.entity.AbstractAuthenticableEntity;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
public class User extends AbstractAuthenticableEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(nullable = false)
	private String password;
	
	@Column
	@ElementCollection(targetClass = Role.class)
	@CollectionTable
	@Enumerated(EnumType.STRING)
	private Set<Role> authorities;
	
}
