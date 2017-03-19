package com.mysage.dao;

import java.io.Serializable;
import java.util.List;

/***
 * 
 * @author Arnaud Broussel
 *
 * Main operations related to persistance and data access
 *
 * @param <T> Any type
 * @param <Id> Type of the identifier of an instance of T type
 */
public interface Dao<T, Id extends Serializable> {

	public void save(T entity);	
	public void update(T entity);	
	public T findById(Id id);	
	public void delete(T entity);	
	public List<T> findAll();
	
}