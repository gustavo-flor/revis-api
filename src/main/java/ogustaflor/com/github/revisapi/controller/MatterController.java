package ogustaflor.com.github.revisapi.controller;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.matter.Matter;
import ogustaflor.com.github.revisapi.entity.matter.MatterDTO;
import ogustaflor.com.github.revisapi.service.MatterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/matters")
public class MatterController extends AbstractRestController<Matter, MatterService, MatterDTO.Request.MatterStore, MatterDTO.Request.MatterUpdate> {
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/is-being-used/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> isBeingUsed(@PathVariable String name) {
		return ResponseEntity.ok(service.isBeingUsed(name));
	}
	
}
