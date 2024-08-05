package org.aacounty.wastetrackingng.entity.masterview.dto;

import java.sql.Timestamp;

public class AccountHistorySearchDto {

    private Integer ahSeqNo;
    private String accountNo;
    private Integer batchId;
    private Integer wasteUnit;
    private String userId;
    private String serviceArea;
    private String status;
    private Timestamp fromStatusDate;
    private Timestamp toStatusDate;
    private Timestamp fromEffectiveDate;
    private Timestamp toEffectiveDate;

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

    public String getUserId() {
        return userId;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getFromEffectiveDate() {
        return fromEffectiveDate;
    }

    public Timestamp getToEffectiveDate() {
        return toEffectiveDate;
    }

    public Timestamp getFromStatusDate() {
        return fromStatusDate;
    }

    public Timestamp getToStatusDate() {
        return toStatusDate;
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

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFromStatusDate(Timestamp fromStatusDate) {
        this.fromStatusDate = fromStatusDate;
    }

    public void setToStatusDate(Timestamp toStatusDate) {
        this.toStatusDate = toStatusDate;
    }

    public void setFromEffectiveDate(Timestamp fromEffectiveDate) {
        this.fromEffectiveDate = fromEffectiveDate;
    }

    public void setToEffectiveDate(Timestamp toEffectiveDate) {
        this.toEffectiveDate = toEffectiveDate;
    }
}
