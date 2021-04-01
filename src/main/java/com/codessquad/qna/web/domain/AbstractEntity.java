package com.codessquad.qna.web.domain;

import com.codessquad.qna.web.utility.TimeConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;

    @CreatedDate
    @JsonProperty
    private LocalDateTime writtenDateTime = LocalDateTime.now();

    @LastModifiedDate
    private LocalDateTime modifiedDateTime;

    public String getFormattedWrittenDateTime() {
        return getFormattedDateTime(writtenDateTime);
    }

    public String getFormattedModifiedDateTime() {
        return getFormattedDateTime(modifiedDateTime);
    }

    private String getFormattedDateTime(LocalDateTime dateTime) {
        if(dateTime == null) {
            return "";
        }
        return dateTime.format(TimeConstant.DATE_PATTERN);
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
