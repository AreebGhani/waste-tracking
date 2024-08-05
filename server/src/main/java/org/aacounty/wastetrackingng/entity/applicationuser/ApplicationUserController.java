package org.aacounty.wastetrackingng.entity.applicationuser;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.aacounty.wastetrackingng.security.AwsCognitoIdTokenProcessor;
import org.aacounty.wastetrackingng.entity.applicationuser.dto.CreateDto;

@RestController
@RequestMapping("/applicationuser")
public class ApplicationUserController {

    @Autowired
    private ApplicationUserRepository repository;

    @Autowired
    AwsCognitoIdTokenProcessor processor;

    private final ApplicationUserService service;

    // Autowiring just to be safe, generally safer to use constructor so conenction
    // happens at compile and not during runtime
    @Autowired
    public ApplicationUserController(ApplicationUserService service, ApplicationUserRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> createApplicationUser(@RequestBody CreateDto dto) {

        ApplicationUser created = service.createUserDto(dto);
        long user_id = created.getId();
        System.out.println("depart_Id");
        System.out.print(dto.getEmailAddress());
        repository.addUserRole(user_id, dto.getroleid(), dto.getdepartid(), dto.getdefaultrole());
        // conversion from entity to DTO if needed

        return ResponseEntity.ok().body(created);
    }

    @PostMapping("/update")
    @Transactional
    public ResponseEntity<?> updateApplicationUser(@RequestBody CreateDto dto) {
        System.out.println("updating");
        System.out.println(dto.getId() + dto.getAdid() + dto.getEmailAddress());
        service.updateUserFields(dto.getId(), dto.getAdid(), dto.getEmailAddress(), dto.getActive(),
                dto.getSendEmail());
        repository.updateUserRole(dto.getId(), dto.getroleid(), dto.getdepartid(), dto.getdefaultrole());

        return ResponseEntity.ok().body("");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/UserRole")
    public ResponseEntity<?> getUserRole(HttpServletRequest request) throws Exception {
        System.out.println("Printing saved details");
        // You can add your logic here to fetch user roles
        return ResponseEntity.ok().build(); // Example response
    }

    @GetMapping(path = "/test")
    public String getTest() {
        return "Hello";
    }

}
