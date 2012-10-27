/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp.repository.impl;

import net.sleworks.morp.repository.IBackendObject;
import net.sleworks.morp.repository.IRepository;
import net.sleworks.morp.repository.IRepositoryLink;
import net.sleworks.morp.repository.IRepositoryObject;
import net.sleworks.morp.repository.RepositoryLinkType;

public class RepositoryLink extends RepositoryElement implements
		IRepositoryLink {

	public RepositoryLink(IRepository repository, IBackendObject backendObject) {
		super(repository, backendObject);
	}

	@Override
	public RepositoryLinkType getType() {
		return RepositoryLinkType.valueOf((String)getRepository()
					.getProperty(this, REPOSITORY_ELEMENT_PROPERTY_TYPE_NAME));
	}

	@Override
	public IRepositoryObject getFrom() {
		return this.getRepository().getFromSideForLink(this);
	}

	@Override
	public IRepositoryObject getTo() {
		return this.getRepository().getToSideForLink(this);
	}

}
