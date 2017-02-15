package com.fronds.dto;

import java.util.Date;

import com.fronds.domain.model.User;

public class FrondDto {
	
	private User user;
	private String status; // bardzo tymczasowo ta klasa
	private Date date; // jednak pewnie nie tak tymczasowo
	
	public FrondDto(User user, String status) {
		this.user = user;
		this.status = status;
		this.date = new Date();
	}
	
	public FrondDto(User user, Date date) {
		this.user = user;
		this.status = "Fronds";
		this.date = date;
	}

	public User getUser() {
		return user;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getDate() {
		return date.toString();
	}
	
}
