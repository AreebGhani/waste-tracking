package org.aacounty.wastetrackingng.entity.masterview.dto;

import java.sql.Timestamp;

public class WasteBatchSearchDto {
    private Integer batchId;
    private String status;
    private String userId;
    private String batchType;
    private Timestamp fromStatusDate;
    private Timestamp toStatusDate;
    private Integer serviceArea;
    public Boolean onlyLastStatus;

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

    public String getBatchType() {
        return batchType;
    }

    public Timestamp getFromStatusDate() {
        return fromStatusDate;
    }

    public Timestamp getToStatusDate() {
        return toStatusDate;
    }

    public Integer getServiceArea() {
        return serviceArea;
    }

    public Boolean getOnlyLastStatus() {
        return onlyLastStatus;
    };

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

    public void setBatchType(String batchType) {
        this.batchType = batchType;
    }

    public void setFromStatusDate(Timestamp fromStatusDate) {
        this.fromStatusDate = fromStatusDate;
    }

    public void setToStatusDate(Timestamp toStatusDate) {
        this.toStatusDate = toStatusDate;
    }

    public void setServiceArea(Integer serviceArea) {
        this.serviceArea = serviceArea;
    }

    public void setOnlyLastStatus(Boolean onlyLastStatus) {
        this.onlyLastStatus = onlyLastStatus;
    }
}
