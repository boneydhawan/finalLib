package com.tekizma.coreServices;

import java.util.List;

public interface CommonDao<T>{
    
    public T find(Class<T> clazz, Object id);
    
    public void create(T t);
    
    public T update(T t);
    
    public void delete(T t);
    
    public List<T> executeNativeQuery(String sqlScript);
}