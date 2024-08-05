package org.aacounty.wastetrackingng.entity.alluserdetails;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
@Entity
@Table(name = "alluserdetails_view")
public class AllUserDetails {
	 @Id
	    @Column(name = "user_id")
	    private int userid;
	 
	    @Column(name = "aur_id")
	    private int aurId;

	    @Column(name = "au_adid")
	    private String auAdid;

	    @Column(name = "au_created_by")
	    private String auCreatedBy;

	    @Column(name = "au_created_date")
	    private Timestamp auCreatedDate;

	    @Column(name = "au_email_address")
	    private String auEmailAddress;

	    @Column(name = "au_theme")
	    private String auTheme;

	    @Column(name = "au_updated_by")
	    private String auUpdatedBy;

	    @Column(name = "au_updated_date")
	    private Timestamp auUpdatedDate;

	    @Column(name = "au_name")
	    private String auName;

	    @Column(name = "au_active")
	    private boolean auActive;

	    @Column(name = "au_send_email")
	    private boolean auSendEmail;

	    @Column(name = "role_id")
	    private int roleId;

	    @Column(name = "role_name")
	    private String roleName;

	    @Column(name = "department_id")
	    private int departmentId;

	    @Column(name = "department_name")
	    private String departmentName;
	    @Column(name = "default_role")
	    private boolean defaultrole;
	    

    // Add getters and setters for each field
	    public boolean getdefaultrole()
	    {
	    	return defaultrole;
	    }
	    public void setdefaultrole(boolean default_role )
	    {
	    	this.defaultrole=default_role;
	    }
    public String getAuAdid() {
        return auAdid;
    }

    public int getuser_id() {
        return userid;
    }
    public void setuser_id(int user_id) {
         this.userid=user_id;
    }
    
    public void setAuAdid(String auAdid) {
        this.auAdid = auAdid;
    }

    public String getAuCreatedBy() {
        return auCreatedBy;
    }

    public void setAuCreatedBy(String auCreatedBy) {
        this.auCreatedBy = auCreatedBy;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Timestamp  getAuCreatedDate() {
        return auCreatedDate;
    }

    public void setAuCreatedDate(Timestamp  auCreatedDate) {
        this.auCreatedDate = auCreatedDate;
    }

    public String getAuName() {
        return auName;
    }

    public void setAuName(String auName) {
        this.auName = auName;
    }

    public String getAuEmailAddress() {
        return auEmailAddress;
    }

    public void setAuEmailAddress(String auEmailAddress) {
        this.auEmailAddress = auEmailAddress;
    }

    public boolean isAuActive() {
        return auActive;
    }

    public void setAuActive(boolean auActive) {
        this.auActive = auActive;
    }

    public String getAuUpdatedBy() {
        return auUpdatedBy;
    }

    public void setAuUpdatedBy(String auUpdatedBy) {
        this.auUpdatedBy = auUpdatedBy;
    }

    public String getAuTheme() {
        return auTheme;
    }

    public void setAuTheme(String auTheme) {
        this.auTheme = auTheme;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getAurId() {
        return aurId;
    }

    public void setAurId(int aurId) {
        this.aurId = aurId;
    }

    public Timestamp  getAuUpdatedDate() {
        return auUpdatedDate;
    }

    public void setAuUpdatedDate(Timestamp  auUpdatedDate) {
        this.auUpdatedDate = auUpdatedDate;
    }

    public boolean isAuSendEmail() {
        return auSendEmail;
    }

    public void setAuSendEmail(boolean auSendEmail) {
        this.auSendEmail = auSendEmail;
    }
 // Method to verify if the role is Guest
    public boolean isGuest() {
        return roleName.equalsIgnoreCase("Guest");
    }

    // Method to verify if the role is Power User
    public boolean isPowerUser() {
        return roleName.equalsIgnoreCase("Power User");
    }

    // Method to verify if the role is Admin
    public boolean isAdmin() {
        return roleName.equalsIgnoreCase("Admin");
    }

    // Method to verify if the role is Supervisor
    public boolean isSupervisor() {
        return roleName.equalsIgnoreCase("Supervisor");
    }
}
