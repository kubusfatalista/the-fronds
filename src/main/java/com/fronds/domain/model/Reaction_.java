package com.fronds.domain.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Reaction.class)
public abstract class Reaction_ {

	public static volatile SingularAttribute<Reaction, ReactionType> reactionType;
	public static volatile SingularAttribute<Reaction, TimeMooseStatus> timeMooseStatus;
	public static volatile SingularAttribute<Reaction, Date> lastUpdateDate;
	public static volatile SingularAttribute<Reaction, Integer> reactionId;
	public static volatile SingularAttribute<Reaction, Date> creationDate;
	public static volatile SingularAttribute<Reaction, User> user;

}

