package org.aacounty.wastetrackingng.entity.masterview;

import org.aacounty.wastetrackingng.entity.masterview.dto.AccountHistoryDto;
import org.aacounty.wastetrackingng.entity.masterview.dto.WasteBatchDto;
import org.aacounty.wastetrackingng.entity.masterview.dto.AccountHistorySearchDto;
import org.aacounty.wastetrackingng.entity.masterview.dto.WasteBatchSearchDto;
import org.aacounty.wastetrackingng.entity.masterview.dto.MasterDto;
import org.aacounty.wastetrackingng.entity.masterview.dto.MasterSearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Comparator;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.cache.annotation.Cacheable;

@Service
public class MasterViewService {

    private final MasterViewRepository repository;

    @Autowired
    public MasterViewService(MasterViewRepository repository) {
        this.repository = repository;
    }

    // ==================================================================================================================
    // ================================================ ACCOUNT HISTORY
    // =================================================
    // ==================================================================================================================

    public AccountHistoryDto findAhByBatchId(Integer batchId) {
        List<MasterView> views = repository.findAllByBatchId(batchId);

        // Optional: Decide on a rule to pick one, e.g., the most recent based on a date
        MasterView selectedView = views.stream()
                .sorted(Comparator.comparing(MasterView::getEffectiveDate).reversed()) // Sort by date descending
                .findFirst() // Take the first one (the most recent)
                .orElseThrow(() -> new EntityNotFoundException("No entries found for batchId: " + batchId));

        return convertToAccountHistoryDto(selectedView);
    }

    public List<AccountHistoryDto> findAllAhByBatchId(Integer batchId) {
        List<MasterView> views = repository.findAllByBatchId(batchId);
        return views.stream().map(this::convertToAccountHistoryDto).collect(Collectors.toList());
    }

    public List<AccountHistoryDto> findAllAhByServiceArea(Integer serviceArea) {
        List<MasterView> views = repository.findAllByServiceArea(serviceArea);
        return views.stream().map(this::convertToAccountHistoryDto).collect(Collectors.toList());
    }

    public List<AccountHistoryDto> findAllAhByServiceAreaAndEffectiveDateBetween(Integer serviceArea, Timestamp start,
            Timestamp end) {

        List<MasterView> views = repository.findAllByServiceAreaAndEffectiveDateBetween(serviceArea, start, end);
        return views.stream().map(this::convertToAccountHistoryDto).collect(Collectors.toList());
    }

    public List<AccountHistoryDto> searchAccountHistory(AccountHistorySearchDto searchDto) {
        Specification<MasterView> specification = MasterViewSpecifications.findByAccHistCriteria(searchDto);
        return repository.findAll(specification).stream()
                .map(this::convertToAccountHistoryDto)
                .collect(Collectors.toList());
    }

    private AccountHistoryDto convertToAccountHistoryDto(MasterView masterView) {
        AccountHistoryDto dto = new AccountHistoryDto();
        // Map fields from MasterView to WasteBatchDto
        dto.setAhSeqNo(masterView.getAhSeqNo());
        dto.setAccountNo(masterView.getAccountNo());
        dto.setBatchId(masterView.getBatchId());
        dto.setWasteUnit(masterView.getWasteUnit());
        dto.setEffectiveDate(masterView.getEffectiveDate());
        dto.setUserId(masterView.getUserId());
        dto.setAhComments(masterView.getAhComments());
        dto.setDocumentNotes(masterView.getDocumentNotes());
        dto.setServiceArea(masterView.getServiceArea());
        // dto.setBatchType(masterView.getBatchType());
        dto.setStatus(masterView.getStatus());

        return dto;
    }

    // public List<MasterView> findAllByBatchId(Integer batchId) {
    // return repository.findAllByBatchId(batchId);
    // }

    // public List<MasterView> findAllByServiceArea(Integer serviceArea) {
    // return repository.findAllByServiceArea(serviceArea);
    // }

    // public List<MasterView> findAllByServiceAreaAndEffectiveDateBetween(Integer
    // serviceArea, Timestamp start, Timestamp end) {
    // return repository.findAllByServiceAreaAndEffectiveDateBetween(serviceArea,
    // start, end);
    // }

    // ==================================================================================================================
    // ================================================ WASTE BATCH
    // =================================================
    // ==================================================================================================================

    public List<WasteBatchDto> getBatchesByStatus(String status) {
        return repository.findByStatus(status).stream()
                .map(this::convertToWasteBatchDto)
                .collect(Collectors.toList());
    }

    public String getBatchTypeById(Integer id) {
        WasteBatchSearchDto searchDto = new WasteBatchSearchDto();
        searchDto.setBatchId(id);
        List<WasteBatchDto> batches = searchBatches(searchDto);
        return batches.isEmpty() ? null : batches.get(0).getBatchType();
    }

