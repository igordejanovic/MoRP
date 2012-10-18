/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp;

public interface IProperty extends IModelI, INamedElement<IProperty>, IMultiplicity<IProperty>{
	
	/**
	 * @return the type of this property.
	 */
	public IModel getType();

	/**
	 * @param model
	 * @return this property
	 */
	public abstract IProperty setType(IModel model);

	
	/**
	 * @return
	 */
	public IModel getOwner();

	
}
