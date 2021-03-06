/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp.impl;

import net.sleworks.morp.IMoRPObject;
import net.sleworks.morp.repository.IRepositoryObject;
import net.sleworks.morp.repository.RepositoryLinkType;
import net.sleworks.morp.repository.impl.RepositoryObject;


public class MoRPObject extends RepositoryObject implements IMoRPObject {

	public MoRPObject(IRepositoryObject obj) {
		super(obj.getRepository(), obj.getBackendObject());
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
		return String.format("MoRP[%s]", getUUID());
	}

	/* For now ordering is done comparing UUIDs
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(IMoRPObject o) {
		return this.getUUID().compareTo(o.getUUID());
	}

}
