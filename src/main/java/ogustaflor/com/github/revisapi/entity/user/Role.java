package ogustaflor.com.github.revisapi.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
public enum Role implements GrantedAuthority {
	
	WRITER("write"),
	REVIEWER("review"),
	PUBLISHER("publish");
	
	private String authority;

}
