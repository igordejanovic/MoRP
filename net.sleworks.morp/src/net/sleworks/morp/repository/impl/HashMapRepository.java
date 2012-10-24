package net.sleworks.morp.repository.impl;

import java.util.List;

import net.sleworks.morp.exceptions.DoesNotExistsException;
import net.sleworks.morp.repository.AbstractRepository;
import net.sleworks.morp.repository.IRepositoryElement;
import net.sleworks.morp.repository.IRepositoryLink;
import net.sleworks.morp.repository.IRepositoryObject;
import net.sleworks.morp.repository.RepositoryLinkType;
import net.sleworks.morp.repository.RepositoryObjectType;

public class HashMapRepository extends AbstractRepository {

	@Override
	public IRepositoryObject getObjectByUUID(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getProperty(IRepositoryElement element, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProperty(IRepositoryElement element, String name,
			Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public IRepositoryObject createObject(RepositoryObjectType type, String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends IRepositoryObject> getObjects(
			RepositoryObjectType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRepositoryObject getObject(String uuid)
			throws DoesNotExistsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends IRepositoryObject> getOthersIn(IRepositoryObject obj,
			RepositoryLinkType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends IRepositoryObject> getOthersOut(
			IRepositoryObject obj, RepositoryLinkType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRepositoryLink createLink(RepositoryLinkType type,
			IRepositoryObject from, IRepositoryObject to, String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRepositoryLink getLink(String uuid) throws DoesNotExistsException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public IRepositoryObject getFromSideForLink(IRepositoryLink link) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRepositoryObject getToSideForLink(IRepositoryLink link) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public List<IRepositoryLink> getLinksIn(IRepositoryObject obj,
			RepositoryLinkType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IRepositoryLink> getLinksOut(IRepositoryObject obj,
			RepositoryLinkType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteElement(IRepositoryElement element)
			throws DoesNotExistsException {
		// TODO Auto-generated method stub

	}

	@Override
	public void txBegin() {
		// TODO Auto-generated method stub

	}

	@Override
	public void txSuccess() {
		// TODO Auto-generated method stub

	}

	@Override
	public void txEnd() {
		// TODO Auto-generated method stub

	}

}
