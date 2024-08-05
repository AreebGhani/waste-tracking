package org.aacounty.wastetrackingng.entity.wastebatchhistory;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WasteBatchHistory {
    @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_no_seq")
    // @SequenceGenerator(name = "seq_no_seq", sequenceName = "\"SEQ_NO_SEQ\"",
    // allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seqNo;

    private Integer batchId; // Assuming this is a foreign key, might need @ManyToOne mapping
    private Timestamp statusDt;
    private String status;
    private String userId;
    private String comments;

    // Getters
    public Integer getSeqNo() {
        return seqNo;
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

    public String getComments() {
        return comments;
    }

    // Setters
    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
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

    public void setComments(String comments) {
        this.comments = comments;
    }

    // toString method
    @Override
    public String toString() {
        return "WasteBatchHistory [seqNo=" + seqNo + ", batchId=" + batchId + ", statusDt=" + statusDt +
                ", status=" + status + ", userId=" + userId + ", comments=" + comments + "]";
    }

    // Constructor
    public WasteBatchHistory() {
    }
}
