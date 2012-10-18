/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp.repository;

import java.util.List;

import net.sleworks.morp.exceptions.DoesNotExistsException;


public interface IBackend {

	// Backend properties
	Object getProperty(Object obj, String name);
	void setProperty(Object obj, String name, Object value);
	
	// Backend objects
	Object createBackendObject(String type, String uuid);
	List<Object> getBackendObjects(String type);
	Object getBackendObject(String uuid) throws DoesNotExistsException;
	void deleteBackendObject(Object obj);
	
	// BackendLinks
	void createBackendFromLink(Object linkObj, Object obj);	
	void createBackendToLink(Object linkObj, Object obj);	
	List<Object> getBackendLinkObjectsForFromLink(Object obj);
	List<Object> getBackendLinkObjectsForToLink(Object obj);
	Object getBackendObjectForFromLink(Object linkObj);
	Object getBackendObjectForToLink(Object linkObj);
	void deleteBackendLink(Object from, Object to);	
	
				
	// Transaction support
	void txBegin();
	void txSuccess();
	void txEnd();
	
}
