package ogustaflor.com.github.revisapi.service;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.user.User;
import ogustaflor.com.github.revisapi.repository.UserRepository;
import ogustaflor.com.github.revisapi.util.PasswordHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public Page<User> paginate(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
	
	public User insert(User user) throws Exception {
		if (!user.isNew())
			throw new UnsupportedOperationException();
		
		if (userRepository.existsByEmailOrUsername(user.getEmail(), user.getUsername())) {
			throw new Exception();
		}
		
		user.setPassword(PasswordHelper.encode(user.getPassword()));
		return userRepository.saveAndFlush(user);
	}
	
	public User update(Long id, User user) throws Exception {
		User savedUser = findById(id).orElseThrow(Exception::new);
		
		if (userRepository.existsByEmailOrUsernameAndIdNot(user.getEmail(), user.getUsername(), id)) {
			throw new Exception();
		}
		
		user.setId(id);
		user.setPassword(savedUser.getPassword());
		
		userRepository.saveAndFlush(user);
		
		return user;
	}
	
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
	}
	
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
	
	public boolean isBeingUsed(String keyword, boolean isEmail) {
		return isEmail
				? userRepository.existsByEmail(keyword)
				: userRepository.existsByUsername(keyword);
	}
	
}
