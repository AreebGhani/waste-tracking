package org.aacounty.wastetrackingng.entity.accounthistory.dto;

import java.sql.Timestamp;
import java.util.Optional;

public class UpdateDto {

    private Integer seqNo; // Assuming seqNo is mandatory for identifying the record
    private Optional<String> accountNo = Optional.empty();
    private Optional<Integer> batchId = Optional.empty();
    private Optional<Integer> wasteUnit = Optional.empty();
    private Optional<Timestamp> effectiveDate = Optional.empty();
    private Optional<String> userId = Optional.empty();
    private Optional<String> comments = Optional.empty();
    private Optional<String> documentNotes = Optional.empty();
    private Optional<String> serviceArea = Optional.empty();

    // getters
    public Integer getSeqNo() {
        return seqNo;
    }

    public Optional<String> getAccountNo() {
        return accountNo;
    }

    public Optional<Integer> getBatchId() {
        return batchId;
    }

    public Optional<Integer> getWasteUnit() {
        return wasteUnit;
    }

    public Optional<Timestamp> getEffectiveDate() {
        return effectiveDate;
    }

    public Optional<String> getUserId() {
        return userId;
    }

    public Optional<String> getComments() {
        return comments;
    }

    public Optional<String> getDocumentNotes() {
        return documentNotes;
    }

    public Optional<String> getServiceArea() {
        return serviceArea;
    }

    // setters
    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public void setAccountNo(Optional<String> accountNo) {
        this.accountNo = accountNo;
    }

    public void setBatchId(Optional<Integer> batchId) {
        this.batchId = batchId;
    }

    public void setWasteUnit(Optional<Integer> wasteUnit) {
        this.wasteUnit = wasteUnit;
    }

    public void setEffectiveDate(Optional<Timestamp> effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public void setUserId(Optional<String> userId) {
        this.userId = userId;
    }

    public void setComments(Optional<String> comments) {
        this.comments = comments;
    }

    public void setDocumentNotes(Optional<String> documentNotes) {
        this.documentNotes = documentNotes;
    }

    public void setServiceArea(Optional<String> serviceArea) {
        this.serviceArea = serviceArea;
    }

    // Default Constructor
    public UpdateDto() {
    }
}