package org.aacounty.wastetrackingng.entity.masterview.dto;

import java.sql.Timestamp;

public class MasterSearchDto {

    private String seqNoPair;
    private Integer ahSeqNo;
    private Integer wbhSeqNo;
    private String accountNo;
    private Integer batchId;
    private Integer wasteUnit;
    private String userId;
    // private String ahComments;
    // private String documentNotes;
    private String serviceArea;
    private String batchType;
    private String status;
    // private String wbhComments;

    private Timestamp fromEffectiveDate;
    private Timestamp toEffectiveDate;
    private Timestamp fromStatusDt;
    private Timestamp toStatusDt;

    private Boolean hideOldBatchHistory;

    // Getters
    public String getSeqNoPair() {
        return seqNoPair;
    }

    public Integer getAhSeqNo() {
        return ahSeqNo;
    }

    public Integer getWbhSeqNo() {
        return wbhSeqNo;
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

    public String getUserId() {
        return userId;
    }

    // public String getAhComments() { return ahComments; }
    // public String getDocumentNotes() { return documentNotes; }
    public String getServiceArea() {
        return serviceArea;
    }

    public String getBatchType() {
        return batchType;
    }

    public String getStatus() {
        return status;
    }
    // public String getWbhComments() { return wbhComments; }

    public Timestamp getFromEffectiveDate() {
        return fromEffectiveDate;
    }

    public Timestamp getToEffectiveDate() {
        return toEffectiveDate;
    }

    public Timestamp getFromStatusDt() {
        return fromStatusDt;
    }

    public Timestamp getToStatusDt() {
        return toStatusDt;
    }

    public Boolean getHideOldBatchHistory() {
        return hideOldBatchHistory;
    }

    // Setters
    public void setSeqNoPair(String seqNoPair) {
        this.seqNoPair = seqNoPair;
    }

    public void setAhSeqNo(Integer ahSeqNo) {
        this.ahSeqNo = ahSeqNo;
    }

    public void setWbhSeqNo(Integer wbhSeqNo) {
        this.wbhSeqNo = wbhSeqNo;
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

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // public void setAhComments(String ahComments) { this.ahComments = ahComments;
    // }
    // public void setDocumentNotes(String documentNotes) { this.documentNotes =
    // documentNotes; }
    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    public void setBatchType(String batchType) {
        this.batchType = batchType;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    // public void setWbhComments(String wbhComments) { this.wbhComments =
    // wbhComments; }

    public void setHideOldBatchHistory(Boolean hideOldBatchHistory) {
        this.hideOldBatchHistory = hideOldBatchHistory;
    }

    public void setFromEffectiveDate(Timestamp fromEffectiveDate) {
        this.fromEffectiveDate = fromEffectiveDate;
    }

    public void setToEffectiveDate(Timestamp toEffectiveDate) {
        this.toEffectiveDate = toEffectiveDate;
    }

    public void setFromStatusDt(Timestamp fromStatusDt) {
        this.fromStatusDt = fromStatusDt;
    }

    public void setToStatusDt(Timestamp toStatusDt) {
        this.toStatusDt = toStatusDt;
    }

    // Constructor
    public MasterSearchDto() {
    }

    @Override
    public String toString() {
        return "MasterViewSearchDto [seqNoPair=" + seqNoPair + ", ahSeqNo=" + ahSeqNo + ", wbhSeqNo=" + wbhSeqNo +
                ", accountNo=" + accountNo + ", batchId=" + batchId + ", wasteUnit=" + wasteUnit +
                ", fromEffectiveDate=" + fromEffectiveDate + ", toEffectiveDate=" + toEffectiveDate +
                ", userId=" + userId + ", ahComments=" +
                ", serviceArea=" + serviceArea + ", batchType=" + batchType +
                ", fromStatusDt=" + fromStatusDt + ", toStatusDt=" + toStatusDt + ", status=" + status + "]";
    }
}
