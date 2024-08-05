package org.aacounty.wastetrackingng.entity.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.aacounty.wastetrackingng.deprecating.BatchRepositoryImpl;
import org.aacounty.wastetrackingng.entity.wastebatch.BatchRequestPayload;
import org.aacounty.wastetrackingng.entity.wastebatch.WasteBatch;

public class FunkyController {

    private BatchRepositoryImpl batchRepository;
    private EmailService emailService;

    @Autowired
    public FunkyController(BatchRepositoryImpl batchRepository, EmailService emailService) {
        this.batchRepository = batchRepository;
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmailToUser(@RequestBody BatchRequestPayload requestPayload) {
        WasteBatch mostRecentBatch = batchRepository.findMostRecentBatchByUserId(requestPayload.getUserId());

        if (mostRecentBatch != null) {
            String emailContent = "Your most recent batch: " + mostRecentBatch.getBatchId();
            emailService.sendEmail(requestPayload.getUserId(), "Most Recent Batch", emailContent);
            return new ResponseEntity<>("Email sent successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No batchs found for the user", HttpStatus.NOT_FOUND);
        }
    }
}
