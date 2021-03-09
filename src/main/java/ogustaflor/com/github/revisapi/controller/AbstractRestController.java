package ogustaflor.com.github.revisapi.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ogustaflor.com.github.revisapi.entity.AbstractRequest;
import ogustaflor.com.github.revisapi.entity.topic.Topic;
import ogustaflor.com.github.revisapi.service.AbstractRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Persistable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

public abstract class AbstractRestController<E extends Persistable<Long>, S extends AbstractRestService<E, ?>, StoreRequest extends AbstractRequest<E>, UpdateRequest extends AbstractRequest<E>> {
	
	protected S service;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public ResponseEntity<List<E>> index() {
		return ResponseEntity.ok(service.findAll());
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/pageable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<E>> pageable(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
											@RequestParam(name = "size", required = false, defaultValue = "8") int size) {
		return ResponseEntity.ok(service.paginate(PageRequest.of(page, size)));
	}

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 204, message = "No Content") })
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<E> show(@PathVariable Long id) {
		return service.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<E> store(@Valid @RequestBody StoreRequest body) throws Exception {
		E created = service.insert(body.toEntity());
		URI location = URI.create(String.format("/%s", created.getId()));
		return ResponseEntity.created(location).body(created);
	}

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 204, message = "No Content") })
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<E> update(@Valid @RequestBody UpdateRequest body, @PathVariable Long id) throws Exception {
		return ResponseEntity.ok(service.update(id, body.toEntity()));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> destroy(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
