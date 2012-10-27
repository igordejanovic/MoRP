/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.sleworks.morp.IModel;
import net.sleworks.morp.IModelI;
import net.sleworks.morp.IProperty;
import net.sleworks.morp.IReference;
import net.sleworks.morp.IReferenceI;
import net.sleworks.morp.MoRPConstants;
import net.sleworks.morp.exceptions.DoesNotExistsException;
import net.sleworks.morp.impl.Model;
import net.sleworks.morp.impl.ModelI;
import net.sleworks.morp.impl.Property;
import net.sleworks.morp.impl.Reference;
import net.sleworks.morp.impl.ReferenceI;

public abstract class AbstractRepository implements IRepository {

	public AbstractRepository() {
		super();
	}

	protected IRepositoryObject wrap(IRepositoryObject obj) {
		RepositoryObjectType type = obj.getRepositoryObjectType();
		IRepositoryObject target = obj;
		
		switch(type){
		case MORP_MODEL: 
			target = new Model(obj);
			break;
		case MORP_MODELI:
			target = new ModelI(obj);
			break;
		case MORP_REFERENCEI:
			target = new ReferenceI(obj);
			break;
		case MORP_PROPERTY:
			target = new Property(obj);
			break;
		case MORP_REFERENCE:
			target = new Reference(obj);
			break;
		}
		
		return target;
	}

		
	// #########################################################################################
	// ###################  Support for the MoRP model
	// #########################################################################################
	
	@Override
	public IModel createModel(String name, String uuid) {
		Model newModel = new Model(createObject(RepositoryObjectType.MORP_MODEL, uuid));
		IModel modelModel = null;
		try {
			modelModel = getModelByUUID(MoRPConstants.UUID_MODEL);
			newModel.setMeta(modelModel);
			newModel.setName(name);
			newModel.setAbstract(false);
			
		} catch (DoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newModel;
	}

	@Override
	public IModel createModel(String name) {
		return createModel(name, UUID.randomUUID().toString());
	}

	@Override
	public IReference createReference(String name, IModel type, String uuid) {
		IModel refModel;
		Reference ref = null;
		try {
			refModel = getModelByUUID(MoRPConstants.UUID_REFERENCE);
			ref =  new Reference(createObject(RepositoryObjectType.MORP_REFERENCE, uuid));
			ref.setMeta(refModel);
			ref.setName(name);
			ref.setContainment(false); // containment is false by default
			ref.setLowerBound(1);  // default cardinality is 1..1
			ref.setUpperBound(1);
			if(type != null){
				ref.setType(type);
			}
			
		} catch (DoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		if(type != null){
//			ref.addReferenceI(getSandbox().getReferenceByUUID(MoRPConstants.UUID_REFERENCE_TYPE), type);
//		}
		
		return ref;
	}

	@Override
	public IReference createReference(String name, IModel type) {
		return createReference(name, type, UUID.randomUUID().toString());
	}

	@Override
	public IProperty createProperty(String name, IModel type, String uuid) {
		IModel propModel;
		Property prop = null;
		try {
			propModel = getModelByUUID(MoRPConstants.UUID_PROPERTY);
			prop =  new Property(createObject(RepositoryObjectType.MORP_PROPERTY, uuid));
			prop.setMeta(propModel);
			prop.setName(name);
			prop.setLowerBound(1);  // default cardinality is 1..1
			prop.setUpperBound(1);
			prop.setType(type);
			
//			prop.addReferenceI(repository.getReferenceByUUID(MoRPConstants.UUID_PROPERTY_TYPE), type);

		} catch (DoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}

	@Override
	public IProperty createProperty(String name, IModel type) {
		return createProperty(name, type, UUID.randomUUID().toString());
	}

	@Override
	public IModelI createModelI(IModel meta, String uuid) {
		ModelI m = new ModelI(createObject(RepositoryObjectType.MORP_MODELI, uuid));
		m.setMeta(meta);
		return m;
	}

	@Override
	public IModelI createModelI(IModel meta) {
		return createModelI(meta, UUID.randomUUID().toString());
	}

	@Override
	public IReferenceI createReferenceI(IReference meta, IModelI from,
			IModelI to, String uuid) {
		
		ReferenceI refI = new ReferenceI(createObject(RepositoryObjectType.MORP_REFERENCEI, uuid));
		refI.setMeta(meta);
		refI.setFrom(from);
		refI.setTo(to);
		return refI;
	}

	@Override
	public IReferenceI createReferenceI(IReference meta, IModelI from,
			IModelI to) {
		
		return createReferenceI(meta, from, to, UUID.randomUUID().toString());
		
	}
	
	
	// #########################################################################################
	// ###################  Support for the repository graph model
	// #########################################################################################
	

	@Override
	public List<IRepositoryLink> getLinks(IRepositoryObject obj) {
		List<IRepositoryLink> links = new ArrayList<IRepositoryLink>();
		links.addAll(getLinksIn(obj));
		links.addAll(getLinksOut(obj));
		return links;
	}

	@Override
	public List<IRepositoryLink> getLinks(IRepositoryObject obj,
			RepositoryLinkType type) {
		List<IRepositoryLink> links = new ArrayList<IRepositoryLink>();
		links.addAll(getLinksIn(obj, type));
		links.addAll(getLinksOut(obj, type));
		return links;
	}

	
	
	@Override
	public List<IRepositoryLink> getLinksIn(IRepositoryObject obj) {
		return getLinksIn(obj, RepositoryLinkType.ALL);
	}

	@Override
	public List<IRepositoryLink> getLinksOut(IRepositoryObject obj) {
		return getLinksOut(obj, RepositoryLinkType.ALL);
	}

	@Override
	public List<? extends IRepositoryObject> getOthersIn(IRepositoryObject obj) {
		return getOthersIn(obj, RepositoryLinkType.ALL);
	}

	@Override
	public List<? extends IRepositoryObject> getOthersOut(
			IRepositoryObject obj) {
		return getOthersOut(obj, RepositoryLinkType.ALL);
	}

	@Override
	public IModel getModelByUUID(String uuid) throws DoesNotExistsException {
		IRepositoryObject model = wrap(getObjectByUUID(uuid));
		if(!(model instanceof IModel))
			throw new NotValidRepositoryObject();
		return (IModel)model;
	}

	@Override
	public IReference getReferenceByUUID(String uuid) throws DoesNotExistsException {
		IRepositoryObject ref = wrap(getObjectByUUID(uuid));
		if(!(ref instanceof IReference))
			throw new NotValidRepositoryObject();
		return (IReference)ref;
	}

}
