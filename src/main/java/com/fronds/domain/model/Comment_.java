package com.fronds.domain.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Comment.class)
public abstract class Comment_ {

	public static volatile SingularAttribute<Comment, TimeMooseStatus> timeMooseStatus;
	public static volatile SingularAttribute<Comment, Date> lastUpdateDate;
	public static volatile SingularAttribute<Comment, Integer> commentId;
	public static volatile SingularAttribute<Comment, String> text;
	public static volatile SingularAttribute<Comment, Date> creationDate;
	public static volatile SingularAttribute<Comment, User> user;

}

