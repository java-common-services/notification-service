package org.jcs.notification.service.services;

import org.jcs.notification.commons.dtos.GetTemplateDto;
import org.jcs.notification.commons.dtos.PostTemplateDto;
import org.jcs.notification.service.database.entities.TemplateEntity;
import org.jcs.notification.service.database.repositories.TemplateRepository;
import org.jcs.notification.service.mappers.TemplateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TemplateService {
  @Autowired
  private TemplateRepository templateRepository;

  @Autowired
  private TemplateMapper templateMapper;

  Logger logger = LoggerFactory.getLogger(TemplateService.class);

  public GetTemplateDto getById(UUID uuid) {
    Optional<TemplateEntity> template = templateRepository.findById(uuid);

    if (template.isEmpty()) {
      throw new RuntimeException("Template not found");
    }

    GetTemplateDto getTemplateDto = templateMapper.entityToGetDto(template.get());

    logger.info("Converted template to get DTO as: {}", getTemplateDto);

    return getTemplateDto;
  }

  public Iterable<GetTemplateDto> getAll(Pageable pageable) {

    Iterable<TemplateEntity> templateEntities = templateRepository.findAll(pageable);

    Iterable<GetTemplateDto> getTemplateDtos = templateMapper.entitiesToGetDtos(templateEntities);

    logger.info("Converted templates to get DTOs as: {}", getTemplateDtos);

    return getTemplateDtos;
  }

  public GetTemplateDto createTemplate(PostTemplateDto postTemplateDto) {
    TemplateEntity templateEntity = templateMapper.postDtoToEntity(postTemplateDto);

    templateEntity = templateRepository.save(templateEntity);

    logger.info("Created template as: {}", templateEntity);

    GetTemplateDto getTemplateDto = templateMapper.entityToGetDto(templateEntity);

    logger.info("Created template as: {}", getTemplateDto);
    return getTemplateDto;
  }
}
