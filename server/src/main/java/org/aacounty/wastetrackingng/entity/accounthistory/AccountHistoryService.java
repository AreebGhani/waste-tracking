package org.aacounty.wastetrackingng.entity.accounthistory;

import org.aacounty.wastetrackingng.entity.accounthistory.dto.CreateDto;
import org.aacounty.wastetrackingng.entity.accounthistory.dto.UpdateDto;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.icu.text.SimpleDateFormat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

@Service
public class AccountHistoryService {

    // @Autowired
    // private AccountHistoryRepository repository;

    private final AccountHistoryRepository repository;

    @Autowired
    public AccountHistoryService(AccountHistoryRepository repository) {
        this.repository = repository;
    }

    // create Acc Hist
    @Transactional
    @CacheEvict(value = "latestItems", allEntries = true)
    public AccountHistory createAccountHistory(CreateDto dto) {
        AccountHistory accountHistory = new AccountHistory();
        accountHistory.setAccountNo(dto.getAccountNo());
        accountHistory.setBatchId(dto.getBatchId());
        accountHistory.setWasteUnit(dto.getWasteUnit());
        accountHistory.setEffectiveDate(dto.getEffectiveDate());
        accountHistory.setUserId(dto.getUserId());
        accountHistory.setComments(dto.getComments());
        accountHistory.setDocumentNotes(dto.getDocumentNotes());
        accountHistory.setServiceArea(dto.getServiceArea());

        return repository.save(accountHistory);
    }

    // delete Acc Hist
    @Transactional
    @CacheEvict(value = "latestItems", allEntries = true)
    public void deleteAccountHistory(Integer seqNo) {
        // check if the entity exists
        if (!repository.existsById(seqNo)) {
            throw new EntityNotFoundException("AccountHistory with seqNo " + seqNo + " not found.");
        }

        repository.deleteById(seqNo);
    }

    @Transactional
    @CacheEvict(value = "latestItems", allEntries = true)
    public AccountHistory updateAccountHistory(UpdateDto dto) {
        AccountHistory entity = repository.findById(dto.getSeqNo())
                .orElseThrow(() -> new EntityNotFoundException(
                        "AccountHistory with seqNo: " + dto.getSeqNo() + " not found"));

        dto.getAccountNo().ifPresent(entity::setAccountNo);
        dto.getBatchId().ifPresent(entity::setBatchId);
        dto.getWasteUnit().ifPresent(entity::setWasteUnit);
        dto.getEffectiveDate().ifPresent(entity::setEffectiveDate);
        dto.getUserId().ifPresent(entity::setUserId);
        dto.getComments().ifPresent(entity::setComments);
        dto.getDocumentNotes().ifPresent(entity::setDocumentNotes);
        dto.getServiceArea().ifPresent(entity::setServiceArea);

        // repository.save() call is optional here due to transactional context
        // to explicitly save the entity, uncomment the following line:
        // return repository.save(entity);

        // else we can return managed entity directly
        return entity;
    }

    public List<AccountHistory> viewAccountHistory() {
        List<AccountHistory> entityList = repository.findAll();
        return entityList;
    }

    // create Account History from File
    @Transactional
    @CacheEvict(value = "latestItems", allEntries = true)
    public List<AccountHistory> addFileDataAccountHistory(MultipartFile multipartFile, Integer batchId, String userId)
            throws FileNotFoundException {
        List<AccountHistory> results = new ArrayList<AccountHistory>();

        File file = new File(multipartFile.getName());
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            fileOutputStream.write(multipartFile.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

        }

        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            // f= new File ("C:\\Users\\itbans24\\Downloads\\Waste Collection - Waste
            // Collection.csv");
            String line = "";

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy");

            int lineNum = 0;
            // Read the file line by line
            while ((line = fileReader.readLine()) != null) {

                if (lineNum > 6) {
                    AccountHistory accountHistory = new AccountHistory();
                    // Get all tokens available in line
                    String[] tokens = line.split(",");

                    // Verify tokens

                    accountHistory.setAccountNo(tokens[0]);
                    accountHistory.setBatchId(batchId);
                    accountHistory.setWasteUnit(1);

                    Date parsedDate = dateFormat.parse(tokens[tokens.length - 1]);
                    accountHistory.setEffectiveDate(new java.sql.Timestamp(parsedDate.getTime()));
                    accountHistory.setUserId(userId);
                    accountHistory.setComments(null);
                    accountHistory.setDocumentNotes(null);
                    accountHistory.setServiceArea(null);
                    results.add(accountHistory);
                }
                lineNum++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(results);
        // return new ArrayList<AccountHistory>();
        return repository.saveAll(results);
    }

}
