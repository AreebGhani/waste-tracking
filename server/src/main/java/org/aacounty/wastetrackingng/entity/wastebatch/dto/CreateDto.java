package org.aacounty.wastetrackingng.entity.wastebatch.dto;

import java.time.LocalDate;
import java.util.Optional;

public class CreateDto {
    private String batchType;
    private Optional<LocalDate> suspensionEffectDt = Optional.empty();
    private Optional<LocalDate> suspensionFollowDt = Optional.empty();
    private Optional<String> suspensionReason = Optional.empty();
    private Optional<String> documentNotes = Optional.empty();
    private String userId;
    private Optional<String> comments = Optional.empty();

    // Getters
    public String getBatchType() {
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

    public String getUserId() {
        return userId;
    }

    public Optional<String> getComments() {
        return comments;
    }

    // Setters
    public void setBatchType(String batchType) {
        this.batchType = batchType;
    }

    public void setSuspensionEffectDt(Optional<LocalDate> suspensionEffectDt) {
        this.suspensionEffectDt = suspensionEffectDt;
    }

    public void setSuspensionFollowDt(Optional<LocalDate> suspensionFollowDt) {
        this.suspensionFollowDt = suspensionFollowDt;
    }

    public void setSuspensionReason(Optional<String> suspensionReason) {
        this.suspensionReason = suspensionReason;
    }

    public void setDocumentNotes(Optional<String> documentNotes) {
        this.documentNotes = documentNotes;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setComments(Optional<String> comments) {
        this.comments = comments;
    }
}
