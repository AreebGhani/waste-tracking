package org.aacounty.wastetrackingng.entity.masterview.dto;

import java.sql.Timestamp;

public class AccountHistoryDto {
    private Integer ahSeqNo;
    private String accountNo;
    private Integer batchId;
    private Integer wasteUnit;
    private Timestamp effectiveDate;
    private String userId;
    private String ahComments;
    private String documentNotes;
    private String serviceArea;
    private String status;

    // Getters
    public Integer getAhSeqNo() {
        return ahSeqNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public Integer getWasteUnit() {
        return wasteUnit;
    }

    public Timestamp getEffectiveDate() {
        return effectiveDate;
    }

    public String getUserId() {
        return userId;
    }

    public String getAhComments() {
        return ahComments;
    }

    public String getDocumentNotes() {
        return documentNotes;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setAhSeqNo(Integer ahSeqNo) {
        this.ahSeqNo = ahSeqNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public void setWasteUnit(Integer wasteUnit) {
        this.wasteUnit = wasteUnit;
    }

    public void setEffectiveDate(Timestamp effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAhComments(String ahComments) {
        this.ahComments = ahComments;
    }

    public void setDocumentNotes(String documentNotes) {
        this.documentNotes = documentNotes;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Constructor
    public AccountHistoryDto() {
    }

    @Override
    public String toString() {
        return "AccountHistoryDto [ahSeqNo=" + ahSeqNo + ", accountNo=" + accountNo + ", batchId=" + batchId +
                ", wasteUnit=" + wasteUnit + ", effectiveDate=" + effectiveDate + ", userId=" + userId +
                ", ahComments=" + ahComments + ", documentNotes=" + documentNotes + ", serviceArea=" + serviceArea +
                ", status=" + status + "]";
    }
}
