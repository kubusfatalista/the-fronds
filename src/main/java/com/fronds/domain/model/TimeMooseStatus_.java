package com.fronds.domain.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TimeMooseStatus.class)
public abstract class TimeMooseStatus_ {

	public static volatile ListAttribute<TimeMooseStatus, Comment> comments;
	public static volatile SingularAttribute<TimeMooseStatus, Date> lastUpdateDate;
	public static volatile SingularAttribute<TimeMooseStatus, Double> latitude;
	public static volatile SetAttribute<TimeMooseStatus, Reaction> reactions;
	public static volatile SingularAttribute<TimeMooseStatus, Integer> timeMooseStatusId;
	public static volatile SingularAttribute<TimeMooseStatus, String> text;
	public static volatile SingularAttribute<TimeMooseStatus, Date> creationDate;
	public static volatile SingularAttribute<TimeMooseStatus, User> user;
	public static volatile SingularAttribute<TimeMooseStatus, Double> longitude;

}

