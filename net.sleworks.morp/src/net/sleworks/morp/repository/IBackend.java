/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp.repository;

import java.util.List;

import net.sleworks.morp.exceptions.DoesNotExistsException;


public interface IBackend {

	// Backend properties
	Object getProperty(IBackendObject obj, String name);
	void setProperty(IBackendObject obj, String name, Object value);
	
	// Backend objects
	IBackendObject createBackendObject(String type, String uuid);
	List<? extends IBackendObject> getBackendObjects(String type);
	IBackendObject getBackendObject(String uuid) throws DoesNotExistsException;
	void deleteBackendObject(String uuid) throws DoesNotExistsException;
	
	// BackendLinks
	void createBackendFromLink(IBackendObject linkObj, IBackendObject obj);	
	void createBackendToLink(IBackendObject linkObj, IBackendObject obj);	
	List<? extends IBackendObject> getBackendLinkObjectsForFromLink(IBackendObject obj);
	List<? extends IBackendObject> getBackendLinkObjectsForToLink(IBackendObject obj);
	IBackendObject getBackendObjectForFromLink(IBackendObject linkObj);
	IBackendObject getBackendObjectForToLink(IBackendObject linkObj);
	void deleteBackendLink(IBackendObject from, IBackendObject to);	
	
				
	// Transaction support
	void txBegin();
	void txSuccess();
	void txEnd();
	
}
