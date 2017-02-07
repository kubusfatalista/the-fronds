package com.fronds.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
 
public abstract class AbstractDao<K extends Serializable, T> {
     
    private final Class<T> persistentClass;
    
    @Autowired
    private SessionFactory sessionFactory;
     
    @SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
 
    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }
    
    protected CriteriaBuilder getCriteriaBuilder() {
    	return getSession().getCriteriaBuilder();
    }
 
    public T getByKey(K key) {
        return (T) getSession().get(persistentClass, key);
    }
 
    public void save(T entity) {
        getSession().save(entity);
    }
    
    public void update(T entity) {
    	getSession().update(entity);
    }
 
    public void delete(T entity) {
        getSession().delete(entity);
    }
}