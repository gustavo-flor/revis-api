package ogustaflor.com.github.revisapi.entity.user.dto;

import lombok.Data;
import ogustaflor.com.github.revisapi.entity.user.Role;
import ogustaflor.com.github.revisapi.entity.user.User;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class UserUpdateDTO {
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String username;
	
	private Set<Role> authorities;
	
	public User toEntity() {
		return User.builder()
				.email(getEmail())
				.username(getUsername())
				.authorities(getAuthorities())
				.build();
	}
	
}
