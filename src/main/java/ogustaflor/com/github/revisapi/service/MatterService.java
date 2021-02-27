package ogustaflor.com.github.revisapi.service;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.matter.Matter;
import ogustaflor.com.github.revisapi.repository.MatterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MatterService {
	
	private final MatterRepository matterRepository;
	
	public List<Matter> findAll() {
		return matterRepository.findAll();
	}
	
	public Page<Matter> paginate(Pageable pageable) {
		return matterRepository.findAll(pageable);
	}
	
	public Optional<Matter> findById(Long id) {
		return matterRepository.findById(id);
	}
	
	public void deleteById(Long id) {
		matterRepository.deleteById(id);
	}
	
	public Matter insert(Matter matter) throws Exception {
		if (!matter.isNew())
			throw new UnsupportedOperationException();
		
		if (matterRepository.existsByName(matter.getName())) {
			throw new Exception();
		}
		
		return matterRepository.saveAndFlush(matter);
	}
	
	public Matter update(Long id, Matter matter) throws Exception {
		if (!matterRepository.existsById(id)
				|| matterRepository.existsByNameAndIdNot(matter.getName(), id)) {
			throw new Exception();
		}
		
		matter.setId(id);
		matterRepository.saveAndFlush(matter);
		
		return matter;
	}
	
	public boolean isBeingUsed(String name) {
		return matterRepository.existsByName(name);
	}
	
}
