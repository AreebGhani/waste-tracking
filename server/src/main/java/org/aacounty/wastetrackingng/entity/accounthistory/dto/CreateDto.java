package org.aacounty.wastetrackingng.entity.accounthistory.dto;

import java.sql.Timestamp;

public class CreateDto {
    private String accountNo;
    private Integer batchId;
    private Integer wasteUnit;
    private Timestamp effectiveDate;
    private String userId;
    private String comments;
    private String documentNotes;
    private String serviceArea;

    // Getters
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

    public String getComments() {
        return comments;
    }

    public String getDocumentNotes() {
        return documentNotes;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    // Setters
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

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setDocumentNotes(String documentNotes) {
        this.documentNotes = documentNotes;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    // Constructor
    public CreateDto() {
    }
}
