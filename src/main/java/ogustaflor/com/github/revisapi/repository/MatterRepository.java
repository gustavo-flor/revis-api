package ogustaflor.com.github.revisapi.repository;

import ogustaflor.com.github.revisapi.entity.matter.Matter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatterRepository extends JpaRepository<Matter, Long> {
	
	boolean existsByNameAndIdNot(String name, Long id);
	
	boolean existsByName(String name);
	
}
