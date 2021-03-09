package ogustaflor.com.github.revisapi.controller;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.question.Question;
import ogustaflor.com.github.revisapi.entity.question.QuestionDTO;
import ogustaflor.com.github.revisapi.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/questions")
public class QuestionController extends AbstractRestController<Question, QuestionService, QuestionDTO.Request.QuestionStore, QuestionDTO.Request.QuestionUpdate> {
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping(value = "/{id}/review", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> review(@PathVariable Long id) {
		service.review(id);
		return ResponseEntity.noContent().build();
	}
	
}
