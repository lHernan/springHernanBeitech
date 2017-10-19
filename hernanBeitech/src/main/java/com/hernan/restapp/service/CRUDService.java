package com.hernan.restapp.service;

import java.io.Serializable;
import java.util.List;

/**
 * CRUD service for any entity.
 *
 * @author Hernan
 * @param <E>
 *            the element type
 */
public interface CRUDService<E> {

	/**
	 * Save.
	 *
	 * @param entity
	 *            the entity
	 * @return the e
	 */
	E save(E entity);

	/**
	 * Gets the by id.
	 *
	 * @param id
	 *            the id
	 * @return the by id
	 */
	E getById(Serializable id);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<E> getAll();

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 */
	void delete(Serializable id);
}
