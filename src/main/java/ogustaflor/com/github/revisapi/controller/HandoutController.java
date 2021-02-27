package ogustaflor.com.github.revisapi.controller;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.handout.Handout;
import ogustaflor.com.github.revisapi.service.HandoutService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/handouts")
public class HandoutController {
	
	private final HandoutService handoutService;
	
	@GetMapping
	public ResponseEntity<List<Handout>> index() {
		return ResponseEntity.ok(handoutService.findAll());
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Handout>> pageable(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
												  @RequestParam(name = "size", required = false, defaultValue = "8") int size) {
		return ResponseEntity.ok(handoutService.paginate(PageRequest.of(page, size)));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Handout> show(@PathVariable Long id) {
		return handoutService.findById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.noContent().build());
	}
	
	@PostMapping
	public ResponseEntity<Handout> store(@RequestBody Handout handout) throws Exception {
		Handout createdHandout = handoutService.insert(handout);
		URI location = URI.create(String.format("/handouts/%s", createdHandout.getId()));
		return ResponseEntity.created(location).body(createdHandout);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Handout> update(@RequestBody Handout handout, @PathVariable Long id) throws Exception {
		return ResponseEntity.ok(handoutService.update(id, handout));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> destroy(@PathVariable Long id) {
		handoutService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/is-being-used/{title}")
	public ResponseEntity<Boolean> isBeingUsed(@PathVariable String title) {
		return ResponseEntity.ok(handoutService.isBeingUsed(title));
	}
	
}
