/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp.repository;

public interface IRepositoryElement {
	
	public static final String REPOSITORY_ELEMENT_PROPERTY_TYPE_NAME = "__type";
	public static final String REPOSITORY_ELEMENT_PROPERTY_UUID_NAME = "__uuid";

	/**
	 * @return repository this element belongs to.
	 */
	IRepository getRepository();
	
	/**
	 * @return object which represents this element in the backend store.
	 */
	IBackendObject getBackendObject();
	
	/**
	 * @return unique identifier of this repository element. 
	 */
	public String getUUID();

	
}
