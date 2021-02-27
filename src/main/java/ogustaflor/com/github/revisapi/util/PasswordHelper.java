package ogustaflor.com.github.revisapi.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHelper {
	
	private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	public static BCryptPasswordEncoder getEncoder() {
		return bCryptPasswordEncoder;
	}
	
	public static String encode(String value) {
		return bCryptPasswordEncoder.encode(value);
	}
	
}
