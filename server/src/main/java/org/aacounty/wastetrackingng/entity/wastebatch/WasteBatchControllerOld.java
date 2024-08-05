// package org.aacounty.wastetrackingng.entity.wastebatch;

// import java.sql.Timestamp;
// import java.text.ParseException;
// import java.text.SimpleDateFormat;
// import java.util.Date;
// import java.util.List;

// import javax.transaction.Transactional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.RestController;

// import com.fasterxml.jackson.databind.node.ObjectNode;

// @RestController
// public class WasteBatchControllerOld {

//     private final WasteBatchRepositoryOld batchRepository;

//     @Autowired
//     public WasteBatchControllerOld(WasteBatchRepositoryOld batchRepository) {
//         this.batchRepository = batchRepository;
//     }

//     // For brevity / sanity, wrapping this method under a simpler name.
//     WasteBatch findBatch(int batchId) {
// 		return batchRepository.findFirstByBatchIdOrderByStatusDTDesc(batchId);
// 	}

//     // ==================================================================================================================
//     // ================================================== GET REQUESTS ==================================================
//     // ==================================================================================================================

//     // Gets all pending batches (status is SU)
//     @RequestMapping(value = "/batches/pending", method = RequestMethod.GET)
//     public ResponseEntity<List<WasteBatch>> getPendingBatchs() {
//         List<WasteBatch> pendingBatches = batchRepository.findMostRecentByCriteria("SU");
//         return new ResponseEntity<>(pendingBatches, HttpStatus.OK);
//     }

//     // Gets batch type of a batch
//     @RequestMapping(value = "/batches/batchtype", method = RequestMethod.GET)
//     public ResponseEntity<WasteBatch> getBatchTypeById(@RequestParam(value = "batchId") int batchId) {
//         WasteBatch match = batchRepository.findTopByBatchId(batchId);
//         return new ResponseEntity<>(match, HttpStatus.OK);
//     }

//     // Gets most recent batch of an ID
//     @RequestMapping(value = "/batches/mostrecentid", method = RequestMethod.GET)
//     public ResponseEntity<WasteBatch> getMostRecentBatchById(@RequestParam(value = "batchId") int batchId) {
//         WasteBatch match = batchRepository.findFirstByBatchIdOrderByStatusDTDesc(batchId);
//         // Batch match = findBatch(batchId);
//         return new ResponseEntity<>(match, HttpStatus.OK);
//     }

//     // Searches by criteria
//     @RequestMapping(value = "/batches/search", method = RequestMethod.GET)
//     public ResponseEntity<List<WasteBatch>> searchBatches(@RequestParam(value = "batchNum") String batchNum,
//             @RequestParam(value = "createdBy") String createdBy, @RequestParam(value = "batchType") String batchType,
//             @RequestParam(value = "between") String between, @RequestParam(value = "and") String and) {
//         // These are all being passed through as strings. I don't really know why (either redwood's useParams or axios parameters? I assume the latter.)
//         // BIG SECTION OF TYPE CONVERSION FOR JPQL QUERY
//         SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (z)");
//         Integer num = (("null".equals(batchNum) || "NaN".equals(batchNum)) ? null : Integer.parseInt(batchNum));
//         String by = ("".equals(createdBy) ? null : createdBy);
//         Date date1;
//         Date date2;
//         Timestamp timestamp1;
//         Timestamp timestamp2;
//         try {
//             date1 = dateFormat.parse(between);
//             timestamp1 = new Timestamp(date1.getTime());
            
//         } catch (ParseException e) {
//             //e.printStackTrace();
//             timestamp1 = null;
//         }
//         try {
//             date2 = dateFormat.parse(and);
//             timestamp2 = new Timestamp(date2.getTime());
            
//         } catch (ParseException e) {
//             //e.printStackTrace();
//             timestamp2 = null;
//         }
//         List<WasteBatch> filteredBatches = batchRepository.findByFilters(num, batchType, timestamp1, timestamp2, createdBy);
//         //System.out.println(filteredBatches);
//         System.out.println(num + ", " + by + ", " + batchType + ", " + timestamp1 + ", " + timestamp2);
//         return new ResponseEntity<>(filteredBatches, HttpStatus.OK);
//     }

//     // ==================================================================================================================
//     // ================================================= POST REQUESTS ==================================================
//     // ==================================================================================================================

