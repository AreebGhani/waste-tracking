// package org.aacounty.wastetrackingng.entity.accounthistory;

// import java.lang.reflect.Executable;
// import java.sql.Timestamp;
// import java.text.ParseException;
// import java.text.SimpleDateFormat;
// import java.util.Arrays;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.List;
// import java.util.TimeZone;

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

// import org.aacounty.wastetrackingng.deprecating.AccountHistoryView;
// import org.aacounty.wastetrackingng.deprecating.AccountHistoryViewRepository;

// import java.util.Map;
// import java.util.Set;

// @RestController
// public class ControllerOld {
    
//     @Autowired
//     private RepositoryOld accountHistoryRepository;

//     @Autowired
//     private AccountHistoryViewRepository accountHistoryViewRepository;

//     //TODO: Add auth?
    

//     // ==================================================================================================================
//     // ============================================ GET REQUESTS (VIEW ONLY) ============================================
//     // ==================================================================================================================

//     @RequestMapping(value="/batches/accounthistory", method=RequestMethod.GET)
//     public ResponseEntity<AccountHistoryView> matchBatchId(@RequestParam(value = "id") int id) {
//         AccountHistoryView match = accountHistoryViewRepository.findByBatchId(id);
//         return new ResponseEntity<>(match, HttpStatus.OK);
//     }

//     @RequestMapping(value="/batches/accounthistories", method=RequestMethod.GET)
//     public ResponseEntity<List<AccountHistoryView>> matchAllBatchId(@RequestParam(value = "id") int id) {
//         List<AccountHistoryView> match = accountHistoryViewRepository.findAllByBatchId(id);
//         return new ResponseEntity<>(match, HttpStatus.OK);
//     }

//     @RequestMapping(value="/batches/accounthistory/servicearea", method=RequestMethod.GET)
//     public ResponseEntity<List<AccountHistoryView>> matchAllServiceArea(@RequestParam(value = "serviceArea") String serviceArea) {
//         Integer serviceNum = (("null".equals(serviceArea) || "NaN".equals(serviceArea)) ? null : Integer.parseInt(serviceArea));
//         List<AccountHistoryView> match = accountHistoryViewRepository.findAllByServiceArea(serviceNum);
//         return new ResponseEntity<>(match, HttpStatus.OK);
//     }

//     @RequestMapping(value="/batches/accounthistory/serviceareadate", method=RequestMethod.GET)
//     public ResponseEntity<List<AccountHistoryView>> matchAllServiceAreaBetweenDates(@RequestParam(value = "serviceArea") String serviceArea, 
//                                                                                 @RequestParam(value = "beginning") String beginning, 
//                                                                                 @RequestParam(value = "end") String end ) {
//             SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
//             dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
//             Date date1;
//             Date date2;
//             Timestamp timestamp1;
//             Timestamp timestamp2;
//             List<AccountHistoryView> filteredBatches;
//             try {
//             date1 = dateFormat.parse(beginning);
//             timestamp1 = new Timestamp(date1.getTime() - TimeZone.getDefault().getRawOffset());
            
//         } catch (ParseException e) {
//             //e.printStackTrace();
//             timestamp1 = null;
//         }
//         try {
//             date2 = dateFormat.parse(end);
//             timestamp2 = new Timestamp(date2.getTime() - TimeZone.getDefault().getRawOffset());
            
//         } catch (ParseException e) {
//             //e.printStackTrace();
//             timestamp2 = null;
//         } if ("All".equals(serviceArea)) {
//             filteredBatches = accountHistoryViewRepository.findAllByStatusBetween("CO", timestamp1, timestamp2);
//         } else {
//             Integer serviceNum = (("null".equals(serviceArea) || "NaN".equals(serviceArea)) ? null : Integer.parseInt(serviceArea));
//             filteredBatches = accountHistoryViewRepository.findAllByServiceAreaAndStatusBetween(serviceNum, "CO", timestamp1, timestamp2);
//         }
//         System.out.println(filteredBatches);
//         return new ResponseEntity<>(filteredBatches, HttpStatus.OK);
        
//     } 

//     // ==================================================================================================================
//     // =================================================== UTILITIES ====================================================
//     // ==================================================================================================================

//     // Checks if Column is considered a number in the database
//     private boolean isIntCol(String variableName) {
//         // Add all variable names that should be treated as integers
//         Set<String> integerVariables = new HashSet<>(Arrays.asList("batchId", "wasteUnit"));
//         return integerVariables.contains(variableName);
//     }

