package ogustaflor.com.github.revisapi.service;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.sheet.Sheet;
import ogustaflor.com.github.revisapi.repository.SheetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SheetService {
	
	private final SheetRepository sheetRepository;
	
	public List<Sheet> findAll() {
		return sheetRepository.findAll();
	}
	
	public Page<Sheet> paginate(Pageable pageable) {
		return sheetRepository.findAll(pageable);
	}
	
	public Optional<Sheet> findById(Long id) {
		return sheetRepository.findById(id);
	}
	
	public void deleteById(Long id) {
		sheetRepository.deleteById(id);
	}
	
	public Sheet insert(Sheet sheet) throws Exception {
		if (!sheet.isNew())
			throw new UnsupportedOperationException();
		
		if (sheetRepository.existsByTitle(sheet.getTitle())) {
			throw new Exception();
		}
		
		return sheetRepository.saveAndFlush(sheet);
	}
	
	public Sheet update(Long id, Sheet sheet) throws Exception {
		if (!sheetRepository.existsById(id) || sheetRepository.existsByTitleAndIdNot(sheet.getTitle(), id)) {
			throw new Exception();
		}
		
		sheet.setId(id);
		sheetRepository.saveAndFlush(sheet);
		
		return sheet;
	}
	
	public boolean isBeingUsed(String title) {
		return sheetRepository.existsByTitle(title);
	}
	
}
