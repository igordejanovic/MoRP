/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp.repository;

import java.util.List;

import net.sleworks.morp.IModel;
import net.sleworks.morp.IModelI;
import net.sleworks.morp.IProperty;
import net.sleworks.morp.IReference;
import net.sleworks.morp.IReferenceI;
import net.sleworks.morp.exceptions.DoesNotExistsException;


public interface IRepository {
	
	// ###########################################################################################
	// #####################         Support for MoRP
	// ###########################################################################################
	
	// Model
	IModel createModel(String name, String uuid);
	IModel createModel(String name);
	IModel getModelByUUID(String uuid) throws DoesNotExistsException;
	
	// Reference
	IReference createReference(String name, IModel type, String uuid);
	IReference createReference(String name, IModel type);
	IReference getReferenceByUUID(String uuid) throws DoesNotExistsException;	
	
	// Property
	IProperty createProperty(String name, IModel type, String uuid);
	IProperty createProperty(String name, IModel type);

	// ModelI
	IModelI createModelI(IModel meta, String uuid);
	IModelI createModelI(IModel meta);

	// ReferenceI
	IReferenceI createReferenceI(IReference meta, IModelI from, IModelI to, String uuid);
	IReferenceI createReferenceI(IReference meta, IModelI from, IModelI to);
	
	
	// ###########################################################################################
	// ############# Support for repository model based on attributed, typed, directed multigraph.
	// ###########################################################################################

	IRepositoryObject getObjectByUUID(String uuid);

	// Repository object property support 
	Object getProperty(IRepositoryElement element, String name);
	void setProperty(IRepositoryElement element, String name, Object value);
	
	// Repository objects
	/**
	 * @param type
	 * @param uuid the UUID of the new element.
	 * @return
	 */
	IRepositoryObject createObject(RepositoryObjectType type, String uuid);
	
	/**
	 * @param type
	 * @return list of repository object of the given type.
	 */
	List<? extends IRepositoryObject> getObjects(RepositoryObjectType type);
	
	/**
	 * @param uuid
	 * @return repository object with the given UUID.
	 * @throws DoesNotExistsException
	 */
	IRepositoryObject getObject(String uuid) throws DoesNotExistsException;

	/**
	 * @param obj
	 * @return list of repository objects connected to this object by the incoming links.
	 */
	List<? extends IRepositoryObject> getOthersIn(IRepositoryObject obj);

	/**
	 * @param obj
	 * @param type
	 * @return list of repository objects connected to this object by the incoming links of the given type.
	 */
	List<? extends IRepositoryObject> getOthersIn(IRepositoryObject obj, RepositoryLinkType type);
	
	/**
	 * @param obj
	 * @return list of repository objects connected to this object by the outgoing links.
	 */
	List<? extends IRepositoryObject> getOthersOut(IRepositoryObject obj);
	
	/**
	 * @param obj
	 * @param type
	 * @return list of repository objects connected to this object by the outgoing links of the given type.
	 */
	List<? extends IRepositoryObject> getOthersOut(IRepositoryObject obj, RepositoryLinkType type);

	// Repository links
	/**
	 * @param type
	 * @param from
	 * @param to
	 * @param uuid
	 * @return
	 */
	IRepositoryLink createLink(RepositoryLinkType type, 
			IRepositoryObject from, IRepositoryObject to, String uuid);

	/**
	 * @param uuid
	 * @return repository link with the given UUID.
	 * @throws DoesNotExistsException
	 */
	IRepositoryLink getLink(String uuid) throws DoesNotExistsException;

	
	/**
	 * @param link link object
	 * @return Repository object which is at the "from" side of the link.
	 */
	IRepositoryObject getFromSideForLink(IRepositoryLink link);
	
	/**
	 * @param link link object
	 * @return Repository object which is at the "to" side of the link.
	 */
	IRepositoryObject getToSideForLink(IRepositoryLink link);

	/**
	 * @param obj
	 * @return list of repository links connected to the given repository object.
	 */
	List<IRepositoryLink> getLinks(IRepositoryObject obj);

	/**
	 * @param obj
	 * @return list of repository links of the given type connected to the given repository object.
	 */
	List<IRepositoryLink> getLinks(IRepositoryObject obj, RepositoryLinkType type);
	
	/**
	 * @param obj
	 * @return list of incoming repository links connected to the given repository object.
	 */
	List<IRepositoryLink> getLinksIn(IRepositoryObject obj);

	/**
	 * @param obj
	 * @return list of incoming repository links of the given type connected to the given repository object.
	 */
	List<IRepositoryLink> getLinksIn(IRepositoryObject obj, RepositoryLinkType type);

	/**
	 * @param obj
	 * @return list of incoming repository links connected to the given repository object.
	 */
	List<IRepositoryLink> getLinksOut(IRepositoryObject obj);

	/**
	 * @param obj
	 * @return list of incoming repository links of the given type connected to the given repository object.
	 */
	List<IRepositoryLink> getLinksOut(IRepositoryObject obj, RepositoryLinkType type);

	/**
	 * @param element delete given repository element. If the given element is object that all connected links
	 * will be deleted also.
	 * @throws DoesNotExistsException 
	 */
	void deleteElement(IRepositoryElement element) throws DoesNotExistsException;
		

	// ###########################################################################################
	// ##########################   Support for transactions
	// ###########################################################################################	
	/**
	 * Begins transaction.
	 */
	public void txBegin();

	/**
	 * Marks transaction as successful
	 */
	public void txSuccess();	
	
	/**
	 * Ends transaction. Commit if marked as successful, rollback otherwise.
	 */
	public void txEnd();

	
}
