package org.jcs.notification.service.mappers;

import org.jcs.notification.commons.constants.TemplateRenderType;
import org.jcs.notification.commons.constants.TemplateType;
import org.jcs.notification.commons.dtos.GetTemplateDto;
import org.jcs.notification.commons.dtos.PostTemplateDto;
import org.jcs.notification.commons.dtos.PostTemplateParameterDefinitionDto;
import org.jcs.notification.commons.dtos.datatypes.DataType;
import org.jcs.notification.commons.dtos.datatypes.validation.DataValidationRule;
import org.jcs.notification.commons.dtos.datatypes.validation.IntDataValidationRule;
import org.jcs.notification.commons.dtos.datatypes.validation.StringValidationRule;
import org.jcs.notification.service.database.entities.TemplateEntity;
import org.jcs.notification.service.database.entities.TemplateParameterEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

// @RunWith(SpringRunner.class)
@SpringBootTest(classes = {TemplateMapperImpl.class, TemplateParameterMapperImpl.class})
class TemplateMapperTest {

  private static final TemplateType TEMPLATE_TYPE = TemplateType.TEXT;
  private static final TemplateRenderType TEMPLATE_RENDER_TYPE = TemplateRenderType.TEXT;
  private static final String RATING_PARAMETER_NAME = "Rating";
  private static final String USER_NAME_PARAMETER_NAME = "UserName";
  private static final String TEMPLATE_NAME = "TestTemplate";
  private static final String TEMPLATE_CONTENT = "TestTemplateContent";
  @Autowired
  private TemplateMapper templateMapper;

  @Test
  void postDtoToEntity() {
    PostTemplateDto postTemplateDto = createDummyPostTemplateDto();

    TemplateEntity output = templateMapper.postDtoToEntity(postTemplateDto);

    // Assert template level fields
    assertEquals(TEMPLATE_NAME, output.getName());
    assertEquals(TEMPLATE_CONTENT, output.getContent());
    assertEquals(TEMPLATE_TYPE, output.getTemplateType());
    assertEquals(TEMPLATE_RENDER_TYPE, output.getTemplateRenderType());
    assertEquals(postTemplateDto.getParameters().size(), output.getParameters().size());

    // Validate first parameter
    PostTemplateParameterDefinitionDto firstInputParameterDto = postTemplateDto.getParameters().get(0);
    TemplateParameterEntity firstOutputParameterEntity = output.getParameters().get(0);
    assertEquals(firstInputParameterDto.getDataType(),  firstOutputParameterEntity.getDataType());
    assertEquals(DataType.INTEGER, firstOutputParameterEntity.getDataType());

    // Validate first data validation rule
    IntDataValidationRule inputDataValidationRule = (IntDataValidationRule)
        firstInputParameterDto.getDataValidationRule();
    IntDataValidationRule outputDataValidationRule = (IntDataValidationRule)
        firstOutputParameterEntity.getDataValidationRule();

    assertEquals(inputDataValidationRule.getMin(), outputDataValidationRule.getMin());
    assertEquals(inputDataValidationRule.getMax(), outputDataValidationRule.getMax());

    // Validate first parameter data validation rule
    IntDataValidationRule inputSecondDataValidationRule = (IntDataValidationRule)
        firstInputParameterDto.getDataValidationRule();
    IntDataValidationRule outputSecondDataValidationRule = (IntDataValidationRule)
        firstOutputParameterEntity.getDataValidationRule();

    assertEquals(inputSecondDataValidationRule.getMin(), outputSecondDataValidationRule.getMin());
    assertEquals(inputSecondDataValidationRule.getMax(), outputSecondDataValidationRule.getMax());

    // Validate second parameter
    PostTemplateParameterDefinitionDto secondInputParameter = postTemplateDto.getParameters().get(1);
    TemplateParameterEntity secondOutputParameter = output.getParameters().get(1);
    assertEquals(secondInputParameter.getDataType(), secondOutputParameter.getDataType());
    assertEquals(DataType.STRING, secondOutputParameter.getDataType());

    // Validate second parameter data validation rule
    StringValidationRule inputStringValidationRule =
        (StringValidationRule) secondInputParameter.getDataValidationRule();
    StringValidationRule outputStringValidationRule =
        (StringValidationRule) secondOutputParameter.getDataValidationRule();
    assertEquals(inputStringValidationRule.getMinLength(), outputStringValidationRule.getMinLength());
    assertEquals(inputStringValidationRule.getMaxLength(), outputStringValidationRule.getMaxLength());
  }

