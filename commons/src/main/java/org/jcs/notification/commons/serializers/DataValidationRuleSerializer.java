package org.jcs.notification.commons.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jcs.notification.commons.dtos.PostTemplateParameterDefinitionDto;
import org.jcs.notification.commons.dtos.datatypes.DataType;
import org.jcs.notification.commons.dtos.datatypes.validation.DataValidationRule;
import org.jcs.notification.commons.dtos.datatypes.validation.IntDataValidationRule;
import org.jcs.notification.commons.dtos.datatypes.validation.StringValidationRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DataValidationRuleSerializer extends JsonDeserializer<PostTemplateParameterDefinitionDto> {

  Logger logger = LoggerFactory.getLogger(DataValidationRuleSerializer.class);

  @Override
  public PostTemplateParameterDefinitionDto deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    ObjectMapper mapper = (ObjectMapper) p.getCodec();
    JsonNode root = mapper.readTree(p);

    // Extract fields from the root JSON node
    JsonNode dataTypeNode = root.get("dataType");
    if (dataTypeNode == null) {
      throw new IllegalArgumentException("dataType field is missing");
    }

    DataType dataType = mapper.treeToValue(dataTypeNode, DataType.class);

    // Deserialize the non-dataValidationRule fields of the DTO
    PostTemplateParameterDefinitionDto tempDto = new PostTemplateParameterDefinitionDto();
    tempDto.setName(root.get("name").asText());
    tempDto.setDataType(dataType);

    // Deserialize the dataValidationRule field separately
    JsonNode ruleNode = root.get("dataValidationRule");
    if (ruleNode == null) {
      throw new IllegalArgumentException("dataValidationRule field is missing");
    }

    Class<? extends DataValidationRule> targetClass;
    switch (dataType) {
      case INTEGER:
        targetClass = IntDataValidationRule.class;
        break;
      case STRING:
        targetClass = StringValidationRule.class;
        break;
      // Add cases for other DataType values
      default:
        throw new IllegalArgumentException("Unknown data type: " + dataType);
    }

    DataValidationRule rule = mapper.treeToValue(ruleNode, targetClass);
    tempDto.setDataValidationRule(rule);

    return tempDto;
  }
}
