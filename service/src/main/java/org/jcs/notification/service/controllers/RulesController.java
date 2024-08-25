package org.jcs.notification.service.controllers;

import jakarta.validation.Valid;
import org.jcs.notification.commons.dtos.rules.GetRuleDto;
import org.jcs.notification.commons.dtos.rules.PostRuleDto;
import org.jcs.notification.service.services.RuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/v1/rules")
public class RulesController {
  private final Logger logger = LoggerFactory.getLogger(RulesController.class);
  @Autowired
  private RuleService ruleService;

  @GetMapping
  public Iterable<GetRuleDto> getAll(
      @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable) {
    Iterable<GetRuleDto> templateRules = ruleService.getAll(pageable);
    logger.info("Got the template rules as: {}", templateRules);
    return templateRules;
  }

  @GetMapping("/{id}")
  public GetRuleDto getById(
      @PathVariable UUID id) {
    GetRuleDto templateRule = ruleService.getById(id);
    logger.info("Got the template rule as: {}", templateRule);
    return templateRule;
  }

  @PostMapping
  public GetRuleDto createRule(
      @Valid @RequestBody PostRuleDto postRuleDto
  ) {
    GetRuleDto templateRule = ruleService.createTemplateRule(postRuleDto);

    logger.info("Created the template rule as: {}", templateRule);
    return templateRule;
  }


}
