package org.jcs.notification.commons.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class GetTemplateParameterDefinitionDto extends PostTemplateParameterDefinitionDto {
  private UUID id;
}
