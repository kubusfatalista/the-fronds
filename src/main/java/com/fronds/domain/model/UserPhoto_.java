package com.fronds.domain.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserPhoto.class)
public abstract class UserPhoto_ {

	public static volatile SingularAttribute<UserPhoto, Integer> userPhotoId;
	public static volatile SingularAttribute<UserPhoto, Date> lastUpdateDate;
	public static volatile SingularAttribute<UserPhoto, String> imageDescription;
	public static volatile SingularAttribute<UserPhoto, Double> latitude;
	public static volatile SingularAttribute<UserPhoto, String> imageSavedName;
	public static volatile SingularAttribute<UserPhoto, PhotoAlbum> photoAlbum;
	public static volatile SingularAttribute<UserPhoto, Date> creationDate;
	public static volatile SingularAttribute<UserPhoto, String> imageTitle;
	public static volatile SingularAttribute<UserPhoto, Double> longitude;

}

