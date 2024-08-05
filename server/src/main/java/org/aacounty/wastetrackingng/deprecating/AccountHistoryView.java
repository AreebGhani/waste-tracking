package org.aacounty.wastetrackingng.deprecating;

// For spring boot 3 and java 17
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import jakarta.persistence.Column;

// Entity for the ACCOUNT_HISTORY_VIEW view
//@Entity(name = "ACCOUNT_HISTORY_VIEW")	// taking out the mapping because it's marked for deprecation. Not moving to postgres

/**
public class AccountHistoryView {

    @Id
    private Long seqNo;

    private String accountNo;
    private Integer batchId;
    private Integer wasteUnit;
    private Timestamp effectiveDate;
    private String userId;
    private String comments;
    private String documentNotes;
    private Integer serviceArea;
    private String batchType;
    private String status;


    public AccountHistoryView() {}

    public AccountHistoryView(String accountNo, int batchId, int wasteUnit,
                        Timestamp effectiveDate, String userId, String comments,
                          String documentNotes, Integer serviceArea,
                          String batchType, String status) {
        this.accountNo = accountNo;
        this.batchId = batchId;
        this.wasteUnit = wasteUnit;
        this.effectiveDate = effectiveDate;
        this.userId = userId;
        this.comments = comments;
        this.documentNotes = documentNotes;
        this.serviceArea = serviceArea;
        this.batchType = batchType;
        this.status = status;
    }

    // Getters
    public Long getSeqNo() { return seqNo; }
    public String getAccountNo() { return accountNo; }
    public Integer getBatchId() { return batchId; }
    public Integer getWasteUnit() { return wasteUnit; }
    public Timestamp getEffectiveDate() { return effectiveDate; }
    public String getUserId() { return userId; }
    public String getComments() { return comments; }
    public String getDocumentNotes() { return documentNotes; }
    public Integer getServiceArea() { return serviceArea; }
    public String getBatchType() { return batchType; }
    public String getStatus() { return status; }
   
    // Setters
    public void setSeqNo(Long seqNo) { this.seqNo = seqNo; }
    public void setAccountNo(String accountNo) { this.accountNo = accountNo; }
    public void setBatchId(Integer batchId) { this.batchId = batchId; }
    public void setWasteUnit(Integer wasteUnit) { this.wasteUnit = wasteUnit; }
    public void setEffectiveDate(Timestamp effectiveDate) { this.effectiveDate = effectiveDate; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setComments(String comments) { this.comments = comments; }
    public void setDocumentNotes(String documentNotes) { this.documentNotes = documentNotes; }
    public void setServiceArea(Integer serviceArea) { this.serviceArea = serviceArea; }
    public void setBatchType(String batchType) { this.batchType = batchType; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "AccountHistory [" +
                "seqNo=" + seqNo +
                ", accountNo='" + accountNo + 
                ", batchId=" + batchId +
                ", wasteUnit=" + wasteUnit +
                ", effectiveDate=" + effectiveDate +
                ", userId='" + userId + 
                ", comments='" + comments +
                ", documentNotes='" + documentNotes +
                ", serviceArea='" + serviceArea  +
                ", batchType = '" + batchType +
                ", status = '" + status +
                ']';
    }
}

**/
