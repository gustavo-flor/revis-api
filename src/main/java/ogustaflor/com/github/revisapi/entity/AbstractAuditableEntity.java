package ogustaflor.com.github.revisapi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ogustaflor.com.github.revisapi.entity.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public abstract class AbstractAuditableEntity<ID> extends AbstractPersistableEntity<ID> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

}
