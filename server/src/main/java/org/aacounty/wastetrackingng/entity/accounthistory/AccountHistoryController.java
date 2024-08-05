package org.aacounty.wastetrackingng.entity.accounthistory;

import java.io.FileNotFoundException;
import java.util.List;

import org.aacounty.wastetrackingng.entity.accounthistory.dto.CreateDto;
import org.aacounty.wastetrackingng.entity.accounthistory.dto.UpdateDto;
import org.aacounty.wastetrackingng.entity.applicationuser.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/batches/accounthistory")
public class AccountHistoryController {

    private final AccountHistoryService service;
    private final ApplicationUserRepository applicationUserRepository;

    // Autowiring just to be safe, generally safer to use constructor so conenction
    // happens at compile and not during runtime
    @Autowired
    public AccountHistoryController(AccountHistoryService service,
            ApplicationUserRepository applicationUserRepository) {
        this.service = service;
        this.applicationUserRepository = applicationUserRepository;
    }

    // // Payload format:
    // {
    // "accountNo": "111111111111",
    // "batchId": 1111,
    // "wasteUnit": 1,
    // "effectiveDate": "2024-1-24 00:00:00.000",
    // "userId": "itng0023",
    // "comments": "Howdy!",
    // "documentNotes": "yeehaw",
    // "serviceArea": "1"
    // }
    @PostMapping("/create")
    public ResponseEntity<?> createAccountHistory(@RequestBody CreateDto dto) {
        AccountHistory created = service.createAccountHistory(dto);
        // conversion from entity to DTO if needed
        return ResponseEntity.ok().body(created);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccountHistory(@RequestParam(value = "seqNo") Integer id) {
        service.deleteAccountHistory(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/update")
    public ResponseEntity<?> updateAccountHistory(@RequestBody UpdateDto dto) {
        AccountHistory updated = service.updateAccountHistory(dto);
        // conversion from entity to DTO if needed
        return ResponseEntity.ok().body(updated);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/view")
    public ResponseEntity<?> viewAccountHistory() {
        List<AccountHistory> list = service.viewAccountHistory();
        return ResponseEntity.ok().body(list);
    }

    // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/addFileData")
    public ResponseEntity<?> addFileDataAccountHistory(@RequestParam("f") MultipartFile f,
            @RequestParam("batchId") Integer batchId, @RequestParam("userId") String userId) {

        System.out.println("F ");
        System.out.println(f);

        System.out.println("Batch ID ");
        System.out.println(batchId);

        System.out.println("userId ");
        System.out.println(userId);

        List<AccountHistory> updated = null;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("AUTH ");
        System.out.println(auth);

        if (auth != null && auth.isAuthenticated()) {
            try {
                userId = applicationUserRepository.findUsernameByEmail(auth.getName());
                System.out.println("\n" + userId + "\n");
            } catch (Exception e) {
                // TODO: handle exception
                e.getStackTrace();
            }

            System.out.println("userId:");
            System.out.println(userId);

        }
        System.out.println("Outside IF");
        System.out.println(userId);
        try {
            updated = service.addFileDataAccountHistory(f, batchId, userId);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(updated);
    }
}
