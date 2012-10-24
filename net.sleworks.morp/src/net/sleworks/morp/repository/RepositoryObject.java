/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp.repository;


public class RepositoryObject extends RepositoryElement implements IRepositoryObject {
	
	public RepositoryObject(IBackendObject backendObject, IRepository repository) {
		super(backendObject, repository);
	}

	@Override
	public RepositoryObjectType getRepositoryObjectType() {
		return RepositoryObjectType.valueOf((String)getRepository().getProperty(this, REPOSITORY_OBJECT_PROPERTY_TYPE_NAME));
	}


}
