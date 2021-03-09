package ogustaflor.com.github.revisapi.service;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.user.User;
import ogustaflor.com.github.revisapi.repository.UserRepository;
import ogustaflor.com.github.revisapi.util.PasswordHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService extends AbstractRestService<User, UserRepository> implements UserDetailsService {

	@Override
	public User insert(User user) throws Exception {
		if (!user.isNew())
			throw new UnsupportedOperationException();
		
		if (repository.existsByEmailOrUsername(user.getEmail(), user.getUsername())) {
			throw new Exception();
		}
		
		user.setPassword(PasswordHelper.encode(user.getPassword()));
		return repository.saveAndFlush(user);
	}

	@Override
	public User update(Long id, User user) throws Exception {
		User savedUser = findById(id).orElseThrow(Exception::new);
		
		if (repository.existsByEmailOrUsernameAndIdNot(user.getEmail(), user.getUsername(), id)) {
			throw new Exception();
		}
		
		user.setId(id);
		user.setPassword(savedUser.getPassword());
		
		return repository.saveAndFlush(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
	}
	
	public boolean isBeingUsed(String keyword, boolean isEmail) {
		return isEmail ? repository.existsByEmail(keyword) : repository.existsByUsername(keyword);
	}
	
}
