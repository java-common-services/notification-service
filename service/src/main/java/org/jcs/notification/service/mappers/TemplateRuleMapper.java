package org.jcs.notification.service.mappers;

import org.jcs.notification.commons.dtos.rules.GetRuleDto;
import org.jcs.notification.commons.dtos.rules.PostRuleDto;
import org.jcs.notification.service.database.entities.TemplateRuleEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = TemplateRuleMapper.class)
public interface TemplateRuleMapper {
  TemplateRuleEntity postDtoToEntity(PostRuleDto postRuleDto);

  GetRuleDto entityToGetDto(TemplateRuleEntity templateRuleEntity);

  Iterable<GetRuleDto> entitiesToGetDtos(Iterable<TemplateRuleEntity> templateRuleEntities);
}
