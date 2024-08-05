package org.aacounty.wastetrackingng.entity.masterview.dto;

import java.sql.Timestamp;

public class WasteBatchDto {
    private Integer wbhSeqNo;
    private Integer batchId;
    private Timestamp statusDt;
    private String status;
    private String userId;
    private String wbhComments;
    private String batchType;

    // Constructor
    public WasteBatchDto() {
    }

    // Getters
    public Integer getWbhSeqNo() {
        return wbhSeqNo;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public Timestamp getStatusDt() {
        return statusDt;
    }

    public String getStatus() {
        return status;
    }

    public String getUserId() {
        return userId;
    }

    public String getWbhComments() {
        return wbhComments;
    }

    public String getBatchType() {
        return batchType;
    }

    // Setters
    public void setWbhSeqNo(Integer wbhSeqNo) {
        this.wbhSeqNo = wbhSeqNo;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public void setStatusDt(Timestamp statusDt) {
        this.statusDt = statusDt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setWbhComments(String wbhComments) {
        this.wbhComments = wbhComments;
    }

    public void setBatchType(String batchType) {
        this.batchType = batchType;
    }

    // toString method
    @Override
    public String toString() {
        return "WasteBatchDto [wbhSeqNo=" + wbhSeqNo + ", batchId=" + batchId + ", statusDt=" + statusDt + ", status=" +
                status + ", userId=" + userId + ", wbhComments=" + wbhComments + ", batchType=" + batchType + "]";
    }
}