    public WasteBatchDto getMostRecentBatchById(Long batchId) {
        List<MasterView> views = repository.findAllByBatchId(batchId.intValue());
        if (views.isEmpty()) {
            throw new EntityNotFoundException("No prior account history found for Batch ID: " + batchId
                    + ". Please create an account history entry.");
        }

        // Assuming that the MasterView entities are already sorted by statusDt in
        // descending order
        MasterView mostRecent = views.get(0);
        return convertToWasteBatchDto(mostRecent);
    }

    // public List<WasteBatchDto> searchBatches(Integer batchNum, String createdBy,
    // String batchType, String from, String to) {
    // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    // Date fromDate = null, toDate = null;
    // try {
    // if (from != null && !from.isEmpty()) fromDate = dateFormat.parse(from);
    // if (to != null && !to.isEmpty()) toDate = dateFormat.parse(to);
    // } catch (ParseException e) {
    // e.printStackTrace();
    // throw new RuntimeException("Error parsing date");
    // }

    // // Implement a method in the repository to filter by these criteria
    // // This is a placeholder implementation assuming such a method exists
    // List<MasterView> views =
    // repository.findAll(MasterViewSpecifications.findByCriteria(batchNum,
    // createdBy, batchType, fromDate, toDate));
    // return
    // views.stream().map(this::convertToWasteBatchDto).collect(Collectors.toList());
    // }

    public List<WasteBatchDto> searchBatches(WasteBatchSearchDto searchDto) {
        List<MasterView> results;
        if (Boolean.TRUE.equals(searchDto.getOnlyLastStatus())) {
            results = repository.findLatestByBatchCriteria(searchDto);
        } else {
            results = repository.findAll(MasterViewSpecifications.findByBatchCriteria(searchDto));
        }

        return results.stream().map(this::convertToWasteBatchDto).collect(Collectors.toList());
    }

    private WasteBatchDto convertToWasteBatchDto(MasterView masterView) {
        WasteBatchDto dto = new WasteBatchDto();
        // Map fields from MasterView to WasteBatchDto
        dto.setWbhSeqNo(masterView.getWbhSeqNo());
        dto.setBatchId(masterView.getBatchId());
        dto.setStatusDt(masterView.getStatusDt());
        dto.setStatus(masterView.getStatus());
        dto.setUserId(masterView.getUserId());
        dto.setWbhComments(masterView.getWbhComments()); // Assuming this maps to COMMENTS
        dto.setBatchType(masterView.getBatchType());
        return dto;
    }

    @Cacheable(value = "latestItems")
    public List<MasterView> findLatestItems() {
        WasteBatchSearchDto emptyDto = new WasteBatchSearchDto(); // Assuming this fetches the latest batches
        return repository.findLatestByBatchCriteria(emptyDto);
    }

    // ==================================================================================================================
    // ================================================ MASTER
    // =================================================
    // ==================================================================================================================

    // public List<MasterDto> searchMasterView(MasterSearchDto searchDto) {
    // Specification<MasterView> specification =
    // MasterViewSpecifications.findByMasterCriteria(searchDto);
    // return repository.findAll(specification).stream()
    // .map(this::convertToMasterDto)
    // .collect(Collectors.toList());
    // }

    public List<MasterDto> searchMasterView(MasterSearchDto searchDto) {
        Specification<MasterView> specification = MasterViewSpecifications.findByMasterCriteria(searchDto);

        List<MasterView> queryResults = repository.findAll(specification);

        if (Boolean.TRUE.equals(searchDto.getHideOldBatchHistory())) {
            Map<Integer, MasterView> latestEntries = new HashMap<>();
            for (MasterView entry : queryResults) {
                MasterView currentLatest = latestEntries.get(entry.getBatchId());
                if (currentLatest == null || entry.getStatusDt().after(currentLatest.getStatusDt())) {
                    latestEntries.put(entry.getBatchId(), entry);
                }
            }
            queryResults = new ArrayList<>(latestEntries.values());
        }

        return queryResults.stream()
                .map(this::convertToMasterDto)
                .collect(Collectors.toList());
    }

    private MasterDto convertToMasterDto(MasterView masterView) {
        MasterDto dto = new MasterDto();
        // Map fields from MasterView to MasterDto
        dto.setSeqNoPair(masterView.getSeqNoPair());
        dto.setAhSeqNo(masterView.getAhSeqNo());
        dto.setWbhSeqNo(masterView.getWbhSeqNo());
        dto.setAccountNo(masterView.getAccountNo());
        dto.setBatchId(masterView.getBatchId());
        dto.setWasteUnit(masterView.getWasteUnit());
        dto.setEffectiveDate(masterView.getEffectiveDate());
        dto.setUserId(masterView.getUserId());
        dto.setAhComments(masterView.getAhComments());
        dto.setDocumentNotes(masterView.getDocumentNotes());
        dto.setServiceArea(masterView.getServiceArea());
        dto.setBatchType(masterView.getBatchType());
        dto.setStatusDt(masterView.getStatusDt());
        dto.setStatus(masterView.getStatus());
        dto.setWbhComments(masterView.getWbhComments());
        return dto;
    }

}
