package org.jcs.notification.commons.dtos.datatypes.validation;

import lombok.Data;

@Data
public class FloatDataValidationRule extends DataValidationRule {
  private float min;
  private float max;

  @Override
  public boolean validate() throws DataTypeValidationException {
    return false;
  }
}
