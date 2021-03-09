package ogustaflor.com.github.revisapi.entity;

import org.springframework.data.domain.Persistable;

public abstract class AbstractRequest<E extends Persistable<Long>> {

    public abstract E toEntity();

}
