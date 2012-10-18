/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp;

import java.util.List;


public interface IMoRPList<E extends IMoRPObject> extends List<E>{

	/**
	 * Filters out all instances with the meta model of the given name or
	 * its inherited models.
	 * @param metaName
	 * @return
	 */
	IMoRPList<E> filterByMeta(String metaName);
	
	
}
