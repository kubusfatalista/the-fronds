package com.fronds.domain.model;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "relationships")
@Access(value = AccessType.FIELD)
public class Relationship {
	
	@Id
    @Column
    private int relationshipId;
	
    @Column
    @CreationTimestamp
    private Date creationDate;
    
    @Column
    @UpdateTimestamp
    private Date lastUpdateDate;
	
    @Enumerated(value = EnumType.STRING)
    private RelationshipStatus relationshipStatus;

	@ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user; // kto pierwszy wysle zaproszenie ten jest w userId

    @ManyToOne
    @JoinColumn(name = "friendId", nullable = false)
    private User friend;

	public int getRelationshipId() {
		return relationshipId;
	}

	public void setRelationshipId(int relationshipId) {
		this.relationshipId = relationshipId;
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

	public RelationshipStatus getRelationshipStatus() {
		return relationshipStatus;
	}

	public void setRelationshipStatus(RelationshipStatus relationshipStatus) {
		this.relationshipStatus = relationshipStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}
}
