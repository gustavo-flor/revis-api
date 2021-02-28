package ogustaflor.com.github.revisapi.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.topic.Topic;
import ogustaflor.com.github.revisapi.entity.topic.TopicDTO;
import ogustaflor.com.github.revisapi.service.TopicService;
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
@RequestMapping("/topics")
public class TopicController {
	
	private final TopicService topicService;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public ResponseEntity<List<Topic>> index() {
		return ResponseEntity.ok(topicService.findAll());
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/pageable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Topic>> pageable(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
												@RequestParam(name = "size", required = false, defaultValue = "8") int size) {
		return ResponseEntity.ok(topicService.paginate(PageRequest.of(page, size)));
	}
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 204, message = "No Content") })
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Topic> show(@PathVariable Long id) {
		return topicService.findById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.noContent().build());
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Topic> store(@Valid @RequestBody TopicDTO.Request.TopicStore body) {
		Topic createdTopic = topicService.insert(body.toEntity());
		URI location = URI.create(String.format("/topics/%s", createdTopic.getId()));
		return ResponseEntity.created(location).body(createdTopic);
	}
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 204, message = "No Content") })
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Topic> update(@Valid @RequestBody TopicDTO.Request.TopicUpdate body, @PathVariable Long id) throws Exception {
		return ResponseEntity.ok(topicService.update(id, body.toEntity()));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public ResponseEntity<?> destroy(@PathVariable Long id) {
		topicService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
