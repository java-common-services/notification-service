package org.jcs.notification.service.database.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.jcs.notification.commons.constants.TemplateRenderType;
import org.jcs.notification.commons.constants.TemplateType;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "template")
public class TemplateEntity {
  @Id
  @GeneratedValue(strategy= GenerationType.UUID)
  @JdbcTypeCode(SqlTypes.VARCHAR)
  private UUID id;

  @Column
  private String name;

  @Column
  private String content;

  @Column
  @Enumerated(EnumType.STRING)
  private TemplateType templateType;

  @Column
  @Enumerated(EnumType.STRING)
  private TemplateRenderType templateRenderType;

  @OneToMany(mappedBy = "templateEntity", orphanRemoval=true, cascade = CascadeType.ALL)
  private List<TemplateParameterEntity> parameters;

  @Column
  private Date createdAt;

  @Column
  private Date updatedAt;

  @PrePersist
  @PreUpdate
  private void ensureRelationships() {
    for (TemplateParameterEntity parameter : parameters) {
      parameter.setTemplateEntity(this);
    }
  }
}
