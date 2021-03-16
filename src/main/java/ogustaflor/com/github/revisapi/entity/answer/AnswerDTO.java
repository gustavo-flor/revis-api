package ogustaflor.com.github.revisapi.entity.answer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ogustaflor.com.github.revisapi.entity.AbstractRequest;
import ogustaflor.com.github.revisapi.entity.alternative.Alternative;
import ogustaflor.com.github.revisapi.entity.user.User;

import javax.validation.constraints.NotNull;

public class AnswerDTO {

    private interface AlternativeField { Alternative getAlternative(); }
    private interface CreatedByField { User getCreatedBy(); }

    public abstract static class Request {

        @EqualsAndHashCode(callSuper = true)
        @Data
        public static class AnswerStore extends AbstractRequest<Answer> implements AlternativeField, CreatedByField {
            @NotNull private Alternative alternative;
            @NotNull private User createdBy;

            @Override
            public Answer toEntity() {
                Answer answer = Answer.builder()
                        .alternative(getAlternative())
                        .build();
                answer.setCreatedBy(getCreatedBy());
                return answer;
            }
        }

        @EqualsAndHashCode(callSuper = true)
        @Data
        public static class MatterUpdate extends AbstractRequest<Answer> implements AlternativeField, CreatedByField {
            @NotNull private Alternative alternative;
            @NotNull private User createdBy;

            @Override
            public Answer toEntity() {
                Answer answer = Answer.builder()
                        .alternative(getAlternative())
                        .build();
                answer.setCreatedBy(getCreatedBy());
                return answer;
            }
        }

    }

}
