/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp;

import net.sleworks.morp.exceptions.DoesNotExistsException;


public interface IModel extends IModelI, INamedElement<IModel> {

	/**
	 * @return
	 */
	public boolean getAbstract();
	
	/**
	 * @param abs
	 * @return This model 
	 */
	public IModel setAbstract(boolean abs);
	
	/**
	 * @param name
	 * @return
	 */
	public IProperty createProperty(String name, IModel type);

	/**
	 * @return The list of properties contained by this model including inherited properties.
	 */
	public IMoRPList<IProperty> getProperties();

	/**
	 * @return The list of properties contained by this model without inherited properties.
	 */
	public IMoRPList<IProperty> getOwnedProperties();

	/**
	 * @param name
	 * @return
	 */
	public IProperty getProperty(String name) throws DoesNotExistsException;

	/**
	 * @param name
	 * @param type
	 * @return
	 */
	public IReference createReference(String name, IModel type);

	/**
	 * @param name
	 * @param uuid
	 * @return
	 */
	public IReference createReference(String name, String uuid);
	
	/**
	 * @param name
	 * @param type
	 * @param uuid
	 * @return
	 */
	public IReference createReference(String name, IModel type, String uuid);
	
	/**
	 * @param name
	 * @return
	 */
	public IReference createReference(String name);

	
	/**
	 * @return The list of references contained by this model including inherited references.
	 */
	public IMoRPList<IReference> getReferences();
	
	/**
	 * @return The list of references contained by this model without inherited references.
	 */
	public IMoRPList<IReference> getOwnedReferences();
				
	/**
	 * @param name
	 * @return
	 */
	public IReference getReference(String name) throws DoesNotExistsException;
	
	/**
	 * Adds super model to this model.
	 * @param superModel
	 * @return
	 */
	public IModel addSuperModel(IModel superModel);
	
	/**
	 * @return Models this model directly inherits from.
	 */
	public IMoRPList<IModel> getSuperModels();

	/**
	 * @return All models this model inherits from. Directly or indirectly.
	 */
	public IMoRPList<IModel> getAllSuperModels();

	/**
	 * @return return all models that directly inherits from this model.
	 */
	public IMoRPList<IModel> getInheritedModels();
	
	/**
	 * @return return all models that directly or indirectly inherits from this model.
	 */
	public IMoRPList<IModel> getAllInheritedModels();
	
	/**
	 * @return Directly contained inner models.
	 */
	public IMoRPList<IModel> getInnerModels();

	/**
	 * @return All inner models directly or indirectly contained.
	 */
	public IMoRPList<IModel> getAllInnerModels();
	
	/**
	 * @param name
	 * @return Creates new inner model with given name.
	 */
	public IModel createInnerModel(String name);

	/**
	 * @param name
	 * @param uuid
	 * @return Creates new inner model with given name and UUID.
	 */
	public IModel createInnerModel(String name, String uuid);
		
	/**
	 * @return The owner of this model. If null then this model is a top-level model.
	 */
	public IModel getOwner();

	
}
