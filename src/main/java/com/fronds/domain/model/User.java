package com.fronds.domain.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "users")
@Access(value = AccessType.FIELD)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int userId;
	@Column
	private String login;
	@Column
	private String password;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	@CreationTimestamp
	private Date creationDate;
	@Column
	@UpdateTimestamp
	private Date lastUpdateDate;
	@Column
	private String profilePicture;
	@Enumerated(value = EnumType.STRING)
	private UserRole role;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<PhotoAlbum> photoAlbums;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<TimeMooseStatus> timeMooseStatuses;

	public User() {
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int id) {
		this.userId = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public Set<PhotoAlbum> getPhotoAlbums() {
		return photoAlbums;
	}

	public void setPhotoAlbums(Set<PhotoAlbum> photoAlbums) {
		this.photoAlbums = photoAlbums;
	}

	public List<TimeMooseStatus> getTimeMooseStatuses() {
		return timeMooseStatuses;
	}

	public void setTimeMooseStatuses(List<TimeMooseStatus> timeMooseStatuses) {
		this.timeMooseStatuses = timeMooseStatuses;
	}
}
