package ogustaflor.com.github.revisapi.controller;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.matter.Matter;
import ogustaflor.com.github.revisapi.service.MatterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/matters")
public class MatterController {
	
	private final MatterService matterService;
	
	@GetMapping
	public ResponseEntity<List<Matter>> index() {
		return ResponseEntity.ok(matterService.findAll());
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Matter>> pageable(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
												 @RequestParam(name = "size", required = false, defaultValue = "8") int size) {
		return ResponseEntity.ok(matterService.paginate(PageRequest.of(page, size)));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Matter> show(@PathVariable Long id) {
		return matterService.findById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.noContent().build());
	}
	
	@PostMapping
	public ResponseEntity<Matter> store(@RequestBody Matter matter) throws Exception {
		Matter createdMatter = matterService.insert(matter);
		URI location = URI.create(String.format("/matters/%s", createdMatter.getId()));
		return ResponseEntity.created(location).body(createdMatter);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Matter> update(@RequestBody Matter matter, @PathVariable Long id) throws Exception {
		return ResponseEntity.ok(matterService.update(id, matter));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> destroy(@PathVariable Long id) {
		matterService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/is-being-used/{name}")
	public ResponseEntity<Boolean> isBeingUsed(@PathVariable String name) {
		return ResponseEntity.ok(matterService.isBeingUsed(name));
	}
	
}
