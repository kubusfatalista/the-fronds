package com.fronds.domain.model;

import java.util.Date;
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
@Table(name = "photoAlbums")
@Access(value = AccessType.FIELD)
public class PhotoAlbum {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int photoAlbumId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	private User user;
	
	@Column
	private String albumName;
	
	@Column
	private String albumDescription;
	
    @Column
    @CreationTimestamp
    private Date creationDate;
    @Column
    @UpdateTimestamp
    private Date lastUpdateDate;
	
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "photoAlbum")
    private Set<UserPhoto> userPhotos;

	public int getId() {
		return photoAlbumId;
	}

	public void setId(int id) {
		this.photoAlbumId = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getAlbumDescription() {
		return albumDescription;
	}

	public void setAlbumDescription(String albumDescription) {
		this.albumDescription = albumDescription;
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
	
	public Set<UserPhoto> getUserPhotos() {
		return userPhotos;
	}

	public void setUserPhotos(Set<UserPhoto> userPhotos) {
		this.userPhotos = userPhotos;
	}

}
