package com.tekizma.coreServices;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.Query;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;

public class CommonDaoImpl<T> implements CommonDao<T>{
    
    private final JPAApi jpaApi;
    
    @Inject
    public CommonDaoImpl(JPAApi jpaApi){
        this.jpaApi = jpaApi;    
    }
    
    public T find(Class<T> clazz, Object id){
        return (T)jpaApi.em().find(clazz,id);    
    }
    
    public void create(T t){
       jpaApi.em().persist(t);
    }
    
    public T update(T t){
        return jpaApi.em().merge(t);
    } 
    
    public List<T> executeNativeQuery(String sqlScript){
    	List<T> list = (List<T>) jpaApi.em().createQuery(sqlScript).getResultList();
		return list;
    }

	@Override
	public void delete(T t) {
		t = jpaApi.em().merge(t);
		jpaApi.em().remove(t);
	}
}