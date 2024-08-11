package org.jcs.notification.commons.dtos.datatypes.validation;

import lombok.Data;

@Data
public class StringValidationRule extends DataValidationRule {
  private int minLength;
  private int maxLength;
  private String regexToValidate;

  @Override
  public boolean validate() throws DataTypeValidationException {
    return false;
  }
}
