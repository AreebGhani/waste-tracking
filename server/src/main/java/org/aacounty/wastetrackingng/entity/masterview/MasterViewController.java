package org.aacounty.wastetrackingng.entity.masterview;

import org.aacounty.wastetrackingng.entity.masterview.dto.AccountHistoryDto;
import org.aacounty.wastetrackingng.entity.masterview.dto.WasteBatchDto;
import org.aacounty.wastetrackingng.entity.masterview.dto.AccountHistorySearchDto;
import org.aacounty.wastetrackingng.entity.masterview.dto.WasteBatchSearchDto;
import org.aacounty.wastetrackingng.entity.masterview.dto.MasterDto;
import org.aacounty.wastetrackingng.entity.masterview.dto.MasterSearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.List;

@RestController
// @RequestMapping("/batches")
public class MasterViewController {

    private final MasterViewService service;
    // private final boolean useNewSearchMethods = true; // Feature flag

    @Autowired
    public MasterViewController(MasterViewService service) {
        this.service = service;
    }
    // ==================================================================================================================
    // ================================================ ACCOUNT HISTORY
    // =================================================
    // ==================================================================================================================

    // http://localhost:8080/batches/accounthistory/1111
    @GetMapping("/batches/accounthistory/view/id")
    public ResponseEntity<AccountHistoryDto> findAhByBatchId(@RequestParam(value = "batchId") Integer id) {
        AccountHistoryDto dto = service.findAhByBatchId(id);
        return ResponseEntity.ok(dto);
    }

    //// New impl
    // @GetMapping("/accounthistory/view/id")
    // public ResponseEntity<List<AccountHistoryDto>>
    //// findAhByBatchId(@RequestParam(value = "batchId") Integer id) {
    // AccountHistorySearchDto searchDto = new AccountHistorySearchDto();
    // searchDto.setBatchId(id);
    // List<AccountHistoryDto> dtos = service.searchAccountHistory(searchDto);
    // return ResponseEntity.ok(dtos);
    // }

    // @GetMapping("/accounthistory/view/allbyid")
    // public ResponseEntity<List<AccountHistoryDto>>
    // findAllAhByBatchId(@RequestParam(value = "batchId") Integer id) {
    // List<AccountHistoryDto> dtos = service.findAllAhByBatchId(id);
    // return ResponseEntity.ok(dtos);
    // }

    @GetMapping("/batches/accounthistory/view/allbyid")
    public ResponseEntity<List<AccountHistoryDto>> findAllAhByBatchId(@RequestParam(value = "batchId") Integer id) {
        AccountHistorySearchDto searchDto = new AccountHistorySearchDto();
        searchDto.setBatchId(id);
        List<AccountHistoryDto> dtos = service.searchAccountHistory(searchDto);
        return ResponseEntity.ok(dtos);
    }

    // @GetMapping("/accounthistory/view/servicearea")
    // public ResponseEntity<List<AccountHistoryDto>>
    // findAllAhByServiceArea(@RequestParam(value = "serviceArea") Integer
    // serviceArea) {
    // List<AccountHistoryDto> dtos = service.findAllAhByServiceArea(serviceArea);
    // return ResponseEntity.ok(dtos);
    // }

