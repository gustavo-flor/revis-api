package ogustaflor.com.github.revisapi.controller;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.topic.Topic;
import ogustaflor.com.github.revisapi.entity.topic.TopicDTO;
import ogustaflor.com.github.revisapi.service.TopicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	
	@GetMapping
	public ResponseEntity<List<Topic>> index() {
		return ResponseEntity.ok(topicService.findAll());
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Topic>> pageable(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
												@RequestParam(name = "size", required = false, defaultValue = "8") int size) {
		return ResponseEntity.ok(topicService.paginate(PageRequest.of(page, size)));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Topic> show(@PathVariable Long id) {
		return topicService.findById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.noContent().build());
	}
	
	@PostMapping
	public ResponseEntity<Topic> store(@Valid @RequestBody TopicDTO.Request.Store body) {
		Topic createdTopic = topicService.insert(body.toEntity());
		URI location = URI.create(String.format("/topics/%s", createdTopic.getId()));
		return ResponseEntity.created(location).body(createdTopic);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Topic> update(@Valid @RequestBody TopicDTO.Request.Update body, @PathVariable Long id) throws Exception {
		return ResponseEntity.ok(topicService.update(id, body.toEntity()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> destroy(@PathVariable Long id) {
		topicService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
