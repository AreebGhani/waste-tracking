package org.aacounty.wastetrackingng.entity.email;

import org.aacounty.wastetrackingng.entity.applicationuser.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationUserService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    public ApplicationUserRepository getApplicationUserRepository() {
        return this.applicationUserRepository;
    }

    // public boolean isCurrentUserSupervisor(String username) {
    // ApplicationUser user = applicationUserRepository.findByUsername(username)
    // .orElseThrow(() -> new UsernameNotFoundException("User not found: " +
    // username));
    // return user.getIsSupervisor();
    // }
}