//     // Map that converts the variable name used in controller to databases column name
//     private String mapVariableToColumnName(String variableName) {
//         Map<String, String> colMap = new HashMap<>();
//         colMap.put("accountNo", "ACCOUNT_NO");
//         colMap.put("batchId", "BATCH_ID");
//         colMap.put("wasteUnit", "WASTE_UNIT");
//         colMap.put("effectiveDate", "EFFECTIVE_DATE");
//         colMap.put("userId", "USER_ID");
//         colMap.put("comments", "COMMENTS");
//         colMap.put("documentNotes", "DOCUMENT_NOTES");
//         colMap.put("serviceArea", "SERVICE_AREA");
//         colMap.put("batchType", "BATCH_TYPE");
//         colMap.put("status", "STATUS");

//         return colMap.getOrDefault(variableName, null);
//     }

//     // ==================================================================================================================
//     // ================================================= POST REQUESTS ==================================================
//     // ==================================================================================================================

//     // Create Account History, all parts of payload are required I believe*
//     // Payload format:
//     // {
//         // "accountNo": "111111111111",
//         // "batchId": 1111,
//         // "wasteUnit": 1,
//         // "effectiveDate": "2024-1-24 00:00:00.000",
//         // "userId": "itng0023",
//         // "comments": "Howdy!",
//         // "documentNotes": "yeehaw",
//         // "serviceArea": "1"
//     // }
//     @PostMapping(value = "/batches/accounthistory/create", consumes = "application/json", produces = "application/json")
//     @ResponseBody
//     @Transactional
//     public ResponseEntity<AccountHistory> createAccountHistory(@RequestBody ObjectNode bodyObject) throws Exception {
//         try {
//             // SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (z)");
//             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//             String accountNo = bodyObject.has("accountNo") ? bodyObject.get("accountNo").asText() : null;
//             Integer batchId = bodyObject.has("batchId") ? bodyObject.get("batchId").asInt() : null;
//             Integer wasteUnit = bodyObject.has("wasteUnit") ? bodyObject.get("wasteUnit").asInt() : null;
//             Date effectiveDateTemp;
//             Timestamp effectiveDate;
//             String userId = bodyObject.has("userId") ? bodyObject.get("userId").asText() : null;
//             String comments = bodyObject.has("comments") ? bodyObject.get("comments").asText() : null;
//             String documentNotes = bodyObject.has("documentNotes") ? bodyObject.get("documentNotes").asText() : null;
//             String serviceArea = bodyObject.has("serviceArea") ? bodyObject.get("serviceArea").asText() : null;
//             effectiveDateTemp = dateFormat.parse(bodyObject.get("effectiveDate").asText());
//             effectiveDate = new Timestamp(effectiveDateTemp.getTime());
//             accountHistoryRepository.newAccountHistory(accountNo, batchId, wasteUnit, effectiveDate, userId, comments, documentNotes, serviceArea);
//             return new ResponseEntity<>(HttpStatus.OK);

//         }  catch (ParseException e) {
//             e.printStackTrace();
//             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//         }        
//     }

//     // Delete Account History
//     @PostMapping(value = "/batches/accounthistory/delete", consumes = "application/json", produces = "application/json")
//     @ResponseBody
//     @Transactional
//     public ResponseEntity<AccountHistory> removeAccountHistory(@RequestBody ObjectNode bodyObject) {
//             Integer batchId = bodyObject.has("batchId") ? bodyObject.get("batchId").asInt() : null; 
//             String accountNo = bodyObject.has("accountNo") ? bodyObject.get("accountNo").asText() : null; 
//             if (batchId != null) {
//                 accountHistoryRepository.deleteFromAccountHistory(batchId, accountNo);
//                 return new ResponseEntity<>(HttpStatus.OK);
//             } else {
//                 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//             }

//     }

//     // Generic POST that changes a single column value, not intended to be used in the actual UI currently but it's repository 
//     // method is used for performing the main /update endpoint task
//     // Payload format:
//     // {
//     //     "seqNo": 9999,
//     //     "variableName": "comments",
//     //     "value": "Howdy!"
//     // }
//     @PostMapping(value = "/batches/accounthistory/update/single", consumes = "application/json", produces = "application/json")
//     @ResponseBody
//     @Transactional
//     public ResponseEntity<AccountHistory> updateSingleValue(@RequestBody ObjectNode bodyObject) {

