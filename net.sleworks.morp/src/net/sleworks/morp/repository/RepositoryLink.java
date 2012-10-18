/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp.repository;

public class RepositoryLink extends RepositoryElement implements
		IRepositoryLink {

	public RepositoryLink(Object backendObject, IRepository sandbox) {
		super(backendObject, sandbox);
	}

	@Override
	public RepositoryLinkType getType() {
		return RepositoryLinkType.valueOf((String)getRepository().getProperty(this, REPOSITORY_OBJECT_PROPERTY_TYPE_NAME));
	}

	@Override
	public IRepositoryObject getFrom() {
		return getRepository().wrap(new RepositoryObject(getRepository().getBackend()
				.getBackendObjectForFromLink(this.getBackendObject()), getRepository()));
	}

	@Override
	public IRepositoryObject getTo() {
		return new RepositoryObject(getRepository().getBackend()
				.getBackendObjectForToLink(this.getBackendObject()), getRepository());
	}

}
