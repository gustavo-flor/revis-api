package ogustaflor.com.github.revisapi.service;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.topic.Topic;
import ogustaflor.com.github.revisapi.repository.TopicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TopicService {
	
	private final TopicRepository topicRepository;
	
	public List<Topic> findAll() {
		return topicRepository.findAll();
	}
	
	public Page<Topic> paginate(Pageable pageable) {
		return topicRepository.findAll(pageable);
	}
	
	public Optional<Topic> findById(Long id) {
		return topicRepository.findById(id);
	}
	
	public void deleteById(Long id) {
		topicRepository.deleteById(id);
	}
	
	public Topic insert(Topic topic) {
		if (!topic.isNew())
			throw new UnsupportedOperationException();
		
		return topicRepository.saveAndFlush(topic);
	}
	
	public Topic update(Long id, Topic matter) throws Exception {
		if (!topicRepository.existsById(id)) {
			throw new Exception();
		}
		
		matter.setId(id);
		topicRepository.saveAndFlush(matter);
		
		return matter;
	}
	
}
