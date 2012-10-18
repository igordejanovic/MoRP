/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp;


public interface MoRPConstants {
	
	static final String MORP_LANGUAGE = "MoRP";
	
	// UUIDs
	static final String UUID_MORP = "2cc517d2-b565-4c27-bd8c-08479b1bc664";

	static final String UUID_NAMED_ELEMENT = "bcd43540-abef-46fe-9cf8-0cdba430016e";

	static final String UUID_MULTIPLICITY = "19b94262-8861-4205-aaf1-4075f2efd540";
	
	static final String UUID_MODEL = "e5597195-2abd-46d6-ab09-399155a75f3c";
	static final String UUID_MODEL_PROPERTIES = "4a072152-ec7b-47cf-ada8-8f51dadc10bd";
	static final String UUID_MODEL_REFERENCES = "a9a1adf3-fbc5-4ba5-a0d7-48b68221bf4a";
	static final String UUID_MODEL_SUPER_MODELS = "38558777-7ecf-4995-a3bb-8086f323c6f7";
	static final String UUID_MODEL_INHERITED_MODELS = "3bbc04c8-a8f0-41fb-a54a-b5afad80553e";
	static final String UUID_MODEL_INNER_MODELS = "02b3be2c-1172-470b-9a1e-61b408eac984";
	static final String UUID_MODEL_OWNER = "54c83c86-edde-45c2-ab7a-4a51cdaabdf0";
	
	static final String UUID_REFERENCE = "bcb26183-2446-4094-a670-2511348f8105";
	static final String UUID_REFERENCE_TYPE = "62025a66-33da-4d10-b6dd-e59ce1bd969a";
	static final String UUID_REFERENCE_OPPOSITE = "79787f30-ed4d-496c-886d-9f95df66f13c";

	static final String UUID_PROPERTY = "425743ab-fcd3-426c-9395-b45f4bed18ec";
	static final String UUID_PROPERTY_TYPE = "7e873531-591a-441e-a45e-efb87f7510d4";

	static final String UUID_PRIMITIVE_TYPES = "5b7f0ca6-956c-4e03-acc1-b7f41da83c16";
	static final String UUID_PRIMITIVE_TYPES_STRING = "cad52269-47f7-4e74-b743-9e92484786ec";
	static final String UUID_PRIMITIVE_TYPES_INTEGER = "f2930f28-9c1b-4c81-b816-22a8f37a975a";
	static final String UUID_PRIMITIVE_TYPES_BOOLEAN = "e021c466-5c04-4550-8094-f095b4779b3b";

	
	// NAMES
	// Models
	static final String MODEL = "Model";
	static final String REFERENCE = "Reference";
	static final String PROPERTY = "Property";
	static final String NAMED_ELEMENT = "NamedElement";
	static final String MULTIPLICITY = "Multiplicity";

	static final String PRIMITIVE_TYPES = "PrimitiveTypes";
	static final String PRIMITIVE_TYPES_PRIMITIVE_TYPE = "PrimitiveType";
	static final String PRIMITIVE_TYPES_STRING = "String";
	static final String PRIMITIVE_TYPES_INTEGER = "Integer";
	static final String PRIMITIVE_TYPES_BOOLEAN = "Boolean";
	
	// Properties
	static final String MODEL_ABSTRACT = "abstract";

	static final String NAMED_ELEMENT_NAME = "name";

	static final String MULTIPLICITY_LOWER_BOUND = "lowerBound";
	static final String MULTIPLICITY_UPPER_BOUND = "upperBound";
	
	static final String REFERENCE_CONTAINMENT = "containment";

	//References
	static final String MODEL_PROPERTIES = "properties";
	static final String MODEL_REFERENCES = "references";
	static final String MODEL_SUPER_MODELS = "superModels";
	static final String MODEL_INNER_MODELS = "innerModels";
	static final String MODEL_OWNER = "owner";
	static final String MODEL_INHERITED_MODELS = "inheritedModels";
	
	static final String PROPERTY_TYPE = "type";
	
	static final String REFERENCE_TYPE = "type";
	static final String REFERENCE_OPPOSITE = "opposite";
	
}
