package net.sleworks.morp.impl;

import net.sleworks.morp.ILanguage;
import net.sleworks.morp.ILanguagePart;
import net.sleworks.morp.IMoRPList;
import net.sleworks.morp.IReference;
import net.sleworks.morp.MoRPConstants;
import net.sleworks.morp.exceptions.DoesNotExistsException;
import net.sleworks.morp.repository.IRepositoryObject;

public class Language extends ModelI implements ILanguage {

	public Language(IRepositoryObject obj) {
		super(obj);
	}

	@Override
	public String getName() {
		return (String) getPropertyI(MoRPConstants.NAMED_ELEMENT_NAME);
	}

	@Override
	public ILanguage setName(String name) {
		setPropertyI(MoRPConstants.NAMED_ELEMENT_NAME, name);
		return this;
	}

	@Override
	public ILanguagePart getAbstractSyntax() {
		ILanguagePart absSyn = null;
		try {
			IReference metaRef = this.getRepository()
					.getReferenceByUUID(MoRPConstants.UUID_LANGUAGE_ABSTRACT_SYNTAX);
			absSyn = (ILanguagePart) getOther(metaRef);
		} catch (DoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return absSyn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IMoRPList<ILanguagePart> getConcreteSyntaxes() {
		IMoRPList<ILanguagePart> conSyn = null;
		try {
			IReference metaRef = this.getRepository()
					.getReferenceByUUID(MoRPConstants.UUID_LANGUAGE_CONCRETE_SYNTAXES);
			conSyn = (IMoRPList<ILanguagePart>) getOthers(metaRef);
		} catch (DoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return conSyn;
	}

}
