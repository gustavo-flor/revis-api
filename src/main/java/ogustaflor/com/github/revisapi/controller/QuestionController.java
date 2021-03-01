package ogustaflor.com.github.revisapi.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.question.Question;
import ogustaflor.com.github.revisapi.entity.question.QuestionDTO;
import ogustaflor.com.github.revisapi.service.QuestionService;
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
@RequestMapping("/questions")
public class QuestionController {
	
	private final QuestionService questionService;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public ResponseEntity<List<Question>> index() {
		return ResponseEntity.ok(questionService.findAll());
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/pageable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Question>> pageable(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
												@RequestParam(name = "size", required = false, defaultValue = "8") int size) {
		return ResponseEntity.ok(questionService.paginate(PageRequest.of(page, size)));
	}
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 204, message = "No Content") })
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Question> show(@PathVariable Long id) {
		return questionService.findById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.noContent().build());
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Question> store(@Valid @RequestBody QuestionDTO.Request.QuestionStore body) {
		Question createdTopic = questionService.insert(body.toEntity());
		URI location = URI.create(String.format("/topics/%s", createdTopic.getId()));
		return ResponseEntity.created(location).body(createdTopic);
	}
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 204, message = "No Content") })
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Question> update(@Valid @RequestBody QuestionDTO.Request.QuestionUpdate body, @PathVariable Long id) throws Exception {
		return ResponseEntity.ok(questionService.update(id, body.toEntity()));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void destroy(@PathVariable Long id) {
		questionService.deleteById(id);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping(value = "/{id}/review", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void review(@PathVariable Long id) {
		questionService.review(id);
	}
	
}
