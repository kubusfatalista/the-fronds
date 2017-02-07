package com.fronds.domain.model;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "reactions")
@Access(value = AccessType.FIELD)
public class Reaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int reactionId;
	@Column
	@CreationTimestamp
	private Date creationDate;
	@Column
	@UpdateTimestamp
	private Date lastUpdateDate;
	@Enumerated(value = EnumType.STRING)
	private ReactionType reactionType;
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;
	@ManyToOne
	@JoinColumn(name = "timeMooseStatusId", nullable = false)
	private TimeMooseStatus timeMooseStatus;

	public Reaction() {
	}

	public int getReactionId() {
		return reactionId;
	}

	public void setReactionId(int reactionId) {
		this.reactionId = reactionId;
	}

	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public ReactionType getReactionType() {
		return reactionType;
	}

	public void setReactionType(ReactionType reactionType) {
		this.reactionType = reactionType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TimeMooseStatus getTimeMooseStatus() {
		return timeMooseStatus;
	}

	public void setTimeMooseStatus(TimeMooseStatus timeMooseStatus) {
		this.timeMooseStatus = timeMooseStatus;
	}
}
