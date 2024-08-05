package org.aacounty.wastetrackingng.deprecating;

// Delete this class when confirm is not needed
/**
import java.util.List;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountHistoryViewRepository extends JpaRepository<AccountHistoryView, Long>, 
        JpaSpecificationExecutor<AccountHistoryView>, CustomRepository<AccountHistoryView, Integer>   {
                
        AccountHistoryView findByBatchId(int batchId);
        List<AccountHistoryView> findAllByBatchId(int batchId);
        AccountHistoryView findTopByBatchId(int batchId);
        List<AccountHistoryView> findAllByServiceArea(Integer serviceArea);
        List<AccountHistoryView> findAllByStatus(String status);
        
        @Query("SELECT a FROM AccountHistoryView a " +
                "WHERE a.serviceArea = :serviceArea " +
                "AND a.status = :status " +
                "AND a.effectiveDate >= :start " +
                "AND a.effectiveDate <= :end "+
                "ORDER BY CAST(a.serviceArea AS integer) ASC") // neater return for service area table, must case since its a string (for some reason?)
        List<AccountHistoryView> findAllByServiceAreaAndStatusBetween(@Param("serviceArea") Integer serviceArea, 
                                                        @Param("status") String status,
                                                        @Param("start") Timestamp start, 
                                                        @Param("end") Timestamp end);
        @Query("SELECT a FROM AccountHistoryView a " +
                "WHERE a.status = :status " +
                "AND a.serviceArea IS NOT NULL " +
                "AND a.effectiveDate >= :start " +
                "AND a.effectiveDate <= :end " +
                "ORDER BY CAST(a.serviceArea AS integer) ASC") // neater return for service area table, must case since its a string (for some reason?)
        List<AccountHistoryView> findAllByStatusBetween(@Param("status") String status, 
                                                        @Param("start") Timestamp start, 
                                                        @Param("end") Timestamp end);
        @Query("SELECT a FROM AccountHistoryView a " +
                "WHERE a.serviceArea IS NOT NULL " +
                "AND a.effectiveDate >= :start " +
                "AND a.effectiveDate <= :end")                                                
        List<AccountHistoryView> findAllByServiceAreaNotNullBetweenDates(@Param("start") Timestamp start, 
                                                                        @Param("end") Timestamp end);

}

**/
