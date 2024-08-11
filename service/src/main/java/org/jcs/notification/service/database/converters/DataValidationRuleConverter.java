package org.jcs.notification.service.database.converters;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.SneakyThrows;
import org.jcs.notification.commons.dtos.datatypes.validation.DataValidationRule;

@Converter
public class DataValidationRuleConverter implements AttributeConverter<DataValidationRule, String> {

  private final ObjectMapper objectMapper;

  public DataValidationRuleConverter() {
    objectMapper = new ObjectMapper();
    objectMapper.activateDefaultTyping(
        LaissezFaireSubTypeValidator.instance,
        ObjectMapper.DefaultTyping.NON_FINAL,
        JsonTypeInfo.As.PROPERTY
    );
  }
  @SneakyThrows
  @Override
  public String convertToDatabaseColumn(DataValidationRule attribute) {
    try {
      return objectMapper.writeValueAsString(attribute);
    } catch (Exception e) {
      // Handle exception
      throw e;
    }
  }

  @SneakyThrows
  @Override
  public DataValidationRule convertToEntityAttribute(String dbData) {
    try {
      return objectMapper.readValue(dbData, DataValidationRule.class);
    } catch (Exception e) {
      // Handle exception
      throw e;
    }
  }
}
