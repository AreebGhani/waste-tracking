package org.aacounty.wastetrackingng.entity.applicationuser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, String> {
        ApplicationUser findByEmailAddressEquals(String emailAddress);

        @Query("SELECT u.adid FROM ApplicationUser u WHERE u.emailAddress =:email")
        String findUsernameByEmail(@Param("email") String email);

        @Query(value = "SELECT coalesce(max(u.id), 0) FROM ApplicationUser u")
        public int getMaxId();

        @Modifying
        @Query(value = "INSERT INTO \"WASTE_UNIT\".application_user_role (user_id, role_id, department_id, is_default_role) VALUES (:userId, :roleId, :departmentId, :isDefaultRole)", nativeQuery = true)
        void addUserRole(@Param("userId") long userId, @Param("roleId") int roleId,
                        @Param("departmentId") int departmentId, @Param("isDefaultRole") boolean isDefaultRole);

        @Query(value = "SELECT aur, au, r, d FROM \"WASTE_UNIT\".Application_user_role aur " +
                        "LEFT JOIN \"WASTE_UNIT\".application_user au ON aur.user_id = au.id " +
                        "LEFT JOIN \"WASTE_UNIT\".role r ON aur.role_id = r.id " +
                        "LEFT JOIN \"WASTE_UNIT\".department d ON d.id = aur.department_id", nativeQuery = true)
        List<Object[]> findAllUserRolesWithDetails();

        @Query(value = "SELECT * FROM \"WASTE_UNIT\".application_user WHERE adid = :adid", nativeQuery = true)
        ApplicationUser findByAdid(@Param("adid") String adid);

        @Modifying
        @Query(value = "UPDATE \"WASTE_UNIT\".application_user SET name = :ad_id, email_address = :email_Address, active = :isActive, send_email = :sendMail WHERE id = :userId", nativeQuery = true)
        void updateFields(@Param("userId") int userId, @Param("ad_id") String adId,
                        @Param("email_Address") String emailAddress, @Param("isActive") boolean isActive,
                        @Param("sendMail") boolean sendMail);

        @Modifying
        @Query(value = "UPDATE \"WASTE_UNIT\".application_user_role " +
                        "SET role_id = :roleId, department_id = :departmentId, is_default_role = :is_defaultrole " +
                        "WHERE user_id = :userId", nativeQuery = true)
        void updateUserRole(@Param("userId") int userId, @Param("roleId") int roleId,
                        @Param("departmentId") int departmentId, @Param("is_defaultrole") boolean is_defaultrole);

}
