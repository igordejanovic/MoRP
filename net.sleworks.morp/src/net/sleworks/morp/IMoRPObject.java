/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp;

import net.sleworks.morp.repository.IRepositoryObject;


/**
 * This interface defines all MoRP objects. MoRP objects are interconnected using meta 
 * links/relations and they should have a readable String representation.
 */
public interface IMoRPObject extends IRepositoryObject, Comparable<IMoRPObject> {
	
	/**
	 * @return the MoRP objects which defines this object (its meta-object).
	 */
	public IMoRPObject getMeta();

	/**
	 * @return human readable String representation.
	 */
	public String getRepr();
	
}
