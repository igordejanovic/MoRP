/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp.impl;

import java.util.List;

import net.sleworks.morp.IMoRPList;
import net.sleworks.morp.IMoRPObject;
import net.sleworks.morp.IModel;
import net.sleworks.morp.IModelI;
import net.sleworks.morp.IProperty;
import net.sleworks.morp.IReference;
import net.sleworks.morp.IReferenceI;
import net.sleworks.morp.MoRPConstants;
import net.sleworks.morp.exceptions.DoesNotExistsException;
import net.sleworks.morp.repository.IRepositoryObject;
import net.sleworks.morp.repository.RepositoryLinkType;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class ModelI extends MoRPObject implements IModelI {

	public ModelI(IRepositoryObject obj) {
		super(obj);
	}

	@Override
	public boolean isInstanceOf(final IMoRPObject meta) {
		if(meta instanceof IModel){
			return Iterables.any(((IModel)meta).getAllSuperModels(), new Predicate<IModel>() {
	
				@Override
				public boolean apply(IModel input) {
					return input.getMeta().equals(meta);
				}

			});
		}else{
			return getMeta().equals(meta);
		}
	}

	@Override
	public IMoRPList<IModelI> getModelIByRepr(String repr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IModelI setPropertyI(IProperty meta, Object value) {
		return setPropertyI(meta.getName(), value);
	}

	@Override
	public IModelI setPropertyI(String name, Object value) {
		this.getRepository().setProperty(this, name, value);
		return this;
	}

	@Override
	public Object getPropertyI(IProperty meta) {
		return this.getRepository().getProperty(this, meta.getName());
	}

	@Override
	public Object getPropertyI(String name) {
		return this.getRepository().getProperty(this, name);
	}

	@Override
	public IModelI addReferenceI(IReference meta, IModelI other) {
		this.getRepository().createReferenceI(meta, this, other);
		return this;
	}

	private IReference getReference(String name) throws DoesNotExistsException {
		IModel meta = ((IModel)this.getMeta());
		IReference modelReferences = this.getRepository().getReferenceByUUID(MoRPConstants.UUID_MODEL_REFERENCES);
		
		for(IReferenceI r: meta.getReferenceI(modelReferences)){
			if(((IReference)r.getTo()).getName().equals(name)){
				return (IReference)r.getTo();
			}
		}
		throw new DoesNotExistsException(String.format("Reference with the name '%s' for model '%s' does not exists.", 
				name, meta.getName()));
	}
	
	@Override
	public IModelI addReferenceI(String metaName, IModelI other)
			throws DoesNotExistsException {
		IReference meta = getReference(metaName);
		return addReferenceI(meta, other);		
	}

	@Override
	public IModelI removeReferenceI(IReference meta, IModelI other) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param p
	 * @return reference instances that satisfy condition given by predicate p 
	 */
	private IMoRPList<IReferenceI> getReferenceI(Predicate<MoRPObject> p) {
		IMoRPList<IReferenceI> refInsts = new MoRPList<IReferenceI>();
		@SuppressWarnings("unchecked")
		List<IReferenceI> refInstAll = (List<IReferenceI>) this.getRepository().getOthersIn(this, RepositoryLinkType.MORP_REFERENCEI_FROM);

		for(IReferenceI r: refInstAll){
			if(p.apply((MoRPObject) r))
				refInsts.add(r);
		}
		return refInsts;
	}
	
	@Override
	public IMoRPList<IReferenceI> getReferenceI() {
		return getReferenceI(new Predicate<MoRPObject>() {
			@Override
			public boolean apply(MoRPObject obj) {
				return true;
			}
		});
	}

	@Override
	public IMoRPList<IReferenceI> getReferenceI(final IReference meta) {
		return getReferenceI(new Predicate<MoRPObject>() {

			@Override
			public boolean apply(MoRPObject input) {
				return input.getMeta().equals(meta);
			}
		});
	}

	@Override
	public IMoRPList<IReferenceI> getReferenceI(String metaName)
			throws DoesNotExistsException {
		IReference meta = getReference(metaName);
		return getReferenceI(meta);
	}

	@Override
	public IMoRPList<? extends IModelI> getOthers(IReference meta) {
		IMoRPList<IModelI> refModels = new MoRPList<IModelI>();
		for(IReferenceI ref: getReferenceI(meta)){
			refModels.add(ref.getTo());
		}
		return refModels;
	}

	@Override
	public IMoRPList<? extends IModelI> getOthers(String refName)
			throws DoesNotExistsException {
		IReference meta = getReference(refName);
		return getOthers(meta);
	}

	@Override
	public IModelI getOther(IReference meta) {
		IMoRPList<? extends IModelI> refs = getOthers(meta);
		if(!refs.isEmpty()){
			return refs.get(0);
		}
		return null;
	}

	@Override
	public IModelI getOther(String refName) throws DoesNotExistsException {
		IReference meta = getReference(refName);
		return getOther(meta);
	}

}
