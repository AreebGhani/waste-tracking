package org.aacounty.wastetrackingng.entity.wastebatch;

import org.aacounty.wastetrackingng.entity.wastebatchhistory.WasteBatchHistory;
import org.aacounty.wastetrackingng.entity.wastebatchhistory.WasteBatchHistoryRepository;
import org.aacounty.wastetrackingng.entity.wastebatch.dto.CreateDto;
import org.aacounty.wastetrackingng.entity.wastebatch.dto.UpdateDto;
import org.aacounty.wastetrackingng.entity.wastebatch.dto.WasteBatchForm;
import org.aacounty.wastetrackingng.entity.wastebatchhistory.dto.HistoryCreateDto;
import org.aacounty.wastetrackingng.entity.wastebatchhistory.WasteBatchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.persistence.EntityNotFoundException;

@Service
public class WasteBatchService {

    private final WasteBatchRepository wasteBatchRepository;
    @SuppressWarnings("unused")
    private final WasteBatchHistoryRepository wasteBatchHistoryRepository;
    private final WasteBatchHistoryService wasteBatchHistoryService;
    // private final AccountHistoryRepository accountHistoryRepository;
    // private final AccountHistoryService accountHistoryService;

    @Autowired
    public WasteBatchService(WasteBatchRepository wasteBatchRepository,
            WasteBatchHistoryRepository wasteBatchHistoryRepository,
            WasteBatchHistoryService wasteBatchHistoryService) {
        this.wasteBatchRepository = wasteBatchRepository;
        this.wasteBatchHistoryRepository = wasteBatchHistoryRepository;
        this.wasteBatchHistoryService = wasteBatchHistoryService;
    }

    @Transactional
    @CacheEvict(value = "latestItems", allEntries = true)
    public WasteBatch createBatch(CreateDto dto) {
        WasteBatch newBatch = new WasteBatch();
        newBatch.setBatchType(dto.getBatchType());
        dto.getSuspensionEffectDt().ifPresent(newBatch::setSuspensionEffectDt);
        dto.getSuspensionFollowDt().ifPresent(newBatch::setSuspensionFollowDt);
        dto.getSuspensionReason().ifPresent(newBatch::setSuspensionReason);
        dto.getDocumentNotes().ifPresent(newBatch::setDocumentNotes);

        WasteBatch savedBatch = wasteBatchRepository.save(newBatch);

        HistoryCreateDto historyDto = new HistoryCreateDto();
        historyDto.setBatchId(savedBatch.getBatchId());
        historyDto.setStatus("CR");
        historyDto.setUserId(dto.getUserId());
        historyDto.setComments(dto.getComments());

        wasteBatchHistoryService.addHistory(historyDto);
        return savedBatch;
    }

    @Transactional
    @CacheEvict(value = "latestItems", allEntries = true)
    public WasteBatch updateBatch(UpdateDto dto) {
        WasteBatch batch = wasteBatchRepository.findById(dto.getBatchId())
                .orElseThrow(() -> new EntityNotFoundException("Batch not found for ID: " + dto.getBatchId()));

        dto.getBatchType().ifPresent(batch::setBatchType);
        dto.getSuspensionEffectDt().ifPresent(batch::setSuspensionEffectDt);
        dto.getSuspensionFollowDt().ifPresent(batch::setSuspensionFollowDt);
        dto.getSuspensionReason().ifPresent(batch::setSuspensionReason);
        dto.getDocumentNotes().ifPresent(batch::setDocumentNotes);

        return wasteBatchRepository.save(batch);
    }

    // Delete a WasteBatch along with its history
    @Transactional
    @CacheEvict(value = "latestItems", allEntries = true)
    public void deleteBatch(Long batchId) {
        // Ensure batch exists
        wasteBatchRepository.findById(batchId)
                .orElseThrow(() -> new EntityNotFoundException("Batch not found for ID: " + batchId));

        // Delete all related history entries before deleting the batch
        wasteBatchHistoryService.deleteByBatchId(batchId);

        // Now delete the batch
        wasteBatchRepository.deleteById(batchId);
    }

    @Transactional
    @CacheEvict(value = "latestItems", allEntries = true)
    public void approve(WasteBatchForm form) {
        // Implementation for approving a batch
    }

    @Transactional
    @CacheEvict(value = "latestItems", allEntries = true)
    public void reject(WasteBatchForm form) {
        // Implementation for rejecting a batch
    }

    @Transactional
    @CacheEvict(value = "latestItems", allEntries = true)
    public void cancel(WasteBatchForm form) {
        // Implementation for canceling a batch
    }

    public List<WasteBatchHistory> viewApprovals() {
        return wasteBatchHistoryService.getAllApprovals();
    }
}
