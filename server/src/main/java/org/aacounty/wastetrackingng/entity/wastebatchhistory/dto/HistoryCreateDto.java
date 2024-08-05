package org.aacounty.wastetrackingng.entity.wastebatchhistory.dto;

import java.util.Optional;

public class HistoryCreateDto {
    private Integer batchId;
    private String status;
    private String userId;
    private Optional<String> comments = Optional.empty();

    // Getters
    public Integer getBatchId() {
        return batchId;
    }

    public String getStatus() {
        return status;
    }

    public String getUserId() {
        return userId;
    }

    public Optional<String> getComments() {
        return comments;
    }

    // Setters
    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setComments(Optional<String> comments) {
        this.comments = comments;
    }
}
