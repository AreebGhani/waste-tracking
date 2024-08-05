package org.aacounty.wastetrackingng.entity.suspendedbatchesview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/batches")
public class SuspendedBatchesViewController {

    private final SuspendedBatchesViewService service;
    // private final boolean useNewSearchMethods = true; // Feature flag

    @Autowired
    public SuspendedBatchesViewController(SuspendedBatchesViewService service) {
        this.service = service;
    }
    // ==================================================================================================================
    // ================================================ SUSPENDED BATCHES
    // =================================================
    // ==================================================================================================================

    // http://localhost:8080/batches/suspendedbatches
    // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/suspendedbatches")
    public ResponseEntity<?> viewSuspendedBatches() {

        List<SuspendedBatchesView> list = service.viewSuspendedBatches();
        System.out.print("Data in List");
        System.out.print(list);
        return ResponseEntity.ok().body(list);
    }
}
