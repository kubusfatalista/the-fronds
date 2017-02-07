package com.fronds.domain.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "timeMooseStatuses")
@Access(value= AccessType.FIELD)
public class TimeMooseStatus {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int timeMooseStatusId;
    @Column
    @CreationTimestamp
    private Date creationDate;
    @Column
    @UpdateTimestamp
    private Date lastUpdateDate;
    @Column
    private String text;
	@Column
	private double longitude;
	@Column
	private double latitude;
	@ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "timeMooseStatus")
	private List<Comment> comments;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "timeMooseStatus")
	private Set<Reaction> reactions;
	
	public TimeMooseStatus() { }

	public int getTimeMooseStatusId() {
		return timeMooseStatusId;
	}

	public void setTimeMooseStatusId(int timeMooseStatusId) {
		this.timeMooseStatusId = timeMooseStatusId;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Set<Reaction> getReactions() {
		return reactions;
	}

	public void setReactions(Set<Reaction> reactions) {
		this.reactions = reactions;
	}
}
