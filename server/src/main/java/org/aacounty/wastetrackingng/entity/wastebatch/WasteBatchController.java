package org.aacounty.wastetrackingng.entity.wastebatch;

import org.aacounty.wastetrackingng.entity.wastebatch.dto.CreateDto;
import org.aacounty.wastetrackingng.entity.wastebatch.dto.UpdateDto;
import org.aacounty.wastetrackingng.entity.wastebatch.dto.WasteBatchForm;
import org.aacounty.wastetrackingng.entity.wastebatchhistory.WasteBatchHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/batches")
public class WasteBatchController {

    private final WasteBatchService wasteBatchService;

    @Autowired
    public WasteBatchController(WasteBatchService wasteBatchService) {
        this.wasteBatchService = wasteBatchService;
    }

    // Formatting guide
    // {
    // "batchType": "A",
    // //"suspensionEffectDt": "2024-03-13 18:00:09.884",
    // //"suspensionFollowDt": "2024-03-13 18:00:09.884",
    // //"suspensionReason"": "reason",
    // //"documentNotes": "aaa",
    // "userId": "itng0023" // For WBHistory
    // //"comments": "ok" // For WBHistory
    // }
    // Variables with an extra // are optional
    @PostMapping("/create")
    public ResponseEntity<WasteBatch> createBatch(@RequestBody CreateDto createBatchDto) {
        WasteBatch newBatch = wasteBatchService.createBatch(createBatchDto);
        return ResponseEntity.ok(newBatch);
    }

    // IMPORTANT: THIS IS DIFFERENT FROM /updatestatus, DO NOT EXPECT TO USE THIS
    // ONE NEARLY AS FREQUENTLY, IT DOES NOT CHANGE STATUS
    // Formatting guide
    // {
    // "batchId": "1137",
    // //"batchType": "C",
    // //"suspensionEffectDt": "2024-03-13 18:00:09.884",
    // //"suspensionFollowDt": "2024-03-13 18:00:09.884",
    // //"suspensionReason": "Aaa",
    // //"documentNotes": "Bbb"
    // }
    // Variables with an extra // are optional
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<WasteBatch> updateBatch(@RequestBody UpdateDto updateDto) {
        WasteBatch updatedBatch = wasteBatchService.updateBatch(updateDto);
        return ResponseEntity.ok(updatedBatch);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteBatch(@RequestParam Long batchId) {
        System.out.println("ID: " + batchId);
        wasteBatchService.deleteBatch(batchId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/approve")
    public ResponseEntity<Void> approveBatch(@RequestBody WasteBatchForm form) {
        wasteBatchService.approve(form);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reject")
    public ResponseEntity<Void> rejectBatch(@RequestBody WasteBatchForm form) {
        wasteBatchService.reject(form);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cancel")
    public ResponseEntity<Void> cancelBatch(@RequestBody WasteBatchForm form) {
        wasteBatchService.cancel(form);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/viewBatchApproval")
    public ResponseEntity<List<WasteBatchHistory>> cancelBatch() {
        return ResponseEntity.ok(wasteBatchService.viewApprovals());
    }
}
