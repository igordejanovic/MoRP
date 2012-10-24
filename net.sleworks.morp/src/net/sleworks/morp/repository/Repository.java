package net.sleworks.morp.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.sleworks.morp.IModel;
import net.sleworks.morp.IModelI;
import net.sleworks.morp.IProperty;
import net.sleworks.morp.IReference;
import net.sleworks.morp.IReferenceI;
import net.sleworks.morp.MoRPConstants;
import net.sleworks.morp.exceptions.DoesNotExistsException;
import net.sleworks.morp.impl.Model;
import net.sleworks.morp.impl.ModelI;
import net.sleworks.morp.impl.Property;
import net.sleworks.morp.impl.Reference;
import net.sleworks.morp.impl.ReferenceI;

public class Repository implements IRepository {

	private final IBackend backend;
	
	public Repository(IBackend backend) {
		super();
		this.backend = backend;
	}

	protected IRepositoryObject wrap(IRepositoryObject obj) {
		RepositoryObjectType type = obj.getRepositoryObjectType();
		IRepositoryObject target = obj;
		
		switch(type){
		case MORP_MODEL: 
			target = new Model(obj);
			break;
		case MORP_MODELI:
			target = new ModelI(obj);
			break;
		case MORP_REFERENCEI:
			target = new ReferenceI(obj);
			break;
		case MORP_PROPERTY:
			target = new Property(obj);
			break;
		case MORP_REFERENCE:
			target = new Reference(obj);
			break;
		}
		
		return target;
	}

	@Override
	public IRepositoryObject wrapBackendObject(IBackendObject obj) {
		return wrap(new RepositoryObject(obj, this));
	}
		
	@Override
	public IBackend getBackend() {
		return this.backend;
	}


	// #########################################################################################
	// ###################  Support for the MoRP model
	// #########################################################################################
	
