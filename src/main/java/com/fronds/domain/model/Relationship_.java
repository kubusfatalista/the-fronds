package com.fronds.domain.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Relationship.class)
public abstract class Relationship_ {

	public static volatile SingularAttribute<Relationship, Date> lastUpdateDate;
	public static volatile SingularAttribute<Relationship, User> friend;
	public static volatile SingularAttribute<Relationship, RelationshipStatus> relationshipStatus;
	public static volatile SingularAttribute<Relationship, Integer> relationshipId;
	public static volatile SingularAttribute<Relationship, Date> creationDate;
	public static volatile SingularAttribute<Relationship, User> user;

}

