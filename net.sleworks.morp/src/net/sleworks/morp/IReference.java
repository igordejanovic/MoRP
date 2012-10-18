/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp;

public interface IReference extends IModelI, INamedElement<IReference>, IMultiplicity<IReference>{
	
	/**
	 * @return
	 */
	IModel getType();
	
	/**
	 * @param type
	 * @return
	 */
	IReference setType(IModel type);
	
	/**
	 * @return The owner of this reference.
	 */
	IModel getOwner();
	
	/**
	 * @return
	 */
	IReference getOpposite();
	
	/**
	 * @param opposite
	 * @return
	 */
	IReference setOpposite(IReference opposite);
	
	/**
	 * @return
	 */
	boolean isContainment();
		
	/**
	 * @param containment
	 * @return
	 */
	IReference setContainment(boolean containment);

}
