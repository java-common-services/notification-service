package org.jcs.notification.service.mappers;

import org.jcs.notification.commons.dtos.rules.PostRuleDto;
import org.jcs.notification.service.database.entities.TemplateRuleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {TemplateRuleMapper.class, TemplateRuleMapperImpl.class})
class TemplateRuleMapperTest {
  private static final UUID TEMPLATE_ID_1 = UUID.fromString("331393cd-bc65-4069-9c89-cb8194b47268");
  private static final String EVENT_NAME = "UserOnboarded";
  private static final String RULE_DESCRIPTION = "Rule that gets executed when the user is onboarded";
  @Autowired
  private TemplateRuleMapper templateRuleMapper;

  @Test
  void testPostDtoToEntity() {
    // Setup
    PostRuleDto postRuleDto = createDummyPostTemplateRuleDto();

    // Execute
    TemplateRuleEntity output = templateRuleMapper.postDtoToEntity(postRuleDto);

    // Verify
    assertNotNull(output);
    assertEquals(EVENT_NAME, output.getEventName());
    assertEquals(RULE_DESCRIPTION, output.getDescription());
    assertEquals(1, output.getTemplateIds().size());
    assertEquals(TEMPLATE_ID_1, output.getTemplateIds().iterator().next());
  }

  private PostRuleDto createDummyPostTemplateRuleDto() {
    PostRuleDto postRuleDto = new PostRuleDto();

    postRuleDto.setEventName(EVENT_NAME);
    postRuleDto.setDescription(RULE_DESCRIPTION);
    postRuleDto.setTemplateIds(List.of(TEMPLATE_ID_1));

    return postRuleDto;
  }
}
