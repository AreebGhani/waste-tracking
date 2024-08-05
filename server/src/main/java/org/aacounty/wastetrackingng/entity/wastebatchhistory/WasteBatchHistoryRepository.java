package org.aacounty.wastetrackingng.entity.wastebatchhistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface WasteBatchHistoryRepository extends JpaRepository<WasteBatchHistory, Long> {

    List<WasteBatchHistory> findByBatchId(Long batchId);

    List<WasteBatchHistory> findByStatus(String status);

    @Query(value = "SELECT b.batchId, MAX(b.statusDt) " +
            "FROM WASTE_BATCH_HISTORY b " +
            "WHERE b.status = 'SU' " +
            "AND b.batchId NOT IN " +
            "(SELECT w.batchId " +
            " FROM WASTE_BATCH_HISTORY w " +
            " WHERE w.status IN ('CO', 'AP', 'CA')) " +
            "GROUP BY b.batchId " +
            "ORDER BY b.batchId", nativeQuery = true)
    List<Object[]> findMaxStatusDtForBatchIds();
}
