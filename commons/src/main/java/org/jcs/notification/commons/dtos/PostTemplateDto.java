package org.jcs.notification.commons.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.jcs.notification.commons.constants.TemplateRenderType;
import org.jcs.notification.commons.constants.TemplateType;
import org.jcs.notification.commons.serializers.DataValidationRuleSerializer;

import java.util.List;

@Data
public class PostTemplateDto {
  private String name;
  private String content;
  private TemplateType templateType;
  private TemplateRenderType templateRenderType;
  private List<PostTemplateParameterDefinitionDto> parameters;
}
