/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp.repository;

public enum RepositoryLinkType {

	ALL, // Special link type. Matches all other link types.
	
	MORP_META,
	MORP_MODELI, 
	MORP_REFERENCEI_FROM, 
	MORP_REFERENCEI_TO, 
	MORP_MODEL
	
}
