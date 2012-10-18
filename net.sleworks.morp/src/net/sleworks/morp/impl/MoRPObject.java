/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp.impl;

import net.sleworks.morp.IMoRPObject;
import net.sleworks.morp.repository.IRepositoryObject;
import net.sleworks.morp.repository.RepositoryLinkType;
import net.sleworks.morp.repository.RepositoryObject;


public class MoRPObject extends RepositoryObject implements IMoRPObject {

	public MoRPObject(IRepositoryObject obj) {
		super(obj.getBackendObject(), obj.getRepository());
	}

	@Override
	public IMoRPObject getMeta() {
		return (IMoRPObject) this.getRepository().getOthersOut(this, RepositoryLinkType.MORP_META).get(0);
	}
	
	public void setMeta(IMoRPObject meta){
		getRepository().createLink(RepositoryLinkType.MORP_META, this, meta, null);
	}

	@Override
	public String getRepr() {
		// TODO Auto-generated method stub
		return null;
	}

}
