package org.jcs.notification.service.database.repositories;


import org.jcs.notification.service.database.entities.TemplateRuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TemplateRuleRepository extends JpaRepository<TemplateRuleEntity, UUID> {
}
