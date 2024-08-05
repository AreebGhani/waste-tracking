package org.aacounty.wastetrackingng.entity.applicationuser;

import java.sql.Timestamp;

import org.aacounty.wastetrackingng.entity.applicationuser.dto.CreateDto;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplicationUserService {

    private final ApplicationUserRepository repository;

    @Autowired
    public ApplicationUserService(ApplicationUserRepository repository) {
        this.repository = repository;
    }

    // create user
    @Transactional
    @CacheEvict(value = "latestItems", allEntries = true)
    public ApplicationUser createUserDto(CreateDto dto) {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setAdid(dto.getAdid());
        applicationUser.setCreatedBy(dto.getCreatedBy());
        applicationUser.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        applicationUser.setEmailAddress(dto.getEmailAddress());
        applicationUser.setTheme(dto.getTheme());
        applicationUser.setUpdatedBy(dto.getUpdatedBy());
        applicationUser.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        applicationUser.setName(dto.getName());
        applicationUser.setActive(dto.getActive());
        applicationUser.setSendEmail(dto.getSendEmail());
        return repository.save(applicationUser);
    }

    public void updateUserFields(int i, String adid, String emailAddress, boolean b, boolean c) {
        repository.updateFields(i, adid, emailAddress, b, c);
    }

}
