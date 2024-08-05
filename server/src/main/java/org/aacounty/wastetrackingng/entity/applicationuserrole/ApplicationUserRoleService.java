package org.aacounty.wastetrackingng.entity.applicationuserrole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApplicationUserRoleService {

    @Autowired
    private ApplicationUserRoleRepository roleRepository;

    public List<role> getAllRole() {
        return roleRepository.findAll();
    }
}
