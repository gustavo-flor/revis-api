package ogustaflor.com.github.revisapi.entity.answer;

import lombok.*;
import ogustaflor.com.github.revisapi.entity.AbstractAuditableEntity;
import ogustaflor.com.github.revisapi.entity.alternative.Alternative;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@Table(name = "answers")
public class Answer extends AbstractAuditableEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alternative_id", nullable = false)
    private Alternative alternative;

}
