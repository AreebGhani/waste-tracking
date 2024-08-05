package org.aacounty.wastetrackingng.entity.masterview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;
import org.aacounty.wastetrackingng.entity.masterview.dto.WasteBatchSearchDto;
import java.util.Optional;

public interface MasterViewRepository extends JpaRepository<MasterView, String>, JpaSpecificationExecutor<MasterView> {

        // ==================================================================================================================
        // ================================================ ACCOUNT HISTORY
        // =================================================
        // ==================================================================================================================

        MasterView findByBatchId(Integer batchId);

        List<MasterView> findAllByBatchId(Integer batchId);

        List<MasterView> findAllByServiceArea(Integer serviceArea);

        // TODO: change to not rely on query
        @Query("SELECT mv FROM MasterView mv WHERE mv.serviceArea = :serviceArea AND mv.effectiveDate BETWEEN :start AND :end")
        List<MasterView> findAllByServiceAreaAndEffectiveDateBetween(Integer serviceArea, Timestamp start,
                        Timestamp end);

        // ==================================================================================================================
        // ================================================ WASTE BATCH
        // =================================================
        // ==================================================================================================================

        // Find all records by batchId, assuming batchId is unique for MasterView
        List<MasterView> findByBatchId(Long batchId);

        // Find all records with a specific status
        List<MasterView> findByStatus(String status);

        default List<MasterView> findLatestByBatchCriteria(WasteBatchSearchDto searchDto) {
                List<MasterView> initialResults = findAll(MasterViewSpecifications.findByBatchCriteria(searchDto));

                return initialResults.stream()
                                .collect(Collectors.groupingBy(MasterView::getBatchId,
                                                Collectors.maxBy(Comparator.comparing(MasterView::getStatusDt))))
                                .values().stream()
                                .map(Optional::get)
                                .collect(Collectors.toList());
        }

}
