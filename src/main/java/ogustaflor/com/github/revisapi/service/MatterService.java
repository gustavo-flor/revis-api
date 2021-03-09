package ogustaflor.com.github.revisapi.service;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.revisapi.entity.matter.Matter;
import ogustaflor.com.github.revisapi.repository.MatterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MatterService extends AbstractRestService<Matter, MatterRepository> {

	@Override
	public Matter insert(Matter matter) throws Exception {
		if (!matter.isNew())
			throw new UnsupportedOperationException();
		
		if (repository.existsByName(matter.getName())) {
			throw new Exception();
		}
		
		return repository.saveAndFlush(matter);
	}

	@Override
	public Matter update(Long id, Matter matter) throws Exception {
		if (!repository.existsById(id)
				|| repository.existsByNameAndIdNot(matter.getName(), id)) {
			throw new Exception();
		}
		
		matter.setId(id);
		
		return repository.saveAndFlush(matter);
	}
	
	public boolean isBeingUsed(String name) {
		return repository.existsByName(name);
	}
	
}
