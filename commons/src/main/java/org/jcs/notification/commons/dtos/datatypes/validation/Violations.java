package org.jcs.notification.commons.dtos.datatypes.validation;

import lombok.Data;

@Data
public class Violations {
  private String error;
  private String userFriendlyMessage;
}
