/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp;

import net.sleworks.morp.exceptions.DoesNotExistsException;

public interface IModelI extends IMoRPObject {
	
	/**
	 * Checks if this model instance is instance of meta MoRP object.
	 * This instance may be directly connected by meta link to given meta model or to any of its inherited models if meta is Model.
	 * @param metaName
	 * @return
	 */
	boolean isInstanceOf(final IMoRPObject meta);
		
	/**
	 * @param repr
	 * @return
	 */
	IMoRPList<IModelI> getModelIByRepr(String repr);
	
//	/**
//	 * @return
//	 */
//	IMoRPList<IModelI> getContainedModelI();
	
	/**
	 * @param meta
	 * @param value
	 * @return
	 */
	IModelI setPropertyI(IProperty meta, Object value);

	/**
	 * @param meta
	 * @param value
	 * @return
	 */
	IModelI setPropertyI(String name, Object value);
	
	/**
	 * @param meta
	 * @return
	 */
	Object getPropertyI(IProperty meta);
	
	/**
	 * @param name
	 * @return the name of the meta IProperty
	 */
	Object getPropertyI(String name);
		
	/**
	 * @param meta
	 * @param other
	 * @return
	 */
	IModelI addReferenceI(IReference meta, IModelI other);

	/**
	 * @param metaName
	 * @param other
	 * @return
	 * @throws DoesNotExistsException 
	 */
	IModelI addReferenceI(String metaName, IModelI other) throws DoesNotExistsException;

	/**
	 * @param meta
	 * @param other
	 * @return
	 */
	IModelI removeReferenceI(IReference meta, IModelI other);
	
	/**
	 * @return
	 */
	IMoRPList<IReferenceI> getReferenceI();

	/**
	 * @param meta
	 * @return
	 */
	IMoRPList<IReferenceI> getReferenceI(IReference meta);
	
	/**
	 * @param metaName the name of the meta IReference
	 * @return
	 * @throws DoesNotExistsException 
	 */
	IMoRPList<IReferenceI> getReferenceI(String metaName) throws DoesNotExistsException;

	/**
	 * Returns model instances connected by the reference instances of given type.
	 * @param meta
	 * @return
	 */
	IMoRPList<? extends IModelI> getOthers(IReference meta);
	
	/**
	 * Returns model instances connected by the reference instances of given name.
	 * @param refName
	 * @return
	 * @throws DoesNotExistsException 
	 */
	IMoRPList<? extends IModelI> getOthers(String refName) throws DoesNotExistsException;
	
	/**
	 * Return model instance connected by the reference of the given type.
	 * Upper bound of reference cardinality must be 1.
	 * @param meta
	 * @return
	 */
	IModelI getOther(IReference meta);
	
	/**
	 * Returns model instances connected by the reference instances of given name.
	 * Upper bound of reference cardinality must be 1.
	 * @param refName
	 * @return
	 * @throws DoesNotExistsException 
	 */
	IModelI getOther(String refName) throws DoesNotExistsException;

}
