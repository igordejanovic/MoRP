package net.sleworks.morp.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sleworks.morp.IMoRPList;
import net.sleworks.morp.IModel;
import net.sleworks.morp.IReference;
import net.sleworks.morp.MoRPConstants;
import net.sleworks.morp.exceptions.DoesNotExistsException;
import net.sleworks.morp.impl.Reference;
import net.sleworks.morp.repository.impl.HashMapRepository;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import static org.junit.Assert.*;

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
		
	}	
	
	
}