//     // Creation post request
//     /*
//      * @PostMapping("/batches/search")
//      * public ResponseEntity<List<Batch>> getBatchsByCriteria(@RequestBody
//      * BatchRequestPayload requestPayload) {
//      * List<Batch> batches = batchRepository.findBatchesByCriteria(
//      * //requestPayload.getFromDate(),
//      * //requestPayload.getToDate(),
//      * //requestPayload.getBatchId(),
//      * requestPayload.getStatus()//,
//      * //requestPayload.getType(),
//      * //requestPayload.getUserId()
//      * );
//      * return new ResponseEntity<>(batches, HttpStatus.OK);
//      * }
//      */
//     @PostMapping(value = "/batches/create", consumes = "application/json", produces = "application/json")
//     @ResponseBody
//     @Transactional
//     public ResponseEntity<WasteBatch> createBatch(@RequestBody ObjectNode bodyObject) {
//         String batchType = bodyObject.get("batchType").asText();
//         String userId = bodyObject.get("userId").asText();
//         int batchId = batchRepository.getNextBatchId();
//         Timestamp d = new Timestamp(System.currentTimeMillis());
//         //Batch newBatch = new Batch(batchNum, d, "CR", user, "Waste Batch ID " + batchNum + " Created.", batchType);
//         System.out.println(batchType);
//         System.out.println(userId);
//         batchRepository.initNewWasteBatch(batchId, batchType);
//         batchRepository.insertNewIntoWasteBatchHistory(batchId, d, "CR", userId, "Waste Batch ID " + batchId + " Created.");
//         // for return (temp?)
//         WasteBatch b = batchRepository.findTopByBatchId(batchId);
//         return new ResponseEntity<>(b, HttpStatus.OK);
//     }


//     @PostMapping(value = "/batches/updatestatus", consumes = "application/json", produces = "application/json")
//     @ResponseBody
//     @Transactional
//     public ResponseEntity<WasteBatch> updateBatchStatus(@RequestBody ObjectNode bodyObject) {

//         int batchId = bodyObject.get("batchId").asInt();
//         String userId = bodyObject.get("userId").asText();
//         String status = bodyObject.get("status").asText();
//         String comment = "";

//         if (!bodyObject.has(comment)) {
//             if (status == "SU") {
//                 comment = "Waste Batch ID " + batchId + " Submitted for approval.";
//             }
//             else if (status == "AP" || status == "CO") {
//                 comment = "Batch ID " + batchId + " has been completed and applied to CPF. (this is a lie)";
//             }
//             else if (status == "RE" || status == "CA" || status == "CR") {
//             }
//         }
//         else comment = bodyObject.get("comment").asText();


//         Timestamp date = new Timestamp(System.currentTimeMillis());
//         batchRepository.insertNewIntoWasteBatchHistory(batchId, date, status, userId, comment);
//         WasteBatch b = findBatch(batchId);
//         return new ResponseEntity<>(b, HttpStatus.OK);
//     }


//     // Updates a batch to be SU with new history entry
//     @PostMapping(value = "/batches/submit", consumes = "application/json", produces = "application/json")
//     @ResponseBody
//     @Transactional
//     public ResponseEntity<WasteBatch> submitBatch(@RequestBody ObjectNode bodyObject) {
//         int batchId = bodyObject.get("batchId").asInt();
//         String userId = bodyObject.get("userId").asText();
//         WasteBatch b = batchRepository.findFirstByBatchIdOrderByStatusDTDesc(batchId);
//         Timestamp d = new Timestamp(System.currentTimeMillis());
//         //Batch newBatch = new Batch(batchNum, d, "CR", user, "Waste Batch ID " + batchNum + " Created.", batchType);
//         //System.out.println(batchType);
//         //batchRepository.initNewWasteBatch(batchId, batchType);
//         batchRepository.insertNewIntoWasteBatchHistory(batchId, d, "SU", userId, "Waste Batch ID " + batchId + " Submitted for approval.");
//         // for return (temp?)
//         return new ResponseEntity<>(b, HttpStatus.OK);
//     }
    
//     // Updates batch status to CA
//     @PostMapping(value = "/batches/cancel", consumes = "application/json", produces = "application/json")
//     @ResponseBody
//     @Transactional
//     public ResponseEntity<WasteBatch> cancelBatchApproval(@RequestBody ObjectNode bodyObject) throws Exception {
//         try {
//             Integer id = bodyObject.has("id") ? bodyObject.get("id").asInt() : null;
//             String c = bodyObject.has("c") ? bodyObject.get("c").asText() : null;
//             if (id == null || c == null) {
//                 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//             }
//             WasteBatch b = batchRepository.findBySeqNo(id);
//             if (b == null) {
//                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//             }
//             Timestamp d = new Timestamp(System.currentTimeMillis());
//             WasteBatch newBatch = new WasteBatch(b.getBatchId(), d, "CA", b.getUserID(), c, b.getBatchType()); // for return
//             // System.out.println(newBatch.toString())
//             batchRepository.insertIntoWasteBatchHistory(b.getBatchId(), d, "CA", b.getUserID(), c);
//             return new ResponseEntity<>(newBatch, HttpStatus.OK);
//         } catch (Exception e) {
//             // Handle other exceptions if needed
//             e.printStackTrace();
//             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//         }
//     }

