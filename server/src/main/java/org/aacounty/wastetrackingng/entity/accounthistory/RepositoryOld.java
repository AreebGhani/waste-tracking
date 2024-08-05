// package org.aacounty.wastetrackingng.entity.accounthistory;

// // import java.sql.Timestamp;

// import org.aacounty.wastetrackingng.deprecating.CustomRepository;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
// // import org.springframework.data.jpa.repository.Modifying;
// // import org.springframework.data.jpa.repository.Query;
// // import org.springframework.data.repository.query.Param;

// // ACCOUNT_HISTORY querys, POST only, cannot interact with BATCH_TYPE and STATUS from VIEW
// public interface RepositoryOld extends JpaRepository<AccountHistory, Long>, 
//         JpaSpecificationExecutor<AccountHistory>, CustomRepository<AccountHistory, Integer>   {

//         // ADD NEW ACCOUNT HISTORY
//         @Modifying
//         @Query(nativeQuery = true, value = "INSERT INTO ACCOUNT_HISTORY (SEQ_NO, ACCOUNT_NO, BATCH_ID, WASTE_UNIT, EFFECTIVE_DATE, USER_ID, COMMENTS, DOCUMENT_NOTES, SERVICE_AREA) "
//                         +
//                         "VALUES (WASTE_UNIT.SEQ_NO_SEQ2.NEXTVAL, :accountNo, :batchId, :wasteUnit, :effectiveDate, :userId, :comments, :documentNotes, :serviceArea)")
//         void newAccountHistory(
//                         @Param("accountNo") String accountNo,
//                         @Param("batchId") int batchId,
//                         @Param("wasteUnit") int wasteUnit,
//                         @Param("effectiveDate") Timestamp effectiveDate,
//                         @Param("userId") String userId,
//                         @Param("comments") String comments,
//                         @Param("documentNotes") String documentNotes,
//                         @Param("serviceArea") String serviceArea
//                         );
        
//         // DELETE ACCOUNT HISTORY
//         @Modifying
//         @Query(nativeQuery = true, value = "DELETE FROM ACCOUNT_HISTORY where BATCH_ID = :batchId AND ACCOUNT_NO = :accountNo")
//         void deleteFromAccountHistory(@Param("batchId") Integer batchId, @Param("accountNo") String accountNo);

//         // UPDATE EFFECTIVE DATE
//         // Separated from rest of updates due to more delicate nature of handling Timestamps vs Ints and Strings, can be done
//         // With generic updater in future though
//         @Modifying
//         @Query(value = "UPDATE ACCOUNT_HISTORY a SET a.EFFECTIVE_DATE = COALESCE(:effectiveDate, a.EFFECTIVE_DATE) WHERE a.SEQ_NO = :seqNo", nativeQuery = true)
//         void updateAccountHistoryEffectiveDate(@Param("seqNo") int seqNo, @Param("effectiveDate") Timestamp effectiveDate);
        

        
// }
