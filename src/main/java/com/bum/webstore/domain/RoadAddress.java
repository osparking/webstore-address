package com.bum.webstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class RoadAddress {
	private String mgmtNumber;
	private String newZipcode;
	private String roadName;
	private String detailAddr;

	public String getMgmtNumber() {
		return mgmtNumber;
	}

	public void setMgmtNumber(String mgmtNumber) {
		this.mgmtNumber = mgmtNumber;
	}

	public String getNewZipcode() {
		return newZipcode;
	}

	public void setNewZipcode(String newZipcode) {
		this.newZipcode = newZipcode;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	@Override
	public String toString() {
		return "(우편번호:" + newZipcode + ") " + roadName + ", " + detailAddr;
	}
}
