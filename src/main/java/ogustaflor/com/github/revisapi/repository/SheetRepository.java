package ogustaflor.com.github.revisapi.repository;

import ogustaflor.com.github.revisapi.entity.sheet.Sheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SheetRepository extends JpaRepository<Sheet, Long> {
	
	boolean existsByTitle(String title);
	
	boolean existsByTitleAndIdNot(String title, Long id);
	
}
