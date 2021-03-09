package ogustaflor.com.github.revisapi.controller;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.user.User;
import ogustaflor.com.github.revisapi.entity.user.UserDTO;
import ogustaflor.com.github.revisapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController extends AbstractRestController<User, UserService, UserDTO.Request.UserStore, UserDTO.Request.UserUpdate> {
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/is-being-used/{keyword}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> isBeingUsed(@PathVariable String keyword, @RequestParam("isEmail") boolean isEmail) {
		return ResponseEntity.ok(service.isBeingUsed(keyword, isEmail));
	}
	
}
