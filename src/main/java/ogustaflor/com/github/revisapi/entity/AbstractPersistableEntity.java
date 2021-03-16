package ogustaflor.com.github.revisapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

public abstract class AbstractPersistableEntity<ID> implements Persistable<ID>, Serializable {
	
	@JsonIgnore
	@Override
	public boolean isNew() {
		return getId() == null;
	}
	
}
