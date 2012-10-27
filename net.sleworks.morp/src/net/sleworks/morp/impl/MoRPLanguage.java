/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp.impl;

import net.sleworks.morp.IModel;
import net.sleworks.morp.IProperty;
import net.sleworks.morp.IReference;
import net.sleworks.morp.MoRPConstants;
import net.sleworks.morp.repository.IRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoRPLanguage {
	
	private final static Logger log = LoggerFactory.getLogger(MoRPLanguage.class);
	
	public static void createMoRP(IRepository repository){
		
		log.info("Creating MoRP");

		IModel model = repository.createModel(MoRPConstants.MODEL, MoRPConstants.UUID_MODEL);
		IModel property = repository.createModel(MoRPConstants.PROPERTY, MoRPConstants.UUID_PROPERTY);
		IModel reference = repository.createModel(MoRPConstants.REFERENCE, MoRPConstants.UUID_REFERENCE);
		IModel namedElement = repository.createModel(MoRPConstants.NAMED_ELEMENT, 
				MoRPConstants.UUID_NAMED_ELEMENT).setAbstract(true);
		IModel multiplicity = repository.createModel(MoRPConstants.MULTIPLICITY,
				MoRPConstants.UUID_MULTIPLICITY).setAbstract(true);
		
		// Support for references
		IReference modelReferences = model.createReference(MoRPConstants.MODEL_REFERENCES, MoRPConstants.UUID_MODEL_REFERENCES)
				.setContainment(true)
				.setLowerBound(0)
				.setUpperBound(-1);
		IReference referenceType = reference.createReference(MoRPConstants.REFERENCE_TYPE, MoRPConstants.UUID_REFERENCE_TYPE);
		
		modelReferences.setType(reference);
		referenceType.setType(model);
		
		IReference referenceOpposite =  reference.createReference(MoRPConstants.REFERENCE_OPPOSITE, reference, 
				MoRPConstants.UUID_REFERENCE_OPPOSITE)
				.setLowerBound(0);

		// support for inner models
		IReference modelInnerModels = model.createReference(MoRPConstants.MODEL_INNER_MODELS, model,
				MoRPConstants.UUID_MODEL_INNER_MODELS)
				.setContainment(true)
				.setLowerBound(0)
				.setUpperBound(-1);
		IReference modelOwner = model.createReference(MoRPConstants.MODEL_OWNER, model,
				MoRPConstants.UUID_MODEL_OWNER)
				.setLowerBound(0)
				.setUpperBound(1).setOpposite(modelInnerModels);

		// support for super models
		IReference modelSuperModels = model.createReference(MoRPConstants.MODEL_SUPER_MODELS, model,
				MoRPConstants.UUID_MODEL_SUPER_MODELS)
				.setLowerBound(0)
				.setUpperBound(-1);
		IReference modelInheritedModels = model.createReference(MoRPConstants.MODEL_INHERITED_MODELS, model,
				MoRPConstants.UUID_MODEL_INHERITED_MODELS)
				.setLowerBound(0)
				.setUpperBound(-1).setOpposite(modelSuperModels);
		

		// Support for properties
		IReference modelProperties = model.createReference(MoRPConstants.MODEL_PROPERTIES, property, 
				MoRPConstants.UUID_MODEL_PROPERTIES)
				.setContainment(true)
				.setLowerBound(0)
				.setUpperBound(-1);

		// Inheritance
		model.addSuperModel(namedElement);
		property.addSuperModel(namedElement).addSuperModel(multiplicity);
		reference.addSuperModel(namedElement).addSuperModel(multiplicity);

		// Primitive types
		IModel primitiveTypes = repository.createModel(MoRPConstants.PRIMITIVE_TYPES, MoRPConstants.UUID_PRIMITIVE_TYPES);
		IModel primitiveType = primitiveTypes.createInnerModel(MoRPConstants.PRIMITIVE_TYPES_PRIMITIVE_TYPE,
									MoRPConstants.UUID_PRIMITIVE_TYPES_PRIMITIVE_TYPE).setAbstract(true);
		IModel string = primitiveTypes.createInnerModel(MoRPConstants.PRIMITIVE_TYPES_STRING, 
									MoRPConstants.UUID_PRIMITIVE_TYPES_STRING)
									.addSuperModel(primitiveType);
		IModel integer = primitiveTypes.createInnerModel(MoRPConstants.PRIMITIVE_TYPES_INTEGER,
									MoRPConstants.UUID_PRIMITIVE_TYPES_INTEGER)
									.addSuperModel(primitiveType);
		IModel bool = primitiveTypes.createInnerModel(MoRPConstants.PRIMITIVE_TYPES_BOOLEAN,
									MoRPConstants.UUID_PRIMITIVE_TYPES_BOOLEAN)
									.addSuperModel(primitiveType);

		// Support for property type
		IReference propertyType = property.createReference(MoRPConstants.PROPERTY_TYPE, primitiveType, 
				MoRPConstants.UUID_PROPERTY_TYPE);

		// Properties
		// Reference 
		IProperty referenceContainment = reference.createProperty(MoRPConstants.REFERENCE_CONTAINMENT, bool);
		
		// Model
		IProperty abst = model.createProperty(MoRPConstants.MODEL_ABSTRACT, bool);				
	
		// NamedElement
		IProperty name = namedElement.createProperty(MoRPConstants.NAMED_ELEMENT_NAME, string);

		// Multiplicity
		IProperty lowerBound = multiplicity.createProperty(MoRPConstants.MULTIPLICITY_LOWER_BOUND, integer);
		IProperty upperBound = multiplicity.createProperty(MoRPConstants.MULTIPLICITY_UPPER_BOUND, integer);
		
		log.info("MoRP created");
	}

}
