package org.aacounty.wastetrackingng.entity.suspendedbatchesview;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "SUSPENDED_BATCHES_VIEW")
public class SuspendedBatchesView {

	@Id
	@Column(name = "BATCH_ID")
	private Integer batchId;

	@Column(name = "SUSPENSION_EFFECT_DT")
	private LocalDate suspensionEffectDt;

	@Column(name = "SUSPENSION_FOLLOW_DT")
	private LocalDate suspensionFollowDt;

	@Column(name = "ACCOUNT_NO")
	private String accountNo;

	@Column(name = "WASTE_UNIT")
	private Integer wasteUnit;

	// Constructor
	public SuspendedBatchesView() {
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public LocalDate getSuspensionEffectDt() {
		return suspensionEffectDt;
	}

	public void setSuspensionEffectDt(LocalDate suspensionEffectDt) {
		this.suspensionEffectDt = suspensionEffectDt;
	}

	public LocalDate getSuspensionFollowDt() {
		return suspensionFollowDt;
	}

	public void setSuspensionFollowDt(LocalDate suspensionFollowDt) {
		this.suspensionFollowDt = suspensionFollowDt;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Integer getWasteUnit() {
		return wasteUnit;
	}

	public void setWasteUnit(Integer wasteUnit) {
		this.wasteUnit = wasteUnit;
	}

}