	@Override
	public IModel createModel(String name, String uuid) {
		Model newModel = new Model(createObject(RepositoryObjectType.MORP_MODEL, uuid));
		IModel modelModel = null;
		try {
			modelModel = getModelByUUID(MoRPConstants.UUID_MODEL);
			newModel.setMeta(modelModel);
			newModel.setName(name);
			newModel.setAbstract(false);
			
		} catch (DoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newModel;
	}

	@Override
	public IModel createModel(String name) {
		return createModel(name, UUID.randomUUID().toString());
	}

	@Override
	public IReference createReference(String name, IModel type, String uuid) {
		IModel refModel;
		Reference ref = null;
		try {
			refModel = getModelByUUID(MoRPConstants.UUID_REFERENCE);
			ref =  new Reference(createObject(RepositoryObjectType.MORP_REFERENCE, uuid));
			ref.setMeta(refModel);
			ref.setName(name);
			ref.setContainment(false); // containment is false by default
			ref.setLowerBound(1);  // default cardinality is 1..1
			ref.setUpperBound(1);
			if(type != null){
				ref.setType(type);
			}
			
		} catch (DoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		if(type != null){
//			ref.addReferenceI(getSandbox().getReferenceByUUID(MoRPConstants.UUID_REFERENCE_TYPE), type);
//		}
		
		return ref;
	}

	@Override
	public IReference createReference(String name, IModel type) {
		return createReference(name, type, UUID.randomUUID().toString());
	}

	@Override
	public IProperty createProperty(String name, IModel type, String uuid) {
		IModel propModel;
		Property prop = null;
		try {
			propModel = getModelByUUID(MoRPConstants.UUID_PROPERTY);
			prop =  new Property(createObject(RepositoryObjectType.MORP_PROPERTY, uuid));
			prop.setMeta(propModel);
			prop.setName(name);
			prop.setLowerBound(1);  // default cardinality is 1..1
			prop.setUpperBound(1);
			prop.setType(type);
			
//			prop.addReferenceI(repository.getReferenceByUUID(MoRPConstants.UUID_PROPERTY_TYPE), type);

		} catch (DoesNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}

	@Override
	public IProperty createProperty(String name, IModel type) {
		return createProperty(name, type, UUID.randomUUID().toString());
	}

	@Override
	public IModelI createModelI(IModel meta, String uuid) {
		ModelI m = new ModelI(createObject(RepositoryObjectType.MORP_MODELI, uuid));
		m.setMeta(meta);
		return m;
	}

	@Override
	public IModelI createModelI(IModel meta) {
		return createModelI(meta, UUID.randomUUID().toString());
	}

	@Override
	public IReferenceI createReferenceI(IReference meta, IModelI from,
			IModelI to, String uuid) {
		
		ReferenceI refI = new ReferenceI(createObject(RepositoryObjectType.MORP_REFERENCEI, uuid));
		refI.setMeta(meta);
		refI.setFrom(from);
		refI.setTo(to);
		return refI;
	}

	@Override
	public IReferenceI createReferenceI(IReference meta, IModelI from,
			IModelI to) {
		
		return createReferenceI(meta, from, to, UUID.randomUUID().toString());
		
	}
	
	
	// #########################################################################################
	// ###################  Support for the repository graph model
	// #########################################################################################
	
	@Override
	public Object getProperty(IRepositoryElement element, String name) {
		return this.getBackend().getProperty(element.getBackendObject(), name);
	}

	@Override
	public void setProperty(IRepositoryElement element, String name,
			Object value) {
		this.getBackend().setProperty(element.getBackendObject(), name, value);
	}

	@Override
	public IRepositoryObject createObject(RepositoryObjectType type, String uuid) {
		
		if(uuid==null)
			uuid = UUID.randomUUID().toString();

		return wrapBackendObject(getBackend().createBackendObject(type.name(), uuid));
	}

	@Override
	public IRepositoryLink createLink(RepositoryLinkType type,
			IRepositoryObject from, IRepositoryObject to, String uuid) {

		if(uuid==null)
			uuid = UUID.randomUUID().toString();
		
		IBackendObject b = getBackend().createBackendObject(type.name(), uuid);
		IRepositoryLink link = new RepositoryLink(b, this); 
		getBackend().createBackendFromLink(b, from.getBackendObject());
		getBackend().createBackendToLink(b, to.getBackendObject());
		return link;
	}

	@Override
	public List<? extends IRepositoryObject> getObjects(RepositoryObjectType type) {
		List<IRepositoryObject> objs = new ArrayList<IRepositoryObject>();
		for(IBackendObject backendObj: getBackend().getBackendObjects(type.name())){
			objs.add(wrapBackendObject(backendObj));
		}
		return objs;
	}

	@Override
	public IRepositoryObject getObject(String uuid) throws DoesNotExistsException {
		return wrapBackendObject(getBackend().getBackendObject(uuid));
	}

	@Override
	public IRepositoryLink getLink(String uuid) throws DoesNotExistsException {
		return new RepositoryLink(getBackend().getBackendObject(uuid), this);
	}

	@Override
	public void deleteElement(IRepositoryElement element) throws DoesNotExistsException {
		this.getBackend().deleteBackendObject(element.getUUID());
	}

	@Override
	public List<IRepositoryLink> getLinks(IRepositoryObject obj) {
		List<IRepositoryLink> links = new ArrayList<IRepositoryLink>();
		links.addAll(getLinksIn(obj));
		links.addAll(getLinksOut(obj));
		return links;
	}

	@Override
	public List<IRepositoryLink> getLinks(IRepositoryObject obj,
			RepositoryLinkType type) {
		List<IRepositoryLink> links = new ArrayList<IRepositoryLink>();
		links.addAll(getLinksIn(obj, type));
		links.addAll(getLinksOut(obj, type));
		return links;
	}

	
	
	@Override
	public List<IRepositoryLink> getLinksIn(IRepositoryObject obj) {
		List<? extends IBackendObject> objs = getBackend()
				.getBackendLinkObjectsForToLink(obj.getBackendObject());
		
		List<IRepositoryLink> links = new ArrayList<IRepositoryLink>();
		for(IBackendObject o: objs){
			links.add(new RepositoryLink(o, this));
		}
		return links;
	}

	@Override
	public List<IRepositoryLink> getLinksIn(IRepositoryObject obj,
			RepositoryLinkType type) {
		List<IRepositoryLink> links = new ArrayList<IRepositoryLink>();
		for(IRepositoryLink l: getLinksIn(obj)){
			if(l.getType() == type)
				links.add(l);
		}
		return links;
	}

	@Override
	public List<IRepositoryLink> getLinksOut(IRepositoryObject obj) {
		List<? extends IBackendObject> objs = getBackend()
				.getBackendLinkObjectsForFromLink(obj.getBackendObject());
		
		List<IRepositoryLink> links = new ArrayList<IRepositoryLink>();
		for(IBackendObject o: objs){
			links.add(new RepositoryLink(o, this));
		}
		return links;
	}

	@Override
	public List<IRepositoryLink> getLinksOut(IRepositoryObject obj,
			RepositoryLinkType type) {
		List<IRepositoryLink> links = new ArrayList<IRepositoryLink>();
		for(IRepositoryLink l: getLinksOut(obj)){
			if(l.getType() == type)
				links.add(l);
		}
		return links;
	}

	@Override
	public List<? extends IRepositoryObject> getOthersIn(IRepositoryObject obj) {
		List<IRepositoryObject> objs = new ArrayList<IRepositoryObject>();
		for(IRepositoryLink l: getLinksIn(obj)){
			objs.add(l.getFrom());
		}
		return objs;
	}

	@Override
	public List<? extends IRepositoryObject> getOthersIn(IRepositoryObject obj,
			RepositoryLinkType type) {
		List<IRepositoryObject> objs = new ArrayList<IRepositoryObject>();
		for(IRepositoryLink l: getLinksIn(obj, type)){
			objs.add(l.getFrom());
		}
		return objs;
	}

	@Override
	public List<? extends IRepositoryObject> getOthersOut(
			IRepositoryObject obj) {
		List<IRepositoryObject> objs = new ArrayList<IRepositoryObject>();
		for(IRepositoryLink l: getLinksOut(obj)){
			objs.add(l.getTo());
		}
		return objs;
	}

	@Override
	public List<? extends IRepositoryObject> getOthersOut(
			IRepositoryObject obj, RepositoryLinkType type) {
		List<IRepositoryObject> objs = new ArrayList<IRepositoryObject>();
		for(IRepositoryLink l: getLinksOut(obj, type)){
			objs.add(l.getTo());
		}
		return objs;
	}


	@Override
	public IModel getModelByUUID(String uuid) throws DoesNotExistsException {
		// TODO Provera tipa.... wrapper metoda 
		return new Model(new RepositoryObject(getBackend().getBackendObject(uuid), this));
	}

	@Override
	public IReference getReferenceByUUID(String uuid) throws DoesNotExistsException {
		// TODO Provera tipa.... wrapper metoda 
		return new Reference(new RepositoryObject(getBackend().getBackendObject(uuid), this));
	}


	@Override
	public void txBegin() {
		getBackend().txBegin();
	}

	@Override
	public void txSuccess() {
		getBackend().txSuccess();
	}

	@Override
	public void txEnd() {
		getBackend().txEnd();
	}



	
}
