package net.sleworks.morp.repository;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import net.sleworks.morp.IMoRPList;
import net.sleworks.morp.IModel;
import net.sleworks.morp.IReference;
import net.sleworks.morp.MoRPConstants;
import net.sleworks.morp.exceptions.DoesNotExistsException;
import net.sleworks.morp.repository.impl.HashMapRepository;

import org.junit.Before;
import org.junit.Test;

public class MoRPTest {

	IRepository repository;

	@Before
	public void setup(){
		this.repository = new HashMapRepository();			
		MoRPLanguage.createMoRP(this.repository);
	}

	@Test			
	public void testMoRPElementsCount() throws DoesNotExistsException {
		List<? extends IRepositoryObject> objects = this.repository.getObjects(RepositoryObjectType.ALL);
		assertEquals(66, objects.size());
		IRepositoryObject model = this.repository.getModelByUUID(MoRPConstants.UUID_MODEL);
		List<? extends IRepositoryLink> links = this.repository.getLinks(model, RepositoryLinkType.ALL);
		assertEquals(24, links.size());
	}	
	
	@Test			
	public void testModelModel() throws DoesNotExistsException {
		IModel model = this.repository.getModelByUUID(MoRPConstants.UUID_MODEL);
		assertNotNull(model);
		
		assertEquals(model, model.getMeta());
		
		assertEquals(false, model.getAbstract());
		assertEquals(1, model.getOwnedProperties().size());
		assertEquals(MoRPConstants.MODEL_ABSTRACT, model.getOwnedProperties().get(0).getName());
		
		assertEquals(1, model.getSuperModels().size());
		assertEquals(MoRPConstants.NAMED_ELEMENT, model.getSuperModels().get(0).getName());
		
		// Inner models
		assertTrue(model.getInnerModels().isEmpty());
		
	}	

	
	@Test			
	public void testReferenceModel() throws DoesNotExistsException {
		IModel model = this.repository.getModelByUUID(MoRPConstants.UUID_MODEL);
		IModel reference = this.repository.getModelByUUID(MoRPConstants.UUID_REFERENCE);
		assertNotNull(model);
		
		assertEquals(model, reference.getMeta());
		
		assertEquals(false, reference.getAbstract());
		assertEquals(1, reference.getOwnedProperties().size());
		assertEquals(2, reference.getOwnedReferences().size());

		// References
		IReference opposite = this.repository.getReferenceByUUID(MoRPConstants.UUID_REFERENCE_OPPOSITE);
		IReference type = this.repository.getReferenceByUUID(MoRPConstants.UUID_REFERENCE_TYPE);
		
		assertNotNull(opposite);
		assertNotNull(type);

		IMoRPList<IReference> ownedRefs = reference.getOwnedReferences();

		// Sorting references list for comparison to be stable.
		Collections.sort(ownedRefs);
		assertArrayEquals(new IReference[]{type, opposite}, ownedRefs.toArray());
		
		assertEquals(MoRPConstants.REFERENCE_CONTAINMENT, reference.getOwnedProperties().get(0).getName());
		
		assertEquals(reference, opposite.getType());
		assertEquals(0, opposite.getLowerBound());
		assertEquals(1, opposite.getUpperBound());
		assertNull(opposite.getOpposite());
		
		assertEquals(model, type.getType());
		assertEquals(1, type.getLowerBound());
		assertEquals(1, type.getUpperBound());
		assertNull(type.getOpposite());

		// Super models
		IMoRPList<IModel> superModels = reference.getSuperModels();
		Collections.sort(superModels);
		IModel namedElement = this.repository.getModelByUUID(MoRPConstants.UUID_NAMED_ELEMENT);
		IModel multiplicity = this.repository.getModelByUUID(MoRPConstants.UUID_MULTIPLICITY);
		assertArrayEquals(new IModel[]{multiplicity, namedElement}, superModels.toArray());
		assertEquals(2, superModels.size());		
		
		// Inner models
		assertTrue(reference.getInnerModels().isEmpty());
		
	}	

	
	@Test			
	public void testPropertyModel() throws DoesNotExistsException {
		IModel model = this.repository.getModelByUUID(MoRPConstants.UUID_MODEL);
		IModel primType = this.repository.getModelByUUID(MoRPConstants.UUID_PRIMITIVE_TYPES_PRIMITIVE_TYPE);
		IModel property = this.repository.getModelByUUID(MoRPConstants.UUID_PROPERTY);
		assertNotNull(model);
		
		assertEquals(model, property.getMeta());
		
		assertEquals(false, property.getAbstract());
		assertEquals(0, property.getOwnedProperties().size());
		assertEquals(1, property.getOwnedReferences().size());
		
		IReference type = this.repository.getReferenceByUUID(MoRPConstants.UUID_PROPERTY_TYPE);
		assertNotNull(type);
		
		IMoRPList<IReference> ownedRefs = property.getOwnedReferences();

		assertEquals(1, ownedRefs.size());
		assertEquals(type, ownedRefs.get(0));

		assertEquals(primType, type.getType());
		assertEquals(1, type.getLowerBound());
		assertEquals(1, type.getUpperBound());
		assertNull(type.getOpposite());

		// Super models
		IMoRPList<IModel> superModels = property.getSuperModels();
		Collections.sort(superModels);
		IModel namedElement = this.repository.getModelByUUID(MoRPConstants.UUID_NAMED_ELEMENT);
		IModel multiplicity = this.repository.getModelByUUID(MoRPConstants.UUID_MULTIPLICITY);
		assertArrayEquals(new IModel[]{multiplicity, namedElement}, superModels.toArray());
		assertEquals(2, superModels.size());		
				
		// Inner models
		assertTrue(property.getInnerModels().isEmpty());

	}	

	@Test			
	public void testPrimitiveTypes() throws DoesNotExistsException {
		IModel model = this.repository.getModelByUUID(MoRPConstants.UUID_MODEL);
		IModel primTypes = this.repository.getModelByUUID(MoRPConstants.UUID_PRIMITIVE_TYPES);
		IModel primType = this.repository.getModelByUUID(MoRPConstants.UUID_PRIMITIVE_TYPES_PRIMITIVE_TYPE);
		IModel stringType = this.repository.getModelByUUID(MoRPConstants.UUID_PRIMITIVE_TYPES_STRING);
		IModel booleanType = this.repository.getModelByUUID(MoRPConstants.UUID_PRIMITIVE_TYPES_BOOLEAN);
		IModel integerType = this.repository.getModelByUUID(MoRPConstants.UUID_PRIMITIVE_TYPES_INTEGER);
	
		assertNotNull(primTypes);
		assertNotNull(primType);
		assertNotNull(stringType);
		assertNotNull(booleanType);
		assertNotNull(integerType);
		
		assertEquals(model, primTypes.getMeta());
		assertEquals(model, primType.getMeta());
		assertEquals(model, stringType.getMeta());
		assertEquals(model, booleanType.getMeta());
		assertEquals(model, integerType.getMeta());
		
		assertEquals(false, primTypes.getAbstract());
		assertEquals(true, primType.getAbstract());
		assertEquals(false, stringType.getAbstract());
		assertEquals(false, booleanType.getAbstract());
		assertEquals(false, integerType.getAbstract());

		assertEquals(primType, stringType.getSuperModels().get(0));
		assertEquals(primType, booleanType.getSuperModels().get(0));
		assertEquals(primType, integerType.getSuperModels().get(0));

		IMoRPList<IModel> types = primTypes.getInnerModels();
		Collections.sort(types);
		assertArrayEquals(new IModel[]{stringType, primType, booleanType, integerType}, types.toArray());
		
	}	

}
