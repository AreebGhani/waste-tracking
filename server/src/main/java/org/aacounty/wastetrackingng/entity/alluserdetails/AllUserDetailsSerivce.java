package org.aacounty.wastetrackingng.entity.alluserdetails;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllUserDetailsSerivce {
	
	// @Autowired
    // private SuspendedBatchesViewRepository repository;

    private final AllUserDetailsRepository repository;

    @Autowired
    public AllUserDetailsSerivce(AllUserDetailsRepository repository) {
        this.repository = repository;
    }
    
	public List <AllUserDetails> viewUsers() {
        List <AllUserDetails> entityList = repository.findAll();
        return entityList;
    }
}
