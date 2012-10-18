/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp;

public interface INamedElement<T> {

	/**
	 * @return The name of this element.
	 */
	public String getName();
	
	/**
	 * @param name
	 * @return This element. To support method call chaining.
	 */
	public T setName(String name);

	
}
