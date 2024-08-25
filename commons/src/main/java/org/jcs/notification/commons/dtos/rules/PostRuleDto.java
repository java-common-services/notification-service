package org.jcs.notification.commons.dtos.rules;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PostRuleDto {

  @NotEmpty
  private String eventName;
  @NotEmpty
  private String description;

  @NotEmpty
  private List<UUID> templateIds;
}
