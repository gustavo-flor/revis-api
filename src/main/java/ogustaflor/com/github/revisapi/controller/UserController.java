package ogustaflor.com.github.revisapi.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.user.User;
import ogustaflor.com.github.revisapi.entity.user.UserDTO;
import ogustaflor.com.github.revisapi.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> index() {
		return ResponseEntity.ok(userService.findAll());
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/pageable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<User>> pageable(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
											@RequestParam(name = "size", required = false, defaultValue = "8") int size) {
		return ResponseEntity.ok(userService.paginate(PageRequest.of(page, size)));
	}
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 204, message = "No Content") })
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> show(@PathVariable Long id) {
		return userService.findById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.noContent().build());
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> store(@Valid @RequestBody UserDTO.Request.UserStore body) throws Exception {
		User createdUser = userService.insert(body.toEntity());
		URI location = URI.create(String.format("/users/%s", createdUser.getId()));
		return ResponseEntity.created(location).body(createdUser);
	}
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 204, message = "No Content") })
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> update(@Valid @RequestBody UserDTO.Request.UserUpdate body, @PathVariable Long id) throws Exception {
		return ResponseEntity.ok(userService.update(id, body.toEntity()));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public ResponseEntity<?> destroy(@PathVariable Long id) {
		userService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/is-being-used/{keyword}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> isBeingUsed(@PathVariable String keyword, @RequestParam("isEmail") boolean isEmail) {
		return ResponseEntity.ok(userService.isBeingUsed(keyword, isEmail));
	}
	
}
