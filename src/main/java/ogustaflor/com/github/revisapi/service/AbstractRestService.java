package ogustaflor.com.github.revisapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractRestService<E extends Persistable<Long>, R extends JpaRepository<E, Long>> {
	
	protected R repository;
	
	public List<E> findAll() {
		return repository.findAll();
	}
	
	public Page<E> paginate(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	public Optional<E> findById(Long id) {
		return repository.findById(id);
	}
	
	public E insert(E entity) {
		if (entity.isNew()) {
			throw new UnsupportedOperationException();
		}
		
		return repository.saveAndFlush(entity);
	}
	
	public E update(Long id, E entity) {
		if (!repository.existsById(id)) {
			throw new UnsupportedOperationException();
		}
		
		return repository.saveAndFlush(entity);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
}
