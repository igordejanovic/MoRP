package net.sleworks.morp.repository;

import java.util.List;
import java.util.UUID;

import net.sleworks.morp.exceptions.DoesNotExistsException;
import net.sleworks.morp.repository.impl.HashMapRepository;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RepositoryTest {

	IRepository repository;
	String modelI1UUID = "6dd54e3a-b07f-4120-aa5c-8a48c40df52c";
	String modelI2UUID = "aefed320-4065-49d6-8e94-88633a97b219";
	String modelI3UUID = "2bb78262-9e38-4da0-9814-fd272db5f939";
	String referenceIUUID = "62d22fed-6734-4633-a25b-36410b1f4e6f";
	String linkFromUUID = "ebff9dd1-a89d-434e-a601-1a77fa2f23f3";
	String linkToUUID = "97bfc0a7-545f-49e3-b2c9-e69d497737e9";
	
	@Before
	public void setup(){
		this.repository = new HashMapRepository();	
		
		IRepositoryObject modelI1 = this.repository
				.createObject(RepositoryObjectType.MORP_MODELI, modelI1UUID);

		IRepositoryObject modelI2 = this.repository
				.createObject(RepositoryObjectType.MORP_MODELI, modelI2UUID);
		
		// ModeliI that is not connected
		IRepositoryObject modelI3 = this.repository
				.createObject(RepositoryObjectType.MORP_MODELI, modelI3UUID);
		
		IRepositoryObject ref = this.repository
				.createObject(RepositoryObjectType.MORP_REFERENCEI, referenceIUUID);
		
		this.repository.createLink(RepositoryLinkType.MORP_REFERENCEI_FROM, ref, modelI1, linkFromUUID);
		this.repository.createLink(RepositoryLinkType.MORP_REFERENCEI_TO, ref, modelI2, linkToUUID);
	}
	
	@Test			
	public void testCreateRetrieveRepositoryObject() throws DoesNotExistsException{
		String uuid = UUID.randomUUID().toString(); 
		IRepositoryObject obj = this.repository.createObject(RepositoryObjectType.MORP_MODEL, uuid);
		
		assertNotNull(obj);
		assertEquals(uuid, obj.getUUID());
		assertEquals(obj, this.repository.getObject(obj.getUUID()));		
	}	

	@Test			
	public void testGetObjectsByType() {
		List<? extends IRepositoryObject> objects = this.repository.getObjects(RepositoryObjectType.MORP_MODELI);
		assertEquals(3, objects.size());
		objects = this.repository.getObjects(RepositoryObjectType.MORP_REFERENCEI);
		assertEquals(1, objects.size());
	}	

	@Test			
	public void testCreateRetrieveRepositoryLink() throws DoesNotExistsException {
		String uuid = UUID.randomUUID().toString(); 
		IRepositoryObject obj1 = this.repository.createObject(RepositoryObjectType.MORP_MODELI, 
				UUID.randomUUID().toString());
		IRepositoryObject obj2 = this.repository.createObject(RepositoryObjectType.MORP_MODELI, 
				UUID.randomUUID().toString());
		
		IRepositoryLink link = this.repository.createLink(RepositoryLinkType.MORP_META, obj1, obj2, uuid);
		
		assertNotNull(link);
		assertEquals(uuid, link.getUUID());
		assertEquals(link, this.repository.getLink(uuid));		
	}	
	
	
}
