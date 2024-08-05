package org.aacounty.wastetrackingng.entity.applicationuserrole;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean admin;
    private String roleType;

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for admin
    public boolean isAdmin() {
        return admin;
    }

    // Setter for admin
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    // Getter for roleType
    public String getRoleType() {
        return roleType;
    }

    // Setter for roleType
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}