//         // Check if it has seqno, variable name, and variable value
//         if (!bodyObject.has("seqNo") || !bodyObject.has("variableName") || !bodyObject.has("value")) {
//             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//         }

//         // Convert as appropriate
//         Integer seqNo = bodyObject.get("seqNo").asInt();
//         String variableName = bodyObject.get("variableName").asText();
//         String col = mapVariableToColumnName(variableName);
//         String idName = "seqNo";

//         // Secondary null check
//         if (col == null || bodyObject.get("value") == null) {
//             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//         }

//         try {
//             // For effectiveDate
//             if ("effectiveDate".equals(variableName)) {
//                 String dateString = bodyObject.get("value").asText();
//                 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//                 Date parsedDate = dateFormat.parse(dateString);
//                 Timestamp effectiveDate = new Timestamp(parsedDate.getTime());
//                 accountHistoryRepository.updateAccountHistoryEffectiveDate(seqNo, effectiveDate);
            
//             // For the integers batch ID and waste unit
//             } else if (isIntCol(variableName)) {
//                 Integer value = bodyObject.get("value").asInt();
//                 accountHistoryRepository.updateIntegerField(AccountHistory.class, variableName, value, idName, seqNo);
            
//             // For all other variables
//             } else {
//                 String value = bodyObject.get("value").asText();
//                 accountHistoryRepository.updateStringField(AccountHistory.class, variableName, value, idName, seqNo);
//             }
//         } catch (ParseException e) {
//             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//         }

//         return new ResponseEntity<>(HttpStatus.OK);
//     }

//     // Dynamic POST that updates whatever variables you give it, EVERY variable below "seqNo" is optional
//     // Payload format:
//     // {
//         // "seqNo": "9999",
//         // "accountNo": "111111111111",
//         // "batchId": 1111,
//         // "wasteUnit": 1,
//         // "effectiveDate": "2024-1-24 00:00:00.000",
//         // "userId": "itng0023",
//         // "comments": "Howdy!",
//         // "documentNotes": "yeehaw",
//         // "serviceArea": "1"
//     // }
//     @PostMapping(value = "/batches/accounthistory/update", consumes = "application/json", produces = "application/json")
//     @ResponseBody
//     @Transactional
//     public ResponseEntity<AccountHistory> updateAccountHistory(@RequestBody ObjectNode bodyObject) throws Exception {

//         if (!bodyObject.has("seqNo")) {
//             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//         }

//         Integer seqNo = bodyObject.has("seqNo") ? bodyObject.get("seqNo").asInt() : null;

//         List<String> varList =  List.of("accountNo", "batchId", "wasteUnit", "effectiveDate", "userId", "comments", "documentNotes", "serviceArea");

//         // Date format used by the Waste Database itself
//         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//         // Previous format used by controller
//         // SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z (z)");

//         for (String var : varList) {
//             if (bodyObject.has(var)) {
//                 String col = mapVariableToColumnName(var);
//                 if (col == null) {
//                     System.out.println("This shouldn't happen");
//                     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//                 }
//                 try {
//                     if ("effectiveDate".equals(var)) {
//                             Date parsedDate = dateFormat.parse(bodyObject.get(var).asText());
//                             Timestamp effectiveDate = new Timestamp(parsedDate.getTime());
//                             accountHistoryRepository.updateAccountHistoryEffectiveDate(seqNo, effectiveDate);
//                     }
//                     else if (isIntCol(var)){
//                         accountHistoryRepository.updateIntegerField(AccountHistory.class, var, bodyObject.get(var).asInt(), "seqNo", seqNo);
//                         // accountHistoryRepository.updateAccountHistorySingleInteger(mapVariableToColumnName(var), bodyObject.get(var).asInt(), "seqNo", seqNo);
//                     }
//                     else {
//                         accountHistoryRepository.updateStringField(AccountHistory.class, var, bodyObject.get(var).asText(), "seqNo", seqNo);
//                         // accountHistoryRepository.updateAccountHistorySingleString(mapVariableToColumnName(var), bodyObject.get(var).asText(), "seqNo", seqNo);
//                     }
//                 }
//                 catch (ParseException e) {
//                     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//                 }
//             }
//         }
//         return new ResponseEntity<>(HttpStatus.OK);
//     }
    
// }