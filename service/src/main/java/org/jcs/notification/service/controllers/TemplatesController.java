package org.jcs.notification.service.controllers;

import org.jcs.notification.commons.dtos.GetTemplateDto;
import org.jcs.notification.commons.dtos.PostTemplateDto;
import org.jcs.notification.service.mappers.TemplateMapper;
import org.jcs.notification.service.services.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/templates")
public class TemplatesController {

  @Autowired
  private TemplateService templateService;

  @Autowired
  private TemplateMapper templateMapper;
  Logger logger = LoggerFactory.getLogger(TemplatesController.class);

  @GetMapping("/{id}")
  public GetTemplateDto getById(
      @PathVariable UUID id) {
    GetTemplateDto template = templateService.getById(id);
    logger.info("Got the template as: {}", template);
    return template;
  }

  @GetMapping()
  public Iterable<GetTemplateDto> getAll(
      @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable) {
    Iterable<GetTemplateDto> templates = templateService.getAll(pageable);
    logger.info("Got the templates as: {}", templates);
    return templates;
  }

  @PostMapping
  public GetTemplateDto createTemplate(
      @RequestBody PostTemplateDto postTemplateDto
      ) {
    GetTemplateDto template = templateService.createTemplate(postTemplateDto);

    logger.info("Created the template as: {}", template);
    return template;
  }

}
