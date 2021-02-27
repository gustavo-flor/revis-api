package ogustaflor.com.github.revisapi.repository;

import ogustaflor.com.github.revisapi.entity.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
