package org.jcs.notification.service.services;

import org.jcs.notification.commons.dtos.rules.GetRuleDto;
import org.jcs.notification.commons.dtos.rules.PostRuleDto;
import org.jcs.notification.service.database.entities.TemplateRuleEntity;
import org.jcs.notification.service.database.repositories.TemplateRuleRepository;
import org.jcs.notification.service.mappers.TemplateRuleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RuleService {
  private static final Logger logger = LoggerFactory.getLogger(RuleService.class);
  @Autowired
  private TemplateRuleRepository templateRuleRepository;
  @Autowired
  private TemplateRuleMapper templateRuleMapper;

  public GetRuleDto createTemplateRule(PostRuleDto postRuleDto) {
    TemplateRuleEntity templateRuleEntity = templateRuleMapper.postDtoToEntity(postRuleDto);
    templateRuleEntity = templateRuleRepository.save(templateRuleEntity);
    return templateRuleMapper.entityToGetDto(templateRuleEntity);
  }

  public Iterable<GetRuleDto> getAll(Pageable pageable) {
    Page<TemplateRuleEntity> templateRuleEntities = templateRuleRepository.findAll(pageable);

    Iterable<GetRuleDto> getTemplateRuleDtos = templateRuleMapper.entitiesToGetDtos(templateRuleEntities);

    logger.info("Got the template rules as: {}", getTemplateRuleDtos);

    return getTemplateRuleDtos;
  }

  public GetRuleDto getById(UUID id) {
    Optional<TemplateRuleEntity> templateRuleEntity = templateRuleRepository.findById(id);

    if (templateRuleEntity.isEmpty()) {
      // TODO: Add custom exception letter
      throw new RuntimeException(String.format("Template with ID: {0} not found", id));
    }

    GetRuleDto getRuleDto = templateRuleMapper.entityToGetDto(templateRuleEntity.get());

    logger.info("Got the template rule as: {}", getRuleDto);

    return getRuleDto;
  }
}
