package net.sleworks.morp.impl;

import net.sleworks.morp.ILanguagePart;
import net.sleworks.morp.IMoRPList;
import net.sleworks.morp.IModel;
import net.sleworks.morp.repository.IRepositoryObject;

public class LanguagePart extends ModelI implements ILanguagePart {

	public LanguagePart(IRepositoryObject obj) {
		super(obj);
	}
	
	@Override
	public ILanguagePart getConformsTo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMoRPList<IModel> getModels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMoRPList<IModel> getAllModels() {
		// TODO Auto-generated method stub
		return null;
	}

}
