package org.jcs.notification.commons.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.jcs.notification.commons.dtos.datatypes.DataType;
import org.jcs.notification.commons.dtos.datatypes.validation.DataValidationRule;
import org.jcs.notification.commons.serializers.DataValidationRuleSerializer;

@Data
@JsonDeserialize(using = DataValidationRuleSerializer.class)
public class PostTemplateParameterDefinitionDto {
  private String name;
  private DataType dataType;
  private DataValidationRule dataValidationRule;
  private boolean required;
}
