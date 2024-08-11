package org.jcs.notification.commons.dtos.datatypes.validation;

import lombok.Data;

@Data
public class IntDataValidationRule extends DataValidationRule {
  private int min;
  private int max;

  @Override
  public boolean validate() throws DataTypeValidationException {
    return false;
  }
}
