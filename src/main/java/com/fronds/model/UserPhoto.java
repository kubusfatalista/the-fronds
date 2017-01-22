package com.fronds.model;

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
import javax.persistence.Table;

@Entity
@Table(name="userPhoto")
@Access(value = AccessType.FIELD)
public class UserPhoto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
	private long userPhotoId;
	@Column
	private long creationDate;
	@Column
	private String imageTitle;
	@Column
	private String imageDescription;
	@Column
	private String imageSavedName;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "photoAlbumId", nullable = false)
	private PhotoAlbum photoAlbum;
	public long getUserPhotoId() {
		return userPhotoId;
	}
	public void setUserPhotoId(long userPhotoId) {
		this.userPhotoId = userPhotoId;
	}
	public long getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(long creationDate) {
		this.creationDate = creationDate;
	}
	public String getImageTitle() {
		return imageTitle;
	}
	public void setImageTitle(String imageTitle) {
		this.imageTitle = imageTitle;
	}
	public String getImageDescription() {
		return imageDescription;
	}
	public void setImageDescription(String imageDescription) {
		this.imageDescription = imageDescription;
	}
	public String getImageSavedName() {
		return imageSavedName;
	}
	public void setImageSavedName(String imageSavedName) {
		this.imageSavedName = imageSavedName;
	}
	public PhotoAlbum getPhotoAlbum() {
		return photoAlbum;
	}
	public void setPhotoAlbum(PhotoAlbum photoAlbum) {
		this.photoAlbum = photoAlbum;
	}

}
