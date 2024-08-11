package org.jcs.notification.service.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.sql.Template;
import org.hibernate.type.SqlTypes;
import org.jcs.notification.commons.dtos.datatypes.DataType;
import org.jcs.notification.commons.dtos.datatypes.validation.DataValidationRule;
import org.jcs.notification.service.database.converters.DataValidationRuleConverter;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "template_parameter")
public class TemplateParameterEntity {
  @Id
  @GeneratedValue(strategy= GenerationType.UUID)
  @JdbcTypeCode(SqlTypes.VARCHAR)
  private UUID id;

  @Column
  private String name;

  @Column
  @Enumerated(EnumType.STRING)
  private DataType dataType;

  @Column
  @Convert(converter = DataValidationRuleConverter.class)
  private DataValidationRule dataValidationRule;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="template_id")
  private TemplateEntity templateEntity;

  @Column
  private Date createdAt;

  @Column
  private Date updatedAt;
}
