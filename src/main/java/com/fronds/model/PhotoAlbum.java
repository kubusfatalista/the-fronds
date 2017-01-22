package com.fronds.model;

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

@Entity
@Table(name = "photoAlbum")
@Access(value = AccessType.FIELD)
public class PhotoAlbum {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long photoAlbumId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	private User user;
	
	@Column
	private String albumName;
	
	@Column
	private String albumDescription;
	
	@Column
	private long albumCreationDate;
	
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "photoAlbum")
    private Set<UserPhoto> userPhotos;

	public long getId() {
		return photoAlbumId;
	}

	public void setId(long id) {
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

	public long getAlbumCreationDate() {
		return albumCreationDate;
	}

	public void setAlbumCreationDate(long albumCreationDate) {
		this.albumCreationDate = albumCreationDate;
	}
	
	public Set<UserPhoto> getUserPhotos() {
		return userPhotos;
	}

	public void setUserPhotos(Set<UserPhoto> userPhotos) {
		this.userPhotos = userPhotos;
	}

}
