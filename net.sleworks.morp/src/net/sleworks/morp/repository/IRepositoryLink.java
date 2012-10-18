/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp.repository;

public interface IRepositoryLink extends IRepositoryElement {

	IRepositoryObject getFrom();
	
	IRepositoryObject getTo();
	
	RepositoryLinkType getType();
	
}
