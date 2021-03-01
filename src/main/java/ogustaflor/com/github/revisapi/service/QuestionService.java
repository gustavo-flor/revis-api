package ogustaflor.com.github.revisapi.service;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.matter.Matter;
import ogustaflor.com.github.revisapi.entity.question.Question;
import ogustaflor.com.github.revisapi.repository.MatterRepository;
import ogustaflor.com.github.revisapi.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	
	public List<Question> findAll() {
		return questionRepository.findAll();
	}
	
	public Page<Question> paginate(Pageable pageable) {
		return questionRepository.findAll(pageable);
	}
	
	public Optional<Question> findById(Long id) {
		return questionRepository.findById(id);
	}
	
	public void deleteById(Long id) {
		questionRepository.deleteById(id);
	}
	
	public Question insert(Question question) {
		if (!question.isNew())
			throw new UnsupportedOperationException();
		
		question.setReviewed(false);
		
		return questionRepository.saveAndFlush(question);
	}
	
	public Question update(Long id, Question question) throws Exception {
		if (!questionRepository.existsById(id)) {
			throw new Exception();
		}
		
		question.setId(id);
		question.setReviewed(false);
		
		return questionRepository.saveAndFlush(question);
	}
	
	public void review(Long id) {
		questionRepository.findById(id).ifPresent(question -> {
			question.setReviewed(true);
			
			questionRepository.save(question);
		});
	}
	
}
