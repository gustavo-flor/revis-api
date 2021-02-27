package ogustaflor.com.github.revisapi.service;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.handout.Handout;
import ogustaflor.com.github.revisapi.repository.HandoutRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HandoutService {
	
	private final HandoutRepository handoutRepository;
	
	public List<Handout> findAll() {
		return handoutRepository.findAll();
	}
	
	public Page<Handout> paginate(Pageable pageable) {
		return handoutRepository.findAll(pageable);
	}
	
	public Optional<Handout> findById(Long id) {
		return handoutRepository.findById(id);
	}
	
	public void deleteById(Long id) {
		handoutRepository.deleteById(id);
	}
	
	public Handout insert(Handout handout) throws Exception {
		if (!handout.isNew())
			throw new UnsupportedOperationException();
		
		if (handoutRepository.existsByTitle(handout.getTitle())) {
			throw new Exception();
		}
		
		return handoutRepository.saveAndFlush(handout);
	}
	
	public Handout update(Long id, Handout handout) throws Exception {
		if (!handoutRepository.existsById(id) || handoutRepository.existsByTitleAndIdNot(handout.getTitle(), id)) {
			throw new Exception();
		}
		
		handout.setId(id);
		handoutRepository.saveAndFlush(handout);
		
		return handout;
	}
	
	public boolean isBeingUsed(String title) {
		return handoutRepository.existsByTitle(title);
	}
	
}
