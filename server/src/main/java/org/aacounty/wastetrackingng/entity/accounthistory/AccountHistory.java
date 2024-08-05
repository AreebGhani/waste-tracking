package org.aacounty.wastetrackingng.entity.accounthistory;

import java.sql.Timestamp;

// For spring boot 3 and java 17
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import jakarta.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.Column;

// Entity for the ACCOUNT_HISTORY_VIEW view
@Entity
// @Table(name = "ACCOUNT_HISTORY")
public class AccountHistory {

    @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
    // "seq_no_seq2")
    // @SequenceGenerator(name = "seq_no_seq2", sequenceName = "\"SEQ_NO_SEQ2\"",
    // allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seqNo;

    private String accountNo;
    private Integer batchId;
    private Integer wasteUnit;
    private Timestamp effectiveDate;
    private String userId;
    private String comments;
    private String documentNotes;
    private String serviceArea;

    // Constructor
    public AccountHistory() {
    }

    // Getters and Setters
    public Integer getSeqNo() {
        return seqNo;
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

    public String getComments() {
        return comments;
    }

    public String getDocumentNotes() {
        return documentNotes;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
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

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setDocumentNotes(String documentNotes) {
        this.documentNotes = documentNotes;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    @Override
    public String toString() {
        // return "AccountHistory [" +
        // "seqNo=" + seqNo +
        // ", accountNo='" + accountNo +
        // ", batchId=" + batchId +
        // ", wasteUnit=" + wasteUnit +
        // ", effectiveDate=" + effectiveDate +
        // ", userId='" + userId +
        // ", comments='" + comments +
        // ", documentNotes='" + documentNotes +
        // ", serviceArea='" + serviceArea +
        // ']';

        return "SeqNo: " + seqNo + ", Account No: " + accountNo + ", batchId=" + batchId +
                ", wasteUnit=" + wasteUnit +
                ", effectiveDate=" + effectiveDate +
                ", userId='" + userId +
                ", comments='" + comments +
                ", documentNotes='" + documentNotes +
                ", serviceArea='" + serviceArea + "\n\n\n";
    }
}