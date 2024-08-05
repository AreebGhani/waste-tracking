package org.aacounty.wastetrackingng.entity.wastebatch.dto;

import java.time.LocalDate;
import java.util.Optional;

public class UpdateDto {
    private Long batchId;
    private Optional<String> batchType = Optional.empty();
    private Optional<LocalDate> suspensionEffectDt = Optional.empty();
    private Optional<LocalDate> suspensionFollowDt = Optional.empty();
    private Optional<String> suspensionReason = Optional.empty();
    private Optional<String> documentNotes = Optional.empty();

    // Constructor
    public UpdateDto() {
    }

    // Getters
    public Long getBatchId() {
        return batchId;
    }

    public Optional<String> getBatchType() {
        return batchType;
    }

    public Optional<LocalDate> getSuspensionEffectDt() {
        return suspensionEffectDt;
    }

    public Optional<LocalDate> getSuspensionFollowDt() {
        return suspensionFollowDt;
    }

    public Optional<String> getSuspensionReason() {
        return suspensionReason;
    }

    public Optional<String> getDocumentNotes() {
        return documentNotes;
    }

    // Setters
    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public void setBatchType(String batchType) {
        this.batchType = Optional.ofNullable(batchType);
    }

    public void setSuspensionEffectDt(LocalDate suspensionEffectDt) {
        this.suspensionEffectDt = Optional.ofNullable(suspensionEffectDt);
    }

    public void setSuspensionFollowDt(LocalDate suspensionFollowDt) {
        this.suspensionFollowDt = Optional.ofNullable(suspensionFollowDt);
    }

    public void setSuspensionReason(String suspensionReason) {
        this.suspensionReason = Optional.ofNullable(suspensionReason);
    }

    public void setDocumentNotes(String documentNotes) {
        this.documentNotes = Optional.ofNullable(documentNotes);
    }
}
