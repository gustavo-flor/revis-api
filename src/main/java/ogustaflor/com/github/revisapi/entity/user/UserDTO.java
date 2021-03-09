package ogustaflor.com.github.revisapi.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ogustaflor.com.github.revisapi.entity.AbstractRequest;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public abstract class UserDTO {
	
	private interface EmailField { String getEmail(); }
	private interface UsernameField { String getUsername(); }
	private interface PasswordField { String getPassword(); }
	private interface AuthoritiesField { Set<Role> getAuthorities(); }
	
	public abstract static class Request {
		
		@EqualsAndHashCode(callSuper = true)
		@Data
		public static class UserStore extends AbstractRequest<User> implements EmailField, UsernameField, PasswordField, AuthoritiesField {
			@NotBlank private String email;
			@NotBlank private String username;
			@NotBlank private String password;
			private Set<Role> authorities;

			@Override
			public User toEntity() {
				return User.builder()
						.email(getEmail())
						.username(getUsername())
						.password(getPassword())
						.authorities(getAuthorities())
						.build();
			}
		}
		
		@EqualsAndHashCode(callSuper = true)
		@Data
		public static class UserUpdate extends AbstractRequest<User> implements EmailField, UsernameField, AuthoritiesField {
			@NotBlank private String email;
			@NotBlank private String username;
			private Set<Role> authorities;
			
			@Override
			public User toEntity() {
				return User.builder()
						.email(getEmail())
						.username(getUsername())
						.authorities(getAuthorities())
						.build();
			}
		}
		
	}
	
}
