package ogustaflor.com.github.revisapi.controller;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.matter.Matter;
import ogustaflor.com.github.revisapi.entity.matter.MatterDTO;
import ogustaflor.com.github.revisapi.service.MatterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/matters")
public class MatterController {
	
	private final MatterService matterService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Matter>> index() {
		return ResponseEntity.ok(matterService.findAll());
	}
	
	@GetMapping(value = "/pageable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Matter>> pageable(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
												 @RequestParam(name = "size", required = false, defaultValue = "8") int size) {
		return ResponseEntity.ok(matterService.paginate(PageRequest.of(page, size)));
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Matter> show(@PathVariable Long id) {
		return matterService.findById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.noContent().build());
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Matter> store(@Valid @RequestBody MatterDTO.Request.MatterStore body) throws Exception {
		Matter createdMatter = matterService.insert(body.toEntity());
		URI location = URI.create(String.format("/matters/%s", createdMatter.getId()));
		return ResponseEntity.created(location).body(createdMatter);
	}
	
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Matter> update(@Valid @RequestBody MatterDTO.Request.MatterUpdate body, @PathVariable Long id) throws Exception {
		return ResponseEntity.ok(matterService.update(id, body.toEntity()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> destroy(@PathVariable Long id) {
		matterService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/is-being-used/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> isBeingUsed(@PathVariable String name) {
		return ResponseEntity.ok(matterService.isBeingUsed(name));
	}
	
}
