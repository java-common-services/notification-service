package org.jcs.notification.service.mappers;

import org.jcs.notification.commons.dtos.GetTemplateDto;
import org.jcs.notification.commons.dtos.PostTemplateDto;
import org.jcs.notification.commons.dtos.PostTemplateParameterDefinitionDto;
import org.jcs.notification.service.database.entities.TemplateEntity;
import org.jcs.notification.service.database.entities.TemplateParameterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = TemplateParameterMapper.class)
public interface TemplateMapper {
  TemplateEntity postDtoToEntity(PostTemplateDto postTemplateDto);
  GetTemplateDto entityToGetDto(TemplateEntity templateEntity);
  Iterable<GetTemplateDto> entitiesToGetDtos(Iterable<TemplateEntity> templateEntities);

  @Mapping(source = "parameterEntities", target = "parameterDtos", qualifiedByName = "entityToPostDto")
  List<PostTemplateParameterDefinitionDto> mapParameterEntitiesToDtos(List<TemplateParameterEntity> parameterEntities);
}
