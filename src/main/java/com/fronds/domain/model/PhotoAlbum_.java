package com.fronds.domain.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PhotoAlbum.class)
public abstract class PhotoAlbum_ {

	public static volatile SingularAttribute<PhotoAlbum, String> albumName;
	public static volatile SetAttribute<PhotoAlbum, UserPhoto> userPhotos;
	public static volatile SingularAttribute<PhotoAlbum, Integer> photoAlbumId;
	public static volatile SingularAttribute<PhotoAlbum, Date> lastUpdateDate;
	public static volatile SingularAttribute<PhotoAlbum, String> albumDescription;
	public static volatile SingularAttribute<PhotoAlbum, Date> creationDate;
	public static volatile SingularAttribute<PhotoAlbum, User> user;

}

