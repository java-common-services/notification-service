package org.jcs.notification.service.mappers;


import org.jcs.notification.commons.dtos.GetTemplateParameterDefinitionDto;
import org.jcs.notification.commons.dtos.PostTemplateParameterDefinitionDto;
import org.jcs.notification.service.database.entities.TemplateParameterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface TemplateParameterMapper {
  TemplateParameterEntity postDtoToEntity(PostTemplateParameterDefinitionDto postTemplateParameterDefinitionDto);

  @Named("entityToGetDto")
  PostTemplateParameterDefinitionDto entityToPostDto(TemplateParameterEntity templateParameterEntity);
  GetTemplateParameterDefinitionDto entityToGetDto(TemplateParameterEntity templateParameterEntity);
}
