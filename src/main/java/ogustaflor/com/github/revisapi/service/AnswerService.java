package ogustaflor.com.github.revisapi.service;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.answer.Answer;
import ogustaflor.com.github.revisapi.repository.AnswerRepository;
import ogustaflor.com.github.revisapi.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository repository;

    public Answer insert(Answer answer) {
        if (!answer.isNew())
            throw new UnsupportedOperationException();



        return repository.saveAndFlush(answer);
    }

}
