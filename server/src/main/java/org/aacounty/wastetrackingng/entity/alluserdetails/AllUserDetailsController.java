package org.aacounty.wastetrackingng.entity.alluserdetails;

import org.aacounty.wastetrackingng.security.AwsCognitoIdTokenProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/alluser")
public class AllUserDetailsController {

    private final AllUserDetailsSerivce service;
    @Autowired
    AwsCognitoIdTokenProcessor processor;

    @Autowired
    public AllUserDetailsController(AllUserDetailsSerivce service) {
        this.service = service;
    }

    // http://localhost:8080/alluser/details

    @GetMapping("/details")
    public ResponseEntity<?> viewallusers() {

        List<AllUserDetails> list = service.viewUsers();
        return ResponseEntity.ok().body(list);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/UserRole")
    public ResponseEntity<?> getUserRole(HttpServletRequest request) throws Exception {
        System.out.println("printing save details");
        System.out.print(request);
        return null;

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/api/user")
    public ResponseEntity<Optional<AllUserDetails>> getUser(HttpServletRequest request) throws Exception {
        Optional<AllUserDetails> saveduser = null;
        System.out.println("printing request");
        String username = processor.extractUsername(request);
        if (username == null) {
            // Handle the case where the username is null
            // For example, you can return an error response or throw an exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<AllUserDetails> list = service.viewUsers();
        for (AllUserDetails user : list) {
            
        // System.out.println(user);
            if (
                user != null && 
                user.getAuAdid() != null && 
                user.getAuAdid().equals(processor.extractUsername(request)))
            {
                saveduser = Optional.ofNullable(user);
            }
        }

        // System.out.println("Saved User ");
        // System.out.println(saveduser);
        return new ResponseEntity<>(saveduser, HttpStatus.OK);

    }

}
