package org.jcs.notification.service.database.repositories;

import org.jcs.notification.service.database.entities.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TemplateRepository extends CrudRepository<TemplateEntity, UUID>,
    PagingAndSortingRepository<TemplateEntity, UUID> {
}