  @Test
  void entityToGetDto() {
    // Setup
    TemplateEntity templateEntity = createDummyTemplateEntity();

    // Execute
    GetTemplateDto output = templateMapper.entityToGetDto(templateEntity);

    // Verify
    assertEquals(templateEntity.getName(), output.getName());
    assertEquals(templateEntity.getContent(), output.getContent());
    assertEquals(templateEntity.getTemplateType(), output.getTemplateType());
    assertEquals(templateEntity.getTemplateRenderType(), output.getTemplateRenderType());
    assertEquals(templateEntity.getParameters().size(), output.getParameters().size());

    assertEquals(templateEntity.getParameters().get(0).getName(), output.getParameters().get(0).getName());
    assertEquals(templateEntity.getParameters().get(0).getDataType(), output.getParameters().get(0).getDataType());
    assertEquals(templateEntity.getParameters().get(1).getName(), output.getParameters().get(1).getName());
    assertEquals(templateEntity.getParameters().get(1).getDataType(), output.getParameters().get(1).getDataType());

    assertEquals(((IntDataValidationRule) templateEntity.getParameters().get(0).getDataValidationRule()).getMin(),
        ((IntDataValidationRule) output.getParameters().get(0).getDataValidationRule()).getMin());
    assertEquals(((IntDataValidationRule) templateEntity.getParameters().get(0).getDataValidationRule()).getMax(),
        ((IntDataValidationRule) output.getParameters().get(0).getDataValidationRule()).getMax());

    assertEquals(((StringValidationRule) templateEntity.getParameters().get(1).getDataValidationRule()).getMinLength(),
        ((StringValidationRule) output.getParameters().get(1).getDataValidationRule()).getMinLength());
    assertEquals(((StringValidationRule) templateEntity.getParameters().get(1).getDataValidationRule()).getMaxLength(),
        ((StringValidationRule) output.getParameters().get(1).getDataValidationRule()).getMaxLength());
  }

  private PostTemplateDto createDummyPostTemplateDto() {
    PostTemplateDto postTemplateDto = new PostTemplateDto();
    postTemplateDto.setName(TEMPLATE_NAME);
    postTemplateDto.setContent(TEMPLATE_CONTENT);
    postTemplateDto.setTemplateType(TEMPLATE_TYPE);
    postTemplateDto.setTemplateRenderType(TEMPLATE_RENDER_TYPE);

    PostTemplateParameterDefinitionDto ratingParameterDefinition = createRatingParameterDto();
    PostTemplateParameterDefinitionDto userNameParameterDefinition = createPostUserNameParameterDto();

    postTemplateDto.setParameters(Arrays.asList(ratingParameterDefinition, userNameParameterDefinition));

    return postTemplateDto;
  }

  private PostTemplateParameterDefinitionDto createRatingParameterDto() {
    PostTemplateParameterDefinitionDto postTemplateParameterDefinitionDto = new PostTemplateParameterDefinitionDto();

    IntDataValidationRule intDataValidationRule = new IntDataValidationRule();
    intDataValidationRule.setMax(5);
    intDataValidationRule.setMin(1);

    postTemplateParameterDefinitionDto.setName(RATING_PARAMETER_NAME);
    postTemplateParameterDefinitionDto.setDataType(DataType.INTEGER);

    postTemplateParameterDefinitionDto.setDataValidationRule(intDataValidationRule);

    return postTemplateParameterDefinitionDto;
  }

  private PostTemplateParameterDefinitionDto createPostUserNameParameterDto() {
    PostTemplateParameterDefinitionDto userPostParameterDefinition = new PostTemplateParameterDefinitionDto();

    StringValidationRule stringValidationRule = new StringValidationRule();
    stringValidationRule.setMinLength(1);
    stringValidationRule.setMaxLength(20);

    userPostParameterDefinition.setName(USER_NAME_PARAMETER_NAME);
    userPostParameterDefinition.setDataType(DataType.STRING);

    userPostParameterDefinition.setDataValidationRule(stringValidationRule);

    return userPostParameterDefinition;
  }

  private TemplateEntity createDummyTemplateEntity() {
    TemplateEntity templateEntity = new TemplateEntity();
    templateEntity.setName(TEMPLATE_NAME);
    templateEntity.setContent(TEMPLATE_CONTENT);
    templateEntity.setTemplateType(TEMPLATE_TYPE);
    templateEntity.setTemplateRenderType(TEMPLATE_RENDER_TYPE);
    templateEntity.setParameters(createDummyTemplateParameterEntities());

    return templateEntity;
  }

  private List<TemplateParameterEntity> createDummyTemplateParameterEntities() {
    return Arrays.asList(createRatingTemplateParameterEntity(), createUserNameTemplateParameterEntity());
  }

  private TemplateParameterEntity createRatingTemplateParameterEntity() {
    TemplateParameterEntity templateParameterEntity = new TemplateParameterEntity();
    templateParameterEntity.setName(RATING_PARAMETER_NAME);
    templateParameterEntity.setDataType(DataType.INTEGER);

    IntDataValidationRule dataValidationRule = new IntDataValidationRule();
    dataValidationRule.setMax(5);
    dataValidationRule.setMin(1);
    templateParameterEntity.setDataValidationRule(dataValidationRule);

    return templateParameterEntity;
  }

  private TemplateParameterEntity createUserNameTemplateParameterEntity() {
    TemplateParameterEntity templateParameterEntity = new TemplateParameterEntity();
    templateParameterEntity.setName(USER_NAME_PARAMETER_NAME);
    templateParameterEntity.setDataType(DataType.STRING);

    StringValidationRule stringValidationRule = new StringValidationRule();
    stringValidationRule.setMinLength(1);
    stringValidationRule.setMaxLength(20);
    templateParameterEntity.setDataValidationRule(stringValidationRule);

    return templateParameterEntity;
  }
}
