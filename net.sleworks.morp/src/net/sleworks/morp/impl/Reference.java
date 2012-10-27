/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp.impl;

import net.sleworks.morp.IModel;
import net.sleworks.morp.IReference;
import net.sleworks.morp.MoRPConstants;
import net.sleworks.morp.exceptions.DoesNotExistsException;
import net.sleworks.morp.repository.IRepositoryObject;


public class Reference extends ModelI implements IReference {

	public Reference(IRepositoryObject obj) {
		super(obj);
	}

	@Override
	public String getName() {
		return (String) getRepository().getProperty(this, MoRPConstants.NAMED_ELEMENT_NAME);
	}

	@Override
	public IReference setName(String name) {
		getRepository().setProperty(this, MoRPConstants.NAMED_ELEMENT_NAME, name);
		return this;
	}

	@Override
	public int getLowerBound() {
		return (Integer) getRepository().getProperty(this, MoRPConstants.MULTIPLICITY_LOWER_BOUND);
	}

	@Override
	public IReference setLowerBound(int lowerBound) {
		getRepository().setProperty(this, MoRPConstants.MULTIPLICITY_LOWER_BOUND, lowerBound);
		return this;
	}

	@Override
	public int getUpperBound() {
		return (Integer) getRepository().getProperty(this, MoRPConstants.MULTIPLICITY_UPPER_BOUND);
	}

	@Override
	public IReference setUpperBound(int upperBound) {
		getRepository().setProperty(this, MoRPConstants.MULTIPLICITY_UPPER_BOUND, upperBound);
		return this;
	}

	@Override
	public boolean isContainment() {
		return (Boolean) getRepository().getProperty(this, MoRPConstants.REFERENCE_CONTAINMENT);
	}

	@Override
	public IReference setContainment(boolean containment) {
		getRepository().setProperty(this, MoRPConstants.REFERENCE_CONTAINMENT, containment);
		return this;
	}
	
	@Override
	public IModel getType() {
		IReference meta;
		try {
			meta = getRepository().getReferenceByUUID(MoRPConstants.UUID_REFERENCE_TYPE);
			return (IModel) getOther(meta);
		} catch (DoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public IReference setType(IModel model) {
		IReference meta;
		try {
			meta = getRepository().getReferenceByUUID(MoRPConstants.UUID_REFERENCE_TYPE);
			this.addReferenceI(meta, model);
		} catch (DoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public IModel getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IReference getOpposite() {
		IReference meta;
		try {
			meta = getRepository().getReferenceByUUID(MoRPConstants.UUID_REFERENCE_OPPOSITE);
			return (IReference) getOther(meta);		
		} catch (DoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public IReference setOpposite(IReference opposite) {
		IReference meta;
		try {
			meta = getRepository().getReferenceByUUID(MoRPConstants.UUID_REFERENCE_OPPOSITE);
			this.addReferenceI(meta, opposite);
		} catch (DoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}

}
