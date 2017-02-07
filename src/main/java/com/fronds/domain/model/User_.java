package com.fronds.domain.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> firstName;
	public static volatile SingularAttribute<User, String> lastName;
	public static volatile SingularAttribute<User, String> profilePicture;
	public static volatile SetAttribute<User, PhotoAlbum> photoAlbums;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, UserRole> role;
	public static volatile SingularAttribute<User, Date> lastUpdateDate;
	public static volatile ListAttribute<User, TimeMooseStatus> timeMooseStatuses;
	public static volatile SingularAttribute<User, String> login;
	public static volatile SingularAttribute<User, Date> creationDate;
	public static volatile SingularAttribute<User, Integer> userId;

}

