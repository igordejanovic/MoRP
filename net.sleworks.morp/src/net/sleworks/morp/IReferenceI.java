/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp;

public interface IReferenceI extends IMoRPObject {

	/**
	 * @return the source model instance. 
	 */
	IModelI getFrom();
	
	/**
	 * @return the destination model instance.
	 */
	IModelI getTo();
	
}
