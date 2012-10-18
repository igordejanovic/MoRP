/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp.impl;

import java.util.ArrayList;
import java.util.Collection;

import net.sleworks.morp.IMoRPList;
import net.sleworks.morp.IMoRPObject;
import net.sleworks.morp.INamedElement;

public class MoRPList<E extends IMoRPObject> extends ArrayList<E> implements IMoRPList<E> {
	
	public MoRPList() {
		super();
	}

	public MoRPList(Collection<? extends E> c) {
		super(c);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3674623872818606239L;

	/**
	 * Filters out all instances with the meta model of the given name or
	 * its inherited models.
	 * @param metaName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public IMoRPList<E> filterByMeta(String metaName){
		IMoRPList<E> newList = new MoRPList<E>();
		for(E m: this){
			if(m.getMeta() instanceof INamedElement && metaName.equals(((INamedElement) m.getMeta()).getName())){
				newList.add(m);
			}
			// TODO Check if in inherited models.
		}
		return newList;
	}

}
