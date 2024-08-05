package org.aacounty.wastetrackingng.entity.suspendedbatchesview;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuspendedBatchesViewService {

    // @Autowired
    // private SuspendedBatchesViewRepository repository;

    private final SuspendedBatchesViewRepository repository;

    @Autowired
    public SuspendedBatchesViewService(SuspendedBatchesViewRepository repository) {
        this.repository = repository;
    }

    public List<SuspendedBatchesView> viewSuspendedBatches() {
        List<SuspendedBatchesView> entityList = repository.findAll();
        return entityList;
    }
}
