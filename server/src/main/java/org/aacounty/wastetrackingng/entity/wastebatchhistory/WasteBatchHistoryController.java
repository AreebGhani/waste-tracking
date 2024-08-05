package org.aacounty.wastetrackingng.entity.wastebatchhistory;

import org.aacounty.wastetrackingng.entity.wastebatchhistory.dto.HistoryCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batches")
public class WasteBatchHistoryController {

    private final WasteBatchHistoryService wasteBatchHistoryService;

    @Autowired
    public WasteBatchHistoryController(WasteBatchHistoryService wasteBatchHistoryService) {
        this.wasteBatchHistoryService = wasteBatchHistoryService;
    }

    // Payload format
    // {
    // "batchId": "1368",
    // "status": "CO",
    // "userId": "itng0023"
    // //"comments": "I see"
    // }
    @PostMapping("/updatestatus")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<WasteBatchHistory> addHistory(@RequestBody HistoryCreateDto dto) {
        WasteBatchHistory addedHistory = wasteBatchHistoryService.addHistory(dto);
        return ResponseEntity.ok(addedHistory);
    }
}
