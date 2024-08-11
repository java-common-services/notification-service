package org.jcs.notification.commons.dtos.datatypes.validation;

public abstract class DataValidationRule {
  public abstract boolean validate() throws DataTypeValidationException;
}