//     // Removes all entries with batch of this ID from databases
//     @PostMapping(value = "/batches/delete", consumes = "application/json", produces = "application/json")
//     @ResponseBody
//     @Transactional
//     public ResponseEntity<WasteBatch> deleteBatch(@RequestBody ObjectNode bodyObject) {
//         Integer batchId = bodyObject.has("batchId") ? bodyObject.get("batchId").asInt() : null;
//         if (batchId != null) {
//             batchRepository.deleteFromWasteBatchHistory(batchId);
//             batchRepository.deleteFromWasteBatch(batchId);
//             batchRepository.deleteFromAccountHistory(batchId);
//             return new ResponseEntity<>(HttpStatus.OK);
//         } else {
//             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//         }
        
//     }

//     // Sets batch status to approved (AP) and completed (CO)
//     @PostMapping(value = "/batches/accept", consumes = "application/json", produces = "application/json")
//     @ResponseBody
//     @Transactional
//     public ResponseEntity<WasteBatch> approveBatchApproval(@RequestBody ObjectNode bodyObject) throws Exception {
//         // MAYBE: Other approval stuff? (Ex. CPF related things, other DB things I'm not
//         // seeing, etc.)
//         // There's definetly some CPF stuff occuring that I'm not sure if the DB is
//         // handeling or not.
//         try {
//             Integer id = bodyObject.has("id") ? bodyObject.get("id").asInt() : null;
//             String c = bodyObject.has("c") ? bodyObject.get("c").asText() : null;
//             if (id == null || c == null) {
//                 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//             }
//             WasteBatch b = batchRepository.findBySeqNo(id);
//             if (b == null) {
//                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//             }
//             Timestamp d = new Timestamp(System.currentTimeMillis());
//             WasteBatch newBatch = new WasteBatch(b.getBatchId(), d, "AP", b.getUserID(), c, b.getBatchType()); // for return
//             // System.out.println(newBatch.toString())
//             batchRepository.insertIntoWasteBatchHistory(b.getBatchId(), d, "AP", b.getUserID(), c);
//             batchRepository.insertIntoWasteBatchHistory(b.getBatchId(), d, "CO", b.getUserID(),
//                     "Batch ID " + b.getBatchId() + " has been completed and applied to CPF."); // lie
//             return new ResponseEntity<>(newBatch, HttpStatus.OK);
//         } catch (Exception e) {
//             // Handle other exceptions if needed
//             e.printStackTrace();
//             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//         }
//     }

//     // Sets batch status to REjected and Ready for Review
//     @PostMapping(value = "/batches/reject", consumes = "application/json", produces = "application/json")
//     @ResponseBody
//     @Transactional
//     public ResponseEntity<WasteBatch> rejectBatchApproval(@RequestBody ObjectNode bodyObject) throws Exception {
//         // MAYBE: Other rejection stuff? (Ex. CPF related things, other DB things I'm
//         // not seeing, etc.)
//         try {
//             Integer id = bodyObject.has("id") ? bodyObject.get("id").asInt() : null;
//             String c = bodyObject.has("c") ? bodyObject.get("c").asText() : null;
//             if (id == null || c == null) {
//                 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//             }
//             WasteBatch b = batchRepository.findBySeqNo(id);
//             if (b == null) {
//                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//             }
//             Timestamp d = new Timestamp(System.currentTimeMillis());
//             WasteBatch newBatch = new WasteBatch(b.getBatchId(), d, "RE", b.getUserID(), c, b.getBatchType()); // for return
//             // System.out.println(newBatch.toString())
//             batchRepository.insertIntoWasteBatchHistory(b.getBatchId(), d, "RE", b.getUserID(), c);
//             batchRepository.insertIntoWasteBatchHistory(b.getBatchId(), d, "RR", b.getUserID(), c);
//             return new ResponseEntity<>(newBatch, HttpStatus.OK);
//         } catch (Exception e) {
//             // Handle other exceptions if needed
//             e.printStackTrace();
//             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//         }
//     }


//     /*
//      * Another helper function to return a list of batches with the most recent
//      * creation date.
//      * This is not in use as the batch data that goes in here only includes
//      * submissions, so the logic
//      * is all wrong.
//      * public static List<Batch>
//      * getUniqueBatchesWithMostRecentCreationDate(List<Batch> batches) {
//      * // Group batches by batchId and find the one with the most recent statusDT
//      * Map<Integer, Batch> latestBatches = batches.stream()
//      * .collect(Collectors.toMap(Batch::getBatchId, b -> b,
//      * BinaryOperator.maxBy(Comparator.comparing(Batch::getStatusDT))));
//      * 
//      * return new ArrayList<>(latestBatches.values());
//      * }
//      */

//     /*
//      * Helper function to get the latest date from a list of the batches.
//      * public static Batch latestDate(List<Batch> batchList) {
//      * if (batchList == null || batchList.isEmpty()) {
//      * return null;
//      * }
//      * Batch mostRecentBatch = batchList.get(0);
//      * for (Batch batch : batchList) {
//      * if (batch.getStatusDT().after(mostRecentBatch.getStatusDT())) {
//      * mostRecentBatch = batch;
//      * }
//      * }
//      * return mostRecentBatch;
//      * }
//      */
// }
