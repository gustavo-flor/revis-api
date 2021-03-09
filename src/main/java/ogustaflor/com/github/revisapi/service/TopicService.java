package ogustaflor.com.github.revisapi.service;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.topic.Topic;
import ogustaflor.com.github.revisapi.repository.TopicRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TopicService extends AbstractRestService<Topic, TopicRepository> {

	@Override
	public Topic insert(Topic topic) {
		if (!topic.isNew())
			throw new UnsupportedOperationException();
		
		return repository.saveAndFlush(topic);
	}

	@Override
	public Topic update(Long id, Topic matter) throws Exception {
		if (!repository.existsById(id)) {
			throw new Exception();
		}
		
		matter.setId(id);
		
		return repository.saveAndFlush(matter);
	}
	
}
