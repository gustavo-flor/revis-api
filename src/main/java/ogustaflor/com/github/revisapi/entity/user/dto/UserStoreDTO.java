package ogustaflor.com.github.revisapi.entity.user.dto;

import lombok.Data;
import ogustaflor.com.github.revisapi.entity.user.Role;
import ogustaflor.com.github.revisapi.entity.user.User;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class UserStoreDTO {
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	private Set<Role> authorities;
	
	public User toEntity() {
		return User.builder()
			.email(getEmail())
			.username(getUsername())
			.password(getPassword())
			.authorities(getAuthorities())
			.build();
	}
	
}
