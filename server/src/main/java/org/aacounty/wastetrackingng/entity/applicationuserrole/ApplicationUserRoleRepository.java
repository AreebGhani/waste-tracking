package org.aacounty.wastetrackingng.entity.applicationuserrole;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRoleRepository extends JpaRepository<role, Long> {

    //// void addUserRole(@Param("userId") long userId, @Param("roleId") int roleId,
    //// @Param("departmentId") int departmentId, @Param("isDefaultRole") boolean
    //// isDefaultRole);
    // @Query(value = "SELECT aur.id AS aur_id, " +
    // "au.adid AS au_adid, " +
    // "au.created_by AS au_created_by, " +
    // "au.created_date AS au_created_date, " +
    // "au.email_address AS au_email_address, " +
    // "au.theme AS au_theme, " +
    // "au.updated_by AS au_updated_by, " +
    // "au.updated_date AS au_updated_date, " +
    // "au.name AS au_name, " +
    // "au.active AS au_active, " +
    // "au.send_email AS au_send_email, " +
    // "r.id AS role_id, " +
    // "r.role_type AS role_name, " + // Assuming the column name in the `role`
    //// table is `role_name`
    // "d.id AS department_id, " +
    // "d.name AS department_name " + // Assuming the column name in the
    //// `department` table is `department_name`
    // "FROM \"WASTE_UNIT\".Application_user_role aur " +
    // "LEFT JOIN \"WASTE_UNIT\".application_user au ON aur.user_id = au.id " +
    // "LEFT JOIN \"WASTE_UNIT\".role r ON aur.role_id = r.id " +
    // "LEFT JOIN \"WASTE_UNIT\".department d ON d.id = aur.department_id",
    //// nativeQuery = true)
    // List<Map<String, Object>> findAllUserRolesWithDetails();
    //

}
