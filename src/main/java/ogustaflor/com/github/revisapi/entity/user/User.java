package ogustaflor.com.github.revisapi.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ogustaflor.com.github.revisapi.entity.AbstractAuthenticableEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
public class User extends AbstractAuthenticableEntity {
	
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
	
	@Column
	@ElementCollection(targetClass = Role.class)
	@CollectionTable
	@Enumerated(EnumType.STRING)
	private Set<Role> authorities;
	
}
