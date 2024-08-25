package org.jcs.notification.service.database.entities;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "template_rule")
public class TemplateRuleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @JdbcTypeCode(SqlTypes.VARCHAR)
  private UUID id;

  @Column
  private String eventName;

  @Column
  private String description;

  @Column
  private Date createdAt;

  @Column
  private Date updatedAt;

  @ElementCollection
  @CollectionTable(name = "template_rule_template_mapping",
      joinColumns = @JoinColumn(name = "template_rule_id"))
  @Column(name = "template_id")
  @JdbcTypeCode(SqlTypes.VARCHAR)
  private Set<UUID> templateIds;
}
