/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp.repository;

public class RepositoryElement implements IRepositoryElement {

	private IRepository repository;
	private IBackendObject backendObject;
	
	public RepositoryElement(IRepository repository, IBackendObject obj){
		this.repository = repository;
		this.backendObject = obj;
	}

	public IBackendObject getBackendObject() {
		return backendObject;
	}

	@Override
	public IRepository getRepository() {
		return this.repository;
	}

	@Override
	public int hashCode() {
		return getUUID().hashCode();
	}

	@Override
	public String getUUID() {
		return (String) this.getRepository().getProperty(this, REPOSITORY_ELEMENT_PROPERTY_UUID_NAME);
	}

	@Override
	public boolean equals(Object obj) {
		return obj!=null && this.getBackendObject().equals(((IRepositoryElement)obj).getBackendObject());
	}

	@Override
	public String toString() {
		return String.format("RepositoryObject[%s]", getUUID());
	}
	
}
