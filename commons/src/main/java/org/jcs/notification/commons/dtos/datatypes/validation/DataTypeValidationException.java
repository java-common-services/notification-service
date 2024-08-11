package org.jcs.notification.commons.dtos.datatypes.validation;

import lombok.Data;

import java.util.List;

@Data
public class DataTypeValidationException extends Exception {
  private List<Violations> violations;
}
