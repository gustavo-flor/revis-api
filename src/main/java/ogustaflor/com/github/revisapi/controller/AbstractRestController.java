package ogustaflor.com.github.revisapi.controller;

import ogustaflor.com.github.revisapi.service.AbstractRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Persistable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AbstractRestController<E extends Persistable<Long>, S extends AbstractRestService<E, ?>> {
	
	protected S service;

	@GetMapping
	public ResponseEntity<List<E>> index() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<E>> pageable(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
											@RequestParam(name = "size", required = false, defaultValue = "8") int size) {
		return ResponseEntity.ok(service.paginate(PageRequest.of(page, size)));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<E> show(@PathVariable Long id) {
		return service.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
	}
	
	@PostMapping
	public ResponseEntity<E> store(Class<?> dto) {
		return null;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<E> update(Class<?> dto) {
		return null;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> destroy(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
