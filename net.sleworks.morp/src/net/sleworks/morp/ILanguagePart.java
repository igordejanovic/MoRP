/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp;

public interface ILanguagePart extends IModelI {

	/**
	 * Return language part this language part conforms to.
	 * It must always be an abstract syntax of some language.
	 * E.g. if this language part represents one of the concrete
	 * syntaxes than conformsTo language part must be an abstract syntax
	 * of the language for concrete syntax definition.
	 * 
	 * @return language part this language part conforms to
	 */
	public ILanguagePart getConformsTo();
	
	/**
	 * @return top-most model instances of this language part
	 */
	public IMoRPList<IModel> getModels();
	
	/**
	 * @return all model instances contained in this language part
	 */
	public IMoRPList<IModel> getAllModels();
	
}
