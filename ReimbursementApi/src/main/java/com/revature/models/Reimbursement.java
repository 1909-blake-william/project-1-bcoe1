package com.revature.models;

import java.sql.Timestamp;

public class Reimbursement {
	
	private int reimbId;
	private Double reimbAmount;
	private Timestamp reimbSubmitted;
	private Timestamp reimbResolved;
	private String reimbDescription;
	private int reimbAuthor;
	private int reimbResolver;
	private int reimbStatusId;
	private int reimbTypeId;
	private String reimbName;
	private String reimbResName;
	private String reimbStatus;
	private String reimbType;
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reimbursement(int reimbId, Double reimbAmount, Timestamp reimbSubmitted, Timestamp reimbResolved,
			String reimbDescription, int reimbAuthor, int reimbResolver, int reimbStatusId, int reimbTypeId,
			String reimbName, String reimbResName, String reimbStatus, String reimbType) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
		this.reimbName = reimbName;
		this.reimbResName = reimbResName;
		this.reimbStatus = reimbStatus;
		this.reimbType = reimbType;
	}
	public int getReimbId() {
		return reimbId;
	}
	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}
	public Double getReimbAmount() {
		return reimbAmount;
	}
	public void setReimbAmount(Double reimbAmount) {
		this.reimbAmount = reimbAmount;
	}
	public Timestamp getReimbSubmitted() {
		return reimbSubmitted;
	}
	public void setReimbSubmitted(Timestamp reimbSubmitted) {
		this.reimbSubmitted = reimbSubmitted;
	}
	public Timestamp getReimbResolved() {
		return reimbResolved;
	}
	public void setReimbResolved(Timestamp reimbResolved) {
		this.reimbResolved = reimbResolved;
	}
	public String getReimbDescription() {
		return reimbDescription;
	}
	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}
	public int getReimbAuthor() {
		return reimbAuthor;
	}
	public void setReimbAuthor(int reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}
	public int getReimbResolver() {
		return reimbResolver;
	}
	public void setReimbResolver(int reimbResolver) {
		this.reimbResolver = reimbResolver;
	}
	public int getReimbStatusId() {
		return reimbStatusId;
	}
	public void setReimbStatusId(int reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}
	public int getReimbTypeId() {
		return reimbTypeId;
	}
	public void setReimbTypeId(int reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}
	public String getReimbName() {
		return reimbName;
	}
	public void setReimbName(String reimbName) {
		this.reimbName = reimbName;
	}
	public String getReimbResName() {
		return reimbResName;
	}
	public void setReimbResName(String reimbResName) {
		this.reimbResName = reimbResName;
	}
	public String getReimbStatus() {
		return reimbStatus;
	}
	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}
	public String getReimbType() {
		return reimbType;
	}
	public void setReimbType(String reimbType) {
		this.reimbType = reimbType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reimbAmount == null) ? 0 : reimbAmount.hashCode());
		result = prime * result + reimbAuthor;
		result = prime * result + ((reimbDescription == null) ? 0 : reimbDescription.hashCode());
		result = prime * result + reimbId;
		result = prime * result + ((reimbName == null) ? 0 : reimbName.hashCode());
		result = prime * result + ((reimbResName == null) ? 0 : reimbResName.hashCode());
		result = prime * result + ((reimbResolved == null) ? 0 : reimbResolved.hashCode());
		result = prime * result + reimbResolver;
		result = prime * result + ((reimbStatus == null) ? 0 : reimbStatus.hashCode());
		result = prime * result + reimbStatusId;
		result = prime * result + ((reimbSubmitted == null) ? 0 : reimbSubmitted.hashCode());
		result = prime * result + ((reimbType == null) ? 0 : reimbType.hashCode());
		result = prime * result + reimbTypeId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (reimbAmount == null) {
			if (other.reimbAmount != null)
				return false;
		} else if (!reimbAmount.equals(other.reimbAmount))
			return false;
		if (reimbAuthor != other.reimbAuthor)
			return false;
		if (reimbDescription == null) {
			if (other.reimbDescription != null)
				return false;
		} else if (!reimbDescription.equals(other.reimbDescription))
			return false;
		if (reimbId != other.reimbId)
			return false;
		if (reimbName == null) {
			if (other.reimbName != null)
				return false;
		} else if (!reimbName.equals(other.reimbName))
			return false;
		if (reimbResName == null) {
			if (other.reimbResName != null)
				return false;
		} else if (!reimbResName.equals(other.reimbResName))
			return false;
		if (reimbResolved == null) {
			if (other.reimbResolved != null)
				return false;
		} else if (!reimbResolved.equals(other.reimbResolved))
			return false;
		if (reimbResolver != other.reimbResolver)
			return false;
		if (reimbStatus == null) {
			if (other.reimbStatus != null)
				return false;
		} else if (!reimbStatus.equals(other.reimbStatus))
			return false;
		if (reimbStatusId != other.reimbStatusId)
			return false;
		if (reimbSubmitted == null) {
			if (other.reimbSubmitted != null)
				return false;
		} else if (!reimbSubmitted.equals(other.reimbSubmitted))
			return false;
		if (reimbType == null) {
			if (other.reimbType != null)
				return false;
		} else if (!reimbType.equals(other.reimbType))
			return false;
		if (reimbTypeId != other.reimbTypeId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", reimbAmount=" + reimbAmount + ", reimbSubmitted="
				+ reimbSubmitted + ", reimbResolved=" + reimbResolved + ", reimbDescription=" + reimbDescription
				+ ", reimbAuthor=" + reimbAuthor + ", reimbResolver=" + reimbResolver + ", reimbStatusId="
				+ reimbStatusId + ", reimbTypeId=" + reimbTypeId + ", reimbName=" + reimbName + ", reimbResName="
				+ reimbResName + ", reimbStatus=" + reimbStatus + ", reimbType=" + reimbType + "]";
	}
	
	
}
