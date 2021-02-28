package ogustaflor.com.github.revisapi.entity.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Set;

public abstract class UserDTO {
	
	private interface IdField { @Positive Long getId(); }
	private interface EmailField { @NotBlank String getEmail(); }
	private interface UsernameField { @NotBlank String getUsername(); }
	private interface PasswordField { @NotBlank String getPassword(); }
	private interface AuthoritiesField { Set<Role> getAuthorities(); }
	
	private interface ToEntityMethod { User toEntity(); }
	
	public abstract static class Request {
		
		@Data
		public static class UserStore implements EmailField, UsernameField, PasswordField, AuthoritiesField, ToEntityMethod {
			@NotBlank String email;
			@NotBlank String username;
			@NotBlank String password;
			Set<Role> authorities;
			
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
		
		@Data
		public static class UserUpdate implements EmailField, UsernameField, AuthoritiesField, ToEntityMethod {
			@NotBlank String email;
			@NotBlank String username;
			Set<Role> authorities;
			
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
