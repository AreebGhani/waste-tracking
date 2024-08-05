package org.aacounty.wastetrackingng.entity.alluserdetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface AllUserDetailsRepository extends JpaRepository<AllUserDetails, String>, JpaSpecificationExecutor<AllUserDetails> {
	@Query("SELECT aud FROM AllUserDetails aud WHERE aud.auEmailAddress = ?1")
    AllUserDetails findAllByAuEmailAddress(String auEmailAddress);
//	@Query("SELECT aud FROM AllUserDetails aud WHERE aud.au_adid = :auAdid")
//    AllUserDetails findByAdid(@Param("auAdid") String auAdid);
}