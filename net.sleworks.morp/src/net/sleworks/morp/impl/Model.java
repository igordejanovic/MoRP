/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import net.sleworks.morp.IMoRPList;
import net.sleworks.morp.IModel;
import net.sleworks.morp.IProperty;
import net.sleworks.morp.IReference;
import net.sleworks.morp.MoRPConstants;
import net.sleworks.morp.exceptions.DoesNotExistsException;
import net.sleworks.morp.repository.IRepositoryObject;

public class Model extends ModelI implements IModel {

	public Model(IRepositoryObject obj) {
		super(obj);
	}

	@Override
	public String getName() {
		return (String) getPropertyI(MoRPConstants.NAMED_ELEMENT_NAME);
	}

	@Override
	public IModel setName(String name) {
		setPropertyI(MoRPConstants.NAMED_ELEMENT_NAME, name);
		return this;
	}

	@Override
	public boolean getAbstract() {
		return (Boolean) getPropertyI(MoRPConstants.MODEL_ABSTRACT);
	}

	@Override
	public IModel setAbstract(boolean abs) {
		setPropertyI(MoRPConstants.MODEL_ABSTRACT, abs);
		return this;
	}

	@Override
	public IProperty createProperty(String name, IModel type) {
		IProperty prop = null;
		try {
			prop = getRepository().createProperty(name, type);
			addReferenceI(getRepository().getReferenceByUUID(MoRPConstants.UUID_MODEL_PROPERTIES), prop);
		} catch (DoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return prop;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IMoRPList<IProperty> getProperties() {
		IReference metaRef;
		IMoRPList<IProperty> prop = null;
		try {
			metaRef = this.getRepository().getReferenceByUUID(MoRPConstants.UUID_MODEL_PROPERTIES);
			prop = (IMoRPList<IProperty>) getOthers(metaRef);
			for(IModel m: getAllSuperModels()){
				prop.addAll((IMoRPList<IProperty>)m.getOthers(metaRef));
			}
		} catch (DoesNotExistsException e) {
			e.printStackTrace();
		} 
		return prop;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IMoRPList<IProperty> getOwnedProperties() {
		IReference metaRef;
		IMoRPList<IProperty> prop = null;
		try {
			metaRef = this.getRepository().getReferenceByUUID(MoRPConstants.UUID_MODEL_PROPERTIES);
			prop = (IMoRPList<IProperty>) getOthers(metaRef);
		} catch (DoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return prop;
	}

	@Override
	public IProperty getProperty(String name) throws DoesNotExistsException {
		for (IProperty p : this.getProperties()) {
			if (name.equals(p.getName())) {
				return p;
			}
		}
		throw new DoesNotExistsException(String.format("Property with the name '%s' for model '%s' does not exists.", 
				name, this.getName()));
	}

	@Override
	public IReference createReference(String name, IModel type) {
		return createReference(name, type, UUID.randomUUID().toString());
	}

	@Override
	public IReference createReference(String name, String uuid) {
		return createReference(name, null, uuid);
	}

	@Override
	public IReference createReference(String name, IModel type, String uuid) {
		IReference ref = null;
		try {
			ref = getRepository().createReference(name, type, uuid);
			addReferenceI(this.getRepository().getReferenceByUUID(MoRPConstants.UUID_MODEL_REFERENCES), ref);
		} catch (DoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ref;		
	}

	@Override
	public IReference createReference(String name) {
		return createReference(name, null, UUID.randomUUID().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public IMoRPList<IReference> getReferences() {
		IMoRPList<IReference> ref = null;
		try {
			IReference metaRef = this.getRepository().getReferenceByUUID(MoRPConstants.UUID_MODEL_REFERENCES);
			ref = (IMoRPList<IReference>) getOthers(metaRef);
			for(IModel m: getAllSuperModels()){
				ref.addAll((IMoRPList<IReference>)m.getOthers(metaRef));
			}
		} catch (DoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return ref;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IMoRPList<IReference> getOwnedReferences() {
		IMoRPList<IReference> ref = null;
		try {
			IReference metaRef = this.getRepository().getReferenceByUUID(MoRPConstants.UUID_MODEL_REFERENCES);
			ref = (IMoRPList<IReference>) getOthers(metaRef);
		} catch (DoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return ref;
	}

	@Override
	public IReference getReference(String name) throws DoesNotExistsException {
		for (IReference r : this.getReferences()) {
			if (name.equals(r.getName())) {
				return r;
			}
		}
		throw new DoesNotExistsException(String.format("Reference with the name '%s' for model '%s' does not exists.", 
				name, this.getName()));
	}

	@Override
	public IModel addSuperModel(IModel superModel) {
		try {
			addReferenceI(this.getRepository().getReferenceByUUID(MoRPConstants.UUID_MODEL_SUPER_MODELS), superModel);
		} catch (DoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IMoRPList<IModel> getSuperModels() {
		try {
			return (IMoRPList<IModel>) this.getOthers(
					this.getRepository().getReferenceByUUID(MoRPConstants.UUID_MODEL_SUPER_MODELS));
		} catch (DoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public IMoRPList<IModel> getAllSuperModels() {
		Set<IModel> allSuper = new HashSet<IModel>();
		
		IMoRPList<IModel> superModels = getSuperModels();
		allSuper.addAll(superModels);
		for(IModel m: superModels){
			allSuper.addAll(m.getAllSuperModels());
		}
		return new MoRPList<IModel>(allSuper);		
	}

	@Override
	public IMoRPList<IModel> getInheritedModels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMoRPList<IModel> getAllInheritedModels() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IMoRPList<IModel> getInnerModels() {
		try {
			return (IMoRPList<IModel>) getOthers(
					this.getRepository().getReferenceByUUID(MoRPConstants.UUID_MODEL_INNER_MODELS));
		} catch (DoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public IMoRPList<IModel> getAllInnerModels() {
		Set<IModel> allInner = new HashSet<IModel>();
		
		IMoRPList<IModel> innerModels = getInnerModels();
		allInner.addAll(innerModels);
		for(IModel m: innerModels){
			allInner.addAll(m.getAllSuperModels());
		}
		return new MoRPList<IModel>(allInner);
	}

	@Override
	public IModel createInnerModel(String name) {
		IModel model = getRepository().createModel(name);
		try {
			this.addReferenceI(this.getRepository().getReferenceByUUID(MoRPConstants.UUID_MODEL_INNER_MODELS), model);
		} catch (DoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}

	@Override
	public IModel createInnerModel(String name, String uuid) {
		IModel model = getRepository().createModel(name, uuid);
		try {
			this.addReferenceI(this.getRepository().getReferenceByUUID(MoRPConstants.UUID_MODEL_INNER_MODELS), model);
		} catch (DoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}

	@Override
	public IModel getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

}
