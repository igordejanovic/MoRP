/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp.repository;

public class RepositoryLink extends RepositoryElement implements
		IRepositoryLink {

	public RepositoryLink(IRepository repository) {
		super(repository);
	}

	@Override
	public RepositoryLinkType getType() {
		return RepositoryLinkType.valueOf((String)getRepository()
					.getProperty(this, REPOSITORY_OBJECT_PROPERTY_TYPE_NAME));
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
