package org.aacounty.wastetrackingng.entity.wastebatchhistory;

import org.aacounty.wastetrackingng.entity.wastebatch.WasteBatchRepository;
import org.aacounty.wastetrackingng.entity.wastebatchhistory.dto.HistoryCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
public class WasteBatchHistoryService {

    private final WasteBatchHistoryRepository repository;
    @SuppressWarnings("unused")
    private final WasteBatchRepository batchRepository;

    @Autowired
    public WasteBatchHistoryService(WasteBatchHistoryRepository repository,
            WasteBatchRepository wasteBatchRepository) { // Modify this constructor
        this.repository = repository;
        this.batchRepository = wasteBatchRepository;
    }

    @Transactional
    @CacheEvict(value = "latestItems", allEntries = true)
    public void deleteByBatchId(Long batchId) {
        List<WasteBatchHistory> histories = repository.findByBatchId(batchId);
        repository.deleteAll(histories);
    }

    @Transactional
    @CacheEvict(value = "latestItems", allEntries = true)
    public WasteBatchHistory addHistory(HistoryCreateDto dto) {
        // // If the dto lacks information, fetch the most recent history or the
        // associated WasteBatch
        // WasteBatch existingBatch = batchRepository.findById(dto.getBatchId())
        // .orElseThrow(() -> new EntityNotFoundException("WasteBatch not found for ID:
        // " + dto.getBatchId()));

        String userId = dto.getUserId();
        String status = dto.getStatus();
        String comments = dto.getComments().orElse(getDefaultCommentForStatus(status));

        WasteBatchHistory newHistory = new WasteBatchHistory();
        newHistory.setBatchId(dto.getBatchId());
        newHistory.setStatus(status);
        newHistory.setUserId(userId);
        newHistory.setComments(comments);
        newHistory.setStatusDt(new Timestamp(System.currentTimeMillis()));

        return repository.save(newHistory);
    }

    // Helper method to generate default comments based on status
    private String getDefaultCommentForStatus(String status) {
        switch (status) {
            case "SU":
                return "Submitted for approval";
            case "AP":
                return "Batch approved";
            case "CO":
                return "Batch completed and (allegedly) applied to CPF";
            case "RE":
                return "Batch rejected";
            case "RR":
                return "Batch ready for review";
            case "RS":
                return "Batch restored";
            case "CR":
                return "Batch created (this is an update, this shouldn't happen?)";
            default:
                return "Action taken on batch"; // Generic default or could tailor more specifically
        }
    }

    // New method to get all approvals
    @Transactional(readOnly = true)
    public List<WasteBatchHistory> getAllApprovals() {
        // Modify the criteria based on how you define an "approval"
        return repository.findByStatus("AP"); // Example: assuming "AP" represents approved
    }
}
