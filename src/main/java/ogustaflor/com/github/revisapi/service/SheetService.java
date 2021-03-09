package ogustaflor.com.github.revisapi.service;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.sheet.Sheet;
import ogustaflor.com.github.revisapi.repository.SheetRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SheetService extends AbstractRestService<Sheet, SheetRepository> {

	@Override
	public Sheet insert(Sheet sheet) throws Exception {
		if (!sheet.isNew())
			throw new UnsupportedOperationException();
		
		if (repository.existsByTitle(sheet.getTitle())) {
			throw new Exception();
		}
		
		return repository.saveAndFlush(sheet);
	}

	@Override
	public Sheet update(Long id, Sheet sheet) throws Exception {
		if (!repository.existsById(id) || repository.existsByTitleAndIdNot(sheet.getTitle(), id)) {
			throw new Exception();
		}
		
		return repository.saveAndFlush(sheet);
	}
	
	public boolean isBeingUsed(String title) {
		return repository.existsByTitle(title);
	}
	
}
