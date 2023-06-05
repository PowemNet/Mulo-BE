package co.powem.mulo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
@JsonIgnoreProperties(
    value = {"created_date, updated_date"},
    allowGetters = true)
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U, T> {

  @Column(name = "created_by", updatable = false)
  @CreatedBy
  protected U createdBy;

  @Column(name = "created_date", nullable = false, updatable = false)
  @CreatedDate
  protected T createdDate;

  @Column(name = "updated_by")
  @LastModifiedBy
  protected U updatedBy;

  @Column(name = "updated_date", nullable = false)
  @LastModifiedDate
  protected T updatedDate;
}
