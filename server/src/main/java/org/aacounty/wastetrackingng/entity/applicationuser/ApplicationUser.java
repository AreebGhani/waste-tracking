package org.aacounty.wastetrackingng.entity.applicationuser;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String adid;

    private String createdBy;
    private Timestamp createdDate;
    private String emailAddress;
    private String theme;
    private String updatedBy;
    private Timestamp updatedDate;
    private String name;
    private boolean active;
    private boolean sendEmail;

    public int getId() {
        return id;
    }

    public String getAdid() {
        return adid;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getTheme() {
        return theme;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public String getName() {
        return name;
    }

    public boolean getActive() {
        return active;
    }

    public boolean getSendEmail() {
        return sendEmail;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setAdid(String adid) {
        this.adid = adid;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setSendEmail(boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", adid=" + adid + ", createdBy=" + createdBy + ", createdDate=" + createdDate
                + ", emailAddress=" + emailAddress + ", theme=" + theme + ", updatedBy=" + updatedBy + ", updatedDate="
                + updatedDate + ", name=" + name + ", active=" + active + ", sendEmail=" + sendEmail + "]";
    }
}
