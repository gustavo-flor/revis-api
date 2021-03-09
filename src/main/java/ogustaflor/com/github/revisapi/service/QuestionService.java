package ogustaflor.com.github.revisapi.service;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.question.Question;
import ogustaflor.com.github.revisapi.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionService extends AbstractRestService<Question, QuestionRepository> {

	@Override
	public Question insert(Question question) {
		if (!question.isNew())
			throw new UnsupportedOperationException();
		
		question.setReviewed(false);
		
		return repository.saveAndFlush(question);
	}

	@Override
	public Question update(Long id, Question question) throws Exception {
		if (!repository.existsById(id)) {
			throw new Exception();
		}
		
		question.setId(id);
		question.setReviewed(false);
		
		return repository.saveAndFlush(question);
	}
	
	public void review(Long id) {
		repository.findById(id).ifPresent(question -> {
			question.setReviewed(true);

			repository.save(question);
		});
	}
	
}
