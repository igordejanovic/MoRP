/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp;

public interface IMultiplicity<T> {

	/**
	 * @return Lower bound of this element. -1 for unbounded.
	 */
	public int getLowerBound();
	
	/**
	 * @param lowerBound
	 * @return This element. To support method chaining.
	 */
	public T setLowerBound(int lowerBound);
	
	/**
	 * @return Upper bound of this element. -1 for unbounded.
	 */
	public int getUpperBound();
	
	/**
	 * @param upperBound
	 * @return
	 */
	public T setUpperBound(int upperBound);

}
