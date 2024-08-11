package org.jcs.notification.service;

import org.jcs.notification.service.database.repositories.TemplateRepository;
import org.jcs.notification.service.services.TemplateService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
class NotificationServiceApplicationTests {

	@MockBean
	private TemplateService templateService;

	@Test
	void contextLoads() {
	}

}