    @GetMapping("/accounthistory/view/servicearea")
    public ResponseEntity<List<AccountHistoryDto>> findAllAhByServiceArea(
            @RequestParam(value = "serviceArea") String serviceArea) {
        AccountHistorySearchDto searchDto = new AccountHistorySearchDto();
        searchDto.setServiceArea(serviceArea);
        List<AccountHistoryDto> dtos = service.searchAccountHistory(searchDto);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/batches/accounthistory/view/serviceareadate")
    public ResponseEntity<List<AccountHistoryDto>> findAllAhByServiceAreaAndEffectiveDateBetween(
            @RequestParam Integer serviceArea,
            @RequestParam Timestamp start,
            @RequestParam Timestamp end) {
        List<AccountHistoryDto> dtos = service.findAllAhByServiceAreaAndEffectiveDateBetween(serviceArea, start, end);
        return ResponseEntity.ok(dtos);
    }

    // Formatting guide
    // {
    // "accountNo": "70000000223",
    // "batchId": 1374,
    // "wasteUnit": 1,
    // "userId": "itng0023",
    // "serviceArea": 2,
    // "status": "SU",
    // "fromStatusDate": "2024-01-01 00:00:00.000",
    // "toStatusDate": "2024-03-24 00:00:00.000",
    // "fromEffectiveDate": "2024-01-01 00:00:00.000",
    // "toEffectiveDate": "2024-03-20 00:00:00.000"
    // }
    @PostMapping("/batches/accounthistory/view/search")
    public ResponseEntity<List<AccountHistoryDto>> findAllAccountHistoryByFilter(
            @RequestBody AccountHistorySearchDto searchDto) {
        List<AccountHistoryDto> results = service.searchAccountHistory(searchDto);
        return ResponseEntity.ok(results);
    }

    // ==================================================================================================================
    // ================================================ WASTE BATCH
    // =================================================
    // ==================================================================================================================

    // @GetMapping("/view/pending")
    // public ResponseEntity<List<WasteBatchDto>> getPendingBatches() {
    // List<WasteBatchDto> pendingBatches = service.getBatchesByStatus("SU");
    // return ResponseEntity.ok(pendingBatches);
    // }

    @GetMapping("/batches/view/pending")
    public ResponseEntity<List<WasteBatchDto>> getPendingBatches() {
        WasteBatchSearchDto searchDto = new WasteBatchSearchDto();
        searchDto.setStatus("SU");
        List<WasteBatchDto> dtos = service.searchBatches(searchDto);
        return ResponseEntity.ok(dtos);
    }

    // Get a batch's type by its ID
    @GetMapping("/batches/view/batchtype")
    public ResponseEntity<String> getBatchTypeById(@RequestParam(value = "batchId") Integer id) {
        WasteBatchSearchDto searchDto = new WasteBatchSearchDto();
        searchDto.setBatchId(id);
        List<WasteBatchDto> batches = service.searchBatches(searchDto);
        String batchType = batches.isEmpty() ? null : batches.get(0).getBatchType();
        return ResponseEntity.ok(batchType);
    }

    // Get the most recent batch by its ID
    @GetMapping("/batches/view/mostrecentid")
    public ResponseEntity<WasteBatchDto> getMostRecentBatchById(@RequestParam(value = "batchId") Long id) {
        WasteBatchDto recentBatch = service.getMostRecentBatchById(id);
        return ResponseEntity.ok(recentBatch);
    }

    // Formatting guide
    // {
    // // "batchId": 2985,
    // // "status": "SU"
    // // "userId": "itng0023",
    // // "batchType": "A",
    // // "fromStatusDate": "2024-01-18 00:00:00.000"
    // // "toStatusDate": "2024-03-18 00:00:00.000",
    // // "serviceArea": "3"
    // }
    @PostMapping("/batches/view/search")
    public ResponseEntity<List<WasteBatchDto>> findAllBatchesByFilter(@RequestBody WasteBatchSearchDto searchDto) {
        List<WasteBatchDto> results = service.searchBatches(searchDto);
        return ResponseEntity.ok(results);
    }

    // ==================================================================================================================
    // ================================================ MASTER VIEW
    // =================================================
    // ==================================================================================================================

    // Formatting Guide
    // {
    // "seqNoPair": "1-1",
    // "ahSeqNo": 1,
    // "wbhSeqNo": 1,
    // "accountNo": "70000000223",
    // "batchId": 1374,
    // "wasteUnit": 1,
    // "userId": "itng0023",
    // "serviceArea": "2",
    // "batchType": "A",
    // "status": "SU",
    // "fromEffectiveDate": "2024-01-01 00:00:00.000",
    // "toEffectiveDate": "2024-03-20 00:00:00.000",
    // "fromStatusDt": "2024-01-01 00:00:00.000",
    // "toStatusDt": "2024-03-24 00:00:00.000"
    // }
    @PostMapping("/view/search")
    public ResponseEntity<List<MasterDto>> findAllDataByFilter(@RequestBody MasterSearchDto searchDto) {
        List<MasterDto> results = service.searchMasterView(searchDto);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/view/pending")
    public ResponseEntity<List<MasterDto>> findPendingEntries() {
        MasterSearchDto searchDto = new MasterSearchDto();
        searchDto.setStatus("SU");
        searchDto.setHideOldBatchHistory(true);

        List<MasterDto> results = service.searchMasterView(searchDto);
        return ResponseEntity.ok(results);
    }

    // call findpendingentries and count list length
    @GetMapping("/view/pendingcount")
    public ResponseEntity<Integer> countPendingEntries() {
        MasterSearchDto searchDto = new MasterSearchDto();
        searchDto.setStatus("SU");
        searchDto.setHideOldBatchHistory(true);

        List<MasterDto> results = service.searchMasterView(searchDto);
        return ResponseEntity.ok(results.size());
    }

}
