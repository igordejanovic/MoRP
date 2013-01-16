/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp;

public interface ILanguage extends IModelI, INamedElement<ILanguage> {

	/**
	 * Returns abstract syntax of the language. 
	 * Abstract syntax must conform to the abstract syntax of the language
	 * for abstract syntax definition (e.g. MoRP).
	 * @return abstract syntax of the language
	 */
	public ILanguagePart getAbstractSyntax();
	
	/**
	 * Returns list of concrete syntax language parts.
	 * This language parts must conform to the abstract syntax of the
	 * language for concrete syntax definition.
	 * @return list of concrete syntax language parts.
	 */
	public IMoRPList<ILanguagePart> getConcreteSyntaxes();
	
}
