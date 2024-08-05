package org.aacounty.wastetrackingng.entity.masterview;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
// @Table //(name = "MASTER_VIEW")
@Table(name = "master_view")
public class MasterView {

    @Id
    // @Column //(name = "SEQ_NO_PAIR")
    private String seqNoPair;

    // @Column //(name = "AH_SEQ_NO")
    private Integer ahSeqNo;

    // @Column //(name = "WBH_SEQ_NO")
    private Integer wbhSeqNo;

    // @Column //(name = "ACCOUNT_NO")
    private String accountNo;

    // @Column //(name = "BATCH_ID")
    private Integer batchId;

    // @Column //(name = "WASTE_UNIT")
    private Integer wasteUnit;

    // @Column //(name = "EFFECTIVE_DATE")
    private Timestamp effectiveDate;

    // @Column //(name = "USER_ID")
    private String userId;

    // @Column //(name = "AH_COMMENTS")
    private String ahComments;

    // @Column //(name = "DOCUMENT_NOTES")
    private String documentNotes;

    // @Column //(name = "SERVICE_AREA")
    private String serviceArea; // change

    // @Column //(name = "BATCH_TYPE")
    private String batchType;

    // @Column //(name = "STATUS_DT")
    private Timestamp statusDt;

    // @Column //(name = "STATUS")
    private String status;

    // @Column //(name = "WBH_COMMENTS")
    private String wbhComments;

    // Constructor
    public MasterView() {
    }

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
}
