package org.aacounty.wastetrackingng.entity.applicationuser.dto;

import java.sql.Timestamp;

public class CreateDto {

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
    private int role_id;
    private int user_id;
    private int departmentId;
    private String department_name;
    private String role_type;
    private boolean defaultrole;

    public boolean getdefaultrole() {
        return defaultrole;
    }

    public void setdefaultrole(boolean defaultrole) {
        this.defaultrole = defaultrole;
    }

    public int getId() {
        return id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public String getRole_type() {
        return role_type;
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

    public int getroleid() {
        return role_id;
    }

    public int getuserid() {
        return user_id;
    }

    public int getdepartid() {
        return departmentId;
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

    public void setroleid(int roleid) {
        this.role_id = roleid;
    }

    public void setuserid(int userid) {
        this.user_id = userid;
    }

    public void setdepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setRole_type(String role_type) {
        this.role_type = role_type;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    // Constructor
    public CreateDto() {
    }
}
