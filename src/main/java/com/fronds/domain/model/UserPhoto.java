package com.fronds.domain.model;

import java.util.Date;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "userPhotos")
@Access(value = AccessType.FIELD)
public class UserPhoto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int userPhotoId;
	@Column
	@CreationTimestamp
	private Date creationDate;
	@Column
	@UpdateTimestamp
	private Date lastUpdateDate;
	@Column
	private String imageTitle;
	@Column
	private String imageDescription;
	@Column
	private String imageSavedName;
	@Column
	private double longitude;
	@Column
	private double latitude;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "photoAlbumId", nullable = false)
	private PhotoAlbum photoAlbum;

	public int getUserPhotoId() {
		return userPhotoId;
	}

	public void setUserPhotoId(int userPhotoId) {
		this.userPhotoId = userPhotoId;
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

	public PhotoAlbum getPhotoAlbum() {
		return photoAlbum;
	}

	public void setPhotoAlbum(PhotoAlbum photoAlbum) {
		this.photoAlbum = photoAlbum;
	}

}
