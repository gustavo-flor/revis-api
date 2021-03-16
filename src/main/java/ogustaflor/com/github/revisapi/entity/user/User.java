package ogustaflor.com.github.revisapi.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogustaflor.com.github.revisapi.entity.AbstractAuthenticableEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@Table(name = "users")
public class User extends AbstractAuthenticableEntity<Long> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String email;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String username;
	
	@NotBlank
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(nullable = false)
	private String password;
	
	@ElementCollection(fetch = FetchType.LAZY, targetClass = Role.class)
	@CollectionTable(name = "authorities", joinColumns = @JoinColumn(name = "user_id"))
	@Enumerated(EnumType.STRING)
	private Set<Role> authorities;
	
}
