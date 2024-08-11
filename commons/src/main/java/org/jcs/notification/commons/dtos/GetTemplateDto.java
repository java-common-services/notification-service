package org.jcs.notification.commons.dtos;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class GetTemplateDto extends PostTemplateDto {
  private UUID id;
  private Date createdAt;
  private Date updatedAt;
  // TODO: Somehow hide postTemplateParameters and introduce GetTemplateParameter here
}
