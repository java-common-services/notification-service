package org.jcs.notification.commons.dtos.rules;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class GetRuleDto extends PostRuleDto {
  private UUID id;
  private Date createdAt;
  private Date updatedAt;
}
