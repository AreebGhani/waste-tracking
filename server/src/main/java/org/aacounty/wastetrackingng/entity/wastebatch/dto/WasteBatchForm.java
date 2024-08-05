package org.aacounty.wastetrackingng.entity.wastebatch.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WasteBatchForm {
	String warning;
	String warning2;
	String warning3;
	String dialogOpen;
	int batchId;
	String batchTypeSel;
	String batchTypeDesc;
	String notes;
	String suspReason;
	String batchStatusDesc;

	String suspEffectiveDt_;
	String suspFollowUpDt_;
	String userId;
	String toDate;
	String fromDate;
	String comments;

	String accountNbr;
	String streetAddr;
	String streetName;
	String streetType;
	String city;
	String state;
	String zip;
	String wasteUnit;
	String curWasteUnit;

	String serviceArea;
	String seqNo;
	int wasteUnitTotal;

	String totalAdds;
	String totalOccupancys;
	String totalDeletes;
	String totalNetChange;
}