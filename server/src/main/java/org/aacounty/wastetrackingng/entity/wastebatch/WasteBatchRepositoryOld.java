// package org.aacounty.wastetrackingng.entity.wastebatch;

// import java.util.List;
// import java.util.Date;
// import java.lang.Integer;
// import java.sql.Timestamp;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

// public interface WasteBatchRepositoryOld extends JpaRepository<WasteBatch, Long>, JpaSpecificationExecutor<WasteBatch> {

// 	List<WasteBatch> findAllByStatus(String status);

// 	WasteBatch findFirstByBatchIdOrderByStatusDTDesc(int batchId);

// 	WasteBatch findBySeqNo(Integer seqNo);

// 	WasteBatch findTopByBatchId(int batchId);

// 	@Query(value = "SELECT BATCH_ID_SEQ.NEXTVAL FROM DUAL", nativeQuery = 
// 	true)
//   	int getNextBatchId();


// 	// @Query("SELECT r FROM Batch r WHERE" +
// 	// "(:fromDate IS NULL OR r.statusDT >= :fromDate) AND" +
// 	// "(:tDate IS NULL OR r.statusDT <= :toDate) AND" +
// 	// "(:batchId IS NULL OR r.batchId = :batchId) AND" +
// 	// "(:status IS NULL OR r.status = :status) AND" +
// 	// "(:type IS NULL OR r.type = :type) AND" +
// 	// "(:userId IS NULL OR r.userId = :userId)")
// 	@Query("SELECT r FROM Batch r WHERE r.status = :status")
// 	List<WasteBatch> findBatchesByCriteria(
// 			// @Param("fromDate") Date fromDate,
// 			// @Param("toDate") Date toDate,
// 			// @Param("batchId") Long batchId,
// 			@Param("status") String status
// 	// @Param("type") String type,
// 	// @Param("userId") String userId
// 	);

// 	// This will find unqiue batch ids by the most recent creation date, the filter
// 	// by status.
// 	@Query("SELECT b FROM Batch b " +
// 			"WHERE b.statusDT = (SELECT MAX(b2.statusDT) FROM Batch b2 WHERE b2.batchId = b.batchId) " +
// 			"AND b.status = :status")
// 	List<WasteBatch> findMostRecentByCriteria(@Param("status") String status);

// 	// Insertion query to insert to accomodate WASTE_BATCH_VIEW by inserting into WASTE_BATCH_HISTORY. 
// 	// Use this rather than .save. This will update the two joined tables in the view.
// 	@Modifying
// 	@Query(nativeQuery = true, value = "INSERT INTO WASTE_BATCH_HISTORY (SEQ_NO, BATCH_ID, STATUS_DT, STATUS, USER_ID, COMMENTS) "
// 			+
// 			"VALUES (WASTE_UNIT.SEQ_NO_SEQ.NEXTVAL, :batchId, :statusDT, :status, :userId, :comments)")
// 	void insertIntoWasteBatchHistory(
// 			@Param("batchId") int batchId,
// 			@Param("statusDT") Date statusDT,
// 			@Param("status") String status,
// 			@Param("userId") String userId,
// 			@Param("comments") String comments);

// 	// Modification of above function to insert a NEW batch into WASTE_BATCH_HISTORY. SeqNo generated here.
// 	@Modifying
// 	@Query(nativeQuery = true, value = "INSERT INTO WASTE_BATCH_HISTORY (SEQ_NO, BATCH_ID, STATUS_DT, STATUS, USER_ID, COMMENTS) "
// 			+
// 			"VALUES (WASTE_UNIT.SEQ_NO_SEQ.NEXTVAL, :batchId, :statusDT, :status, :userId, :comments)")
// 	void insertNewIntoWasteBatchHistory(
// 			@Param("batchId") int batchId,
// 			@Param("statusDT") Timestamp statusDT,
// 			@Param("status") String status,
// 			@Param("userId") String userId,
// 			@Param("comments") String comments);

// 	// Create new WASTE_BATCH entry based on params.
// 	@Modifying
// 	@Query(nativeQuery = true, value = "INSERT INTO WASTE_BATCH (BATCH_ID, BATCH_TYPE, SUSPENSION_EFFECT_DT, SUSPENSION_FOLLOW_DT, SUSPENSION_REASON, DOCUMENT_NOTES) "
// 			+
// 			"VALUES (:batchId, :batchType, :suspensionEffectDT, :suspensionFollowDT, :suspensionReason, :documentNotes)")
// 	void insertNewIntoWasteBatch(
// 			@Param("batchId") int batchId,
// 			@Param("batchType") String batchType,
// 			@Param("suspensionEffectDT") Timestamp suspensionEffectDT,
// 			@Param("suspensionFollowDT") Timestamp suspensionFollowDT,
// 			@Param("suspensionReason") String suspensionReason,
// 			@Param("documentNotes") String documentNotes);

// 	// Create new WASTE_BATCH with sequence number and batch type, setting the rest to NULL!
// 	@Modifying
// 	@Query(nativeQuery = true, value = "INSERT INTO WASTE_BATCH (BATCH_ID, BATCH_TYPE, SUSPENSION_EFFECT_DT, SUSPENSION_FOLLOW_DT, SUSPENSION_REASON, DOCUMENT_NOTES) "
// 			+
// 			"VALUES (:batchId, :batchType, NULL, NULL, NULL, NULL)")
// 	void initNewWasteBatch(
// 			@Param("batchId") int batchId,
// 			@Param("batchType") String batchType);



// 	@Query("SELECT b FROM Batch b " +
// 			"WHERE (:batchId is null or b.batchId = :batchId) " +
// 			"AND (:batchType is null or b.batchType = :batchType) " +
// 			"AND (:statusDTFrom is null or b.statusDT >= :statusDTFrom) " +
// 			"AND (:statusDTTo is null or b.statusDT <= :statusDTTo) " +
// 			"AND (:userID is null or b.userID = :userID)")
// 	 List<WasteBatch> findByFilters(@Param("batchId") Integer batchId,
// 							   @Param("batchType") String batchType,
// 							   @Param("statusDTFrom") Timestamp statusDTFrom,
// 							   @Param("statusDTTo") Timestamp statusDTTo,
// 							   @Param("userID") String userID);
// 	@Modifying
// 	@Query(value = "DELETE FROM WASTE_BATCH WHERE BATCH_ID = :batchId", nativeQuery = true)
// 	void deleteFromWasteBatch(@Param("batchId") Integer batchId);

// 	@Modifying
// 	@Query(value = "DELETE FROM WASTE_BATCH_HISTORY WHERE BATCH_ID = :batchId", nativeQuery = true)
// 	void deleteFromWasteBatchHistory(@Param("batchId") Integer batchId);

// 	@Modifying
// 	@Query(value = "DELETE FROM ACCOUNT_HISTORY WHERE BATCH_ID = :batchId", nativeQuery = true)
// 	void deleteFromAccountHistory(@Param("batchId") Integer batchId);
 
// 	// @Query("SELECT r FROM Batch r WHERE r.userId = :userId ORDER BY r.date DESC")
// 	// Batch findMostRecentBatchByUserId(String userId);

// }
