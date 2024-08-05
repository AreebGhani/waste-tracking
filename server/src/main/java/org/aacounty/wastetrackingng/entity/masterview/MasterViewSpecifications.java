package org.aacounty.wastetrackingng.entity.masterview;

import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.aacounty.wastetrackingng.entity.masterview.dto.AccountHistorySearchDto;
import org.aacounty.wastetrackingng.entity.masterview.dto.WasteBatchSearchDto;
import org.aacounty.wastetrackingng.entity.masterview.dto.MasterSearchDto;

public class MasterViewSpecifications {

    public static Specification<MasterView> findByBatchCriteria(WasteBatchSearchDto searchDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (searchDto.getBatchId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("batchId"), searchDto.getBatchId()));
            }
            if (searchDto.getStatus() != null && !searchDto.getStatus().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("status"), searchDto.getStatus()));
            }
            if (searchDto.getUserId() != null && !searchDto.getUserId().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("userId"), searchDto.getUserId()));
            }
            if (searchDto.getBatchType() != null && !searchDto.getBatchType().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("batchType"), searchDto.getBatchType()));
            }
            // Date range handling
            if (searchDto.getFromStatusDate() != null && searchDto.getToStatusDate() != null) {
                predicates.add(criteriaBuilder.between(root.get("statusDt"), searchDto.getFromStatusDate(),
                        searchDto.getToStatusDate()));
            } else if (searchDto.getFromStatusDate() != null) {
                predicates
                        .add(criteriaBuilder.greaterThanOrEqualTo(root.get("statusDt"), searchDto.getFromStatusDate()));
            } else if (searchDto.getToStatusDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("statusDt"), searchDto.getToStatusDate()));
            }
            if (searchDto.getServiceArea() != null) {
                predicates.add(criteriaBuilder.equal(root.get("serviceArea"), searchDto.getServiceArea()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<MasterView> findByAccHistCriteria(AccountHistorySearchDto searchDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (searchDto.getBatchId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("batchId"), searchDto.getBatchId()));
            }
            if (searchDto.getAccountNo() != null && !searchDto.getAccountNo().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("accountNo"), searchDto.getAccountNo()));
            }
            if (searchDto.getServiceArea() != null) {
                predicates.add(criteriaBuilder.equal(root.get("serviceArea"), searchDto.getServiceArea()));
            }
            if (searchDto.getUserId() != null && !searchDto.getUserId().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("userId"), searchDto.getUserId()));
            }
            if (searchDto.getWasteUnit() != null) {
                predicates.add(criteriaBuilder.equal(root.get("wasteUnit"), searchDto.getWasteUnit()));
            }
            if (searchDto.getStatus() != null && !searchDto.getStatus().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("status"), searchDto.getStatus()));
            }
            // Date range handling
            if (searchDto.getFromStatusDate() != null && searchDto.getToStatusDate() != null) {
                predicates.add(criteriaBuilder.between(root.get("statusDt"), searchDto.getFromStatusDate(),
                        searchDto.getToStatusDate()));
            } else if (searchDto.getFromStatusDate() != null) {
                predicates
                        .add(criteriaBuilder.greaterThanOrEqualTo(root.get("statusDt"), searchDto.getFromStatusDate()));
            } else if (searchDto.getToStatusDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("statusDt"), searchDto.getToStatusDate()));
            }
            // Date range handling
            if (searchDto.getFromEffectiveDate() != null && searchDto.getToEffectiveDate() != null) {
                predicates.add(criteriaBuilder.between(root.get("effectiveDate"), searchDto.getFromEffectiveDate(),
                        searchDto.getToEffectiveDate()));
            } else if (searchDto.getFromEffectiveDate() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("effectiveDate"),
                        searchDto.getFromEffectiveDate()));
            } else if (searchDto.getToEffectiveDate() != null) {
                predicates.add(
                        criteriaBuilder.lessThanOrEqualTo(root.get("effectiveDate"), searchDto.getToEffectiveDate()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<MasterView> findByMasterCriteria(MasterSearchDto searchDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (searchDto.getBatchId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("batchId"), searchDto.getBatchId()));
            }
            if (searchDto.getAccountNo() != null && !searchDto.getAccountNo().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("accountNo"), searchDto.getAccountNo()));
            }
            if (searchDto.getServiceArea() != null) {
                predicates.add(criteriaBuilder.equal(root.get("serviceArea"), searchDto.getServiceArea()));
            }
            if (searchDto.getUserId() != null && !searchDto.getUserId().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("userId"), searchDto.getUserId()));
            }
            if (searchDto.getWasteUnit() != null) {
                predicates.add(criteriaBuilder.equal(root.get("wasteUnit"), searchDto.getWasteUnit()));
            }
            if (searchDto.getStatus() != null && !searchDto.getStatus().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("status"), searchDto.getStatus()));
            }
            // Date range handling
            if (searchDto.getFromStatusDt() != null && searchDto.getToStatusDt() != null) {
                predicates.add(criteriaBuilder.between(root.get("statusDt"), searchDto.getFromStatusDt(),
                        searchDto.getToStatusDt()));
            } else if (searchDto.getFromStatusDt() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("statusDt"), searchDto.getFromStatusDt()));
            } else if (searchDto.getToStatusDt() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("statusDt"), searchDto.getToStatusDt()));
            }
            // Date range handling
            if (searchDto.getFromEffectiveDate() != null && searchDto.getToEffectiveDate() != null) {
                predicates.add(criteriaBuilder.between(root.get("effectiveDate"), searchDto.getFromEffectiveDate(),
                        searchDto.getToEffectiveDate()));
            } else if (searchDto.getFromEffectiveDate() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("effectiveDate"),
                        searchDto.getFromEffectiveDate()));
            } else if (searchDto.getToEffectiveDate() != null) {
                predicates.add(
                        criteriaBuilder.lessThanOrEqualTo(root.get("effectiveDate"), searchDto.getToEffectiveDate()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
