package org.aacounty.wastetrackingng.entity.serviceareas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ServiceAreasRepository
        extends JpaRepository<ServiceAreas, Integer>, JpaSpecificationExecutor<ServiceAreas> {
    List<ServiceAreas> findAll();
}
