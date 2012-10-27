/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp.impl;

import net.sleworks.morp.IMoRPObject;
import net.sleworks.morp.IModelI;
import net.sleworks.morp.IReferenceI;
import net.sleworks.morp.MoRPConstants;
import net.sleworks.morp.repository.IRepositoryObject;
import net.sleworks.morp.repository.RepositoryLinkType;


public class ReferenceI extends MoRPObject implements IReferenceI {

	public ReferenceI(IRepositoryObject obj) {
		super(obj);
	}

	private IModelI getMoRPObject(IMoRPObject obj){
		if(MoRPConstants.UUID_MODEL.equals(obj.getMeta().getUUID())){
			return new Model(obj);
		}else if(MoRPConstants.UUID_PROPERTY.equals(obj.getMeta().getUUID())){
			return new Property(obj); 
		}else if(MoRPConstants.UUID_REFERENCE.equals(obj.getMeta().getUUID())){
			return new Reference(obj); 			
		}else{
			return new ModelI(obj);
		}		
	}

	
	@Override
	public IModelI getFrom() {
		return getMoRPObject((IMoRPObject) this.getRepository()
				.getOthersOut(this, RepositoryLinkType.MORP_REFERENCEI_FROM).get(0));
	}
	
	public void setFrom(IModelI from){
		this.getRepository().createLink(RepositoryLinkType.MORP_REFERENCEI_FROM, this, from, null);
	}

	@Override
	public IModelI getTo() {
		return getMoRPObject((IMoRPObject) this.getRepository().getOthersOut(this, RepositoryLinkType.MORP_REFERENCEI_TO).get(0));
	}
	
	public void setTo(IModelI to){
		this.getRepository().createLink(RepositoryLinkType.MORP_REFERENCEI_TO, this, to, null);
	}

}
