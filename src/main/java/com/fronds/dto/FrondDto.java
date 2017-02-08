package com.fronds.dto;

import com.fronds.domain.model.User;

public class FrondDto {
	
	private User user;
	private String status; // bardzo tymczasowo ta klasa
	
	public FrondDto(User user, String status) {
		this.user = user;
		this.status = status;
	}

	public User getUser() {
		return user;
	}
	
	public String getStatus() {
		return status;
	}
}
