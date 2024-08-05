package org.aacounty.wastetrackingng.entity.wastebatch;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WasteBatch {

    @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
    // "batch_id_seq")
    // @SequenceGenerator(name = "batch_id_seq", sequenceName = "\"BATCH_ID_SEQ\"",
    // allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer batchId;

    private String batchType;
    private LocalDate suspensionEffectDt;
    private LocalDate suspensionFollowDt;
    private String suspensionReason;
    private String documentNotes;

    // Getters
    public Integer getBatchId() {
        return batchId;
    }

    public String getBatchType() {
        return batchType;
    }

    public LocalDate getSuspensionEffectDt() {
        return suspensionEffectDt;
    }

    public LocalDate getSuspensionFollowDt() {
        return suspensionFollowDt;
    }

    public String getSuspensionReason() {
        return suspensionReason;
    }

    public String getDocumentNotes() {
        return documentNotes;
    }

    // Setters
    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public void setBatchType(String batchType) {
        this.batchType = batchType;
    }

    public void setSuspensionEffectDt(LocalDate suspensionEffectDt) {
        this.suspensionEffectDt = suspensionEffectDt;
    }

    public void setSuspensionFollowDt(LocalDate suspensionFollowDt) {
        this.suspensionFollowDt = suspensionFollowDt;
    }

    public void setSuspensionReason(String suspensionReason) {
        this.suspensionReason = suspensionReason;
    }

    public void setDocumentNotes(String documentNotes) {
        this.documentNotes = documentNotes;
    }

    // Constructor
    public WasteBatch() {
    }
}
