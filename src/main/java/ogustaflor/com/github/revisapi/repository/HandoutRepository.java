package ogustaflor.com.github.revisapi.repository;

import ogustaflor.com.github.revisapi.entity.handout.Handout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HandoutRepository extends JpaRepository<Handout, Long> {
	
	boolean existsByTitle(String title);
	
	boolean existsByTitleAndIdNot(String title, Long id);
	
}
