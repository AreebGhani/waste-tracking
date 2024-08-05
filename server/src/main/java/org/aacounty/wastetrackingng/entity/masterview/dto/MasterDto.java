package org.aacounty.wastetrackingng.entity.masterview.dto;

import java.sql.Timestamp;

public class MasterDto {

    private String seqNoPair;
    private Integer ahSeqNo;
    private Integer wbhSeqNo;
    private String accountNo;
    private Integer batchId;
    private Integer wasteUnit;
    private Timestamp effectiveDate;
    private String userId;
    private String ahComments;
    private String documentNotes;
    private String serviceArea;
    private String batchType;
    private Timestamp statusDt;
    private String status;
    private String wbhComments;

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

    public String getBatchType() {
        return batchType;
    }

    public Timestamp getStatusDt() {
        return statusDt;
    }

    public String getStatus() {
        return status;
    }

    public String getWbhComments() {
        return wbhComments;
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

    public void setBatchType(String batchType) {
        this.batchType = batchType;
    }

    public void setStatusDt(Timestamp statusDt) {
        this.statusDt = statusDt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setWbhComments(String wbhComments) {
        this.wbhComments = wbhComments;
    }

    // Constructor
    public MasterDto() {
    }

    @Override
    public String toString() {
        return "MasterViewDto [seqNoPair=" + seqNoPair + ", ahSeqNo=" + ahSeqNo + ", wbhSeqNo=" + wbhSeqNo +
                ", accountNo=" + accountNo + ", batchId=" + batchId + ", wasteUnit=" + wasteUnit +
                ", effectiveDate=" + effectiveDate + ", userId=" + userId + ", ahComments=" + ahComments +
                ", documentNotes=" + documentNotes + ", serviceArea=" + serviceArea + ", batchType=" + batchType +
                ", statusDt=" + statusDt + ", status=" + status + ", wbhComments=" + wbhComments + "]";
    }
}
