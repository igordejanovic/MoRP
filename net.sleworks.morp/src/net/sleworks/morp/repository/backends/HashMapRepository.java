package net.sleworks.morp.repository.backends;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sleworks.morp.exceptions.DoesNotExistsException;
import net.sleworks.morp.repository.IBackendObject;
import net.sleworks.morp.repository.IRepositoryElement;
import net.sleworks.morp.repository.IRepositoryLink;
import net.sleworks.morp.repository.IRepositoryObject;
import net.sleworks.morp.repository.RepositoryLinkType;
import net.sleworks.morp.repository.RepositoryObjectType;
import net.sleworks.morp.repository.impl.AbstractRepository;
import net.sleworks.morp.repository.impl.RepositoryLink;
import net.sleworks.morp.repository.impl.RepositoryObject;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

public class HashMapRepository extends AbstractRepository {

	private HashMap<String, IRepositoryObject> objects = new HashMap<>();
	private HashMap<String, IRepositoryLink> links = new HashMap<>();
	
	class HashMapBackendObject implements IBackendObject {
		private HashMap<String, Object> properties = new HashMap<>();
		
		Object getProperty(String name){
			return properties.get(name);
		}
		
		void setProperty(String name, Object value){
			properties.put(name, value);
		}
		
	}
	
	class HashMapLinkBackendObject extends HashMapBackendObject {
		private IRepositoryObject from;
		private IRepositoryObject to;
		
		public HashMapLinkBackendObject(IRepositoryObject from, IRepositoryObject to) {
			this.from = from;
			this.to = to;
		}
		
		public IRepositoryObject getFrom() {
			return from;
		}
		public IRepositoryObject getTo() {
			return to;
		}
		
	}
	
	private HashMapBackendObject getBO(IRepositoryElement element) {
		return (HashMapBackendObject)element.getBackendObject();
	}
	
	@Override
	public IRepositoryObject getObjectByUUID(String uuid) {
		return objects.get(uuid);
	}

	@Override
	public Object getProperty(IRepositoryElement element, String name) {
		return getBO(element).getProperty(name);
	}

	@Override
	public void setProperty(IRepositoryElement element, String name, Object value) {
		getBO(element).setProperty(name, value);
	}
	
	protected void setType(IRepositoryElement e, String type){
		this.setProperty(e, IRepositoryElement.REPOSITORY_ELEMENT_PROPERTY_TYPE_NAME, type);		
	}

	protected void setUUID(IRepositoryElement e, String uuid){
		this.setProperty(e, IRepositoryElement.REPOSITORY_ELEMENT_PROPERTY_UUID_NAME, uuid);		
	}

	@Override
	public IRepositoryObject createObject(RepositoryObjectType type, String uuid) {
		IRepositoryObject obj = new RepositoryObject(this, new HashMapBackendObject());
		this.setType(obj, type.name());
		if(uuid==null){
			uuid = UUID.randomUUID().toString();
		}
		this.setUUID(obj, uuid);
		objects.put(uuid, obj);
		return obj;
	}

	@Override
	public List<? extends IRepositoryObject> getObjects(final RepositoryObjectType type) {
		if(type.equals(RepositoryObjectType.ALL)){
			return ImmutableList.copyOf(objects.values());
		}else{
			List<IRepositoryObject> objects = 
					Lists.newArrayList(Iterators.filter(
						this.objects.values().iterator(), 
						new Predicate<IRepositoryObject>() {
							@Override
							public boolean apply(IRepositoryObject obj){
								String typeName = (String) HashMapRepository.this
										.getProperty(obj, IRepositoryObject.REPOSITORY_ELEMENT_PROPERTY_TYPE_NAME);
								return type.name().equals(typeName);
							}
					}));
			return objects;
		}
	}

	@Override
	public IRepositoryObject getObject(String uuid)
			throws DoesNotExistsException {
		return objects.get(uuid);
	}

	@Override
	public IRepositoryLink createLink(RepositoryLinkType type,
			IRepositoryObject from, IRepositoryObject to, String uuid) {
		IRepositoryLink link = new RepositoryLink(this, new HashMapLinkBackendObject(from, to));
		this.setType(link, type.name());
		if(uuid==null){
			uuid = UUID.randomUUID().toString();
		}
		this.setUUID(link, uuid);
		links.put(uuid, link);
		return link;
	}

	@Override
	public IRepositoryLink getLink(String uuid) throws DoesNotExistsException {
		return links.get(uuid);
	}
		
	@Override
	public List<? extends IRepositoryObject> getOthersIn(IRepositoryObject obj,
			RepositoryLinkType type) {
		List<IRepositoryObject> others = Lists.newArrayList();
		for(IRepositoryLink link: links.values()){
			if((RepositoryLinkType.ALL.equals(type) || link.getType().equals(type)) 
					&& link.getTo().equals(obj)){
				others.add(link.getFrom());
			}
		}
		return others;
	}

	@Override
	public List<? extends IRepositoryObject> getOthersOut(
			IRepositoryObject obj, RepositoryLinkType type) {
		List<IRepositoryObject> others = Lists.newArrayList();
		for(IRepositoryLink link: links.values()){
			if((RepositoryLinkType.ALL.equals(type) || link.getType().equals(type)) 
					&& link.getFrom().equals(obj)){
				others.add(link.getTo());
			}
		}
		return others;
	}

	@Override
	public IRepositoryObject getFromSideForLink(IRepositoryLink link) {
		return ((HashMapLinkBackendObject)link.getBackendObject()).getFrom();
	}

	@Override
	public IRepositoryObject getToSideForLink(IRepositoryLink link) {
		return ((HashMapLinkBackendObject)link.getBackendObject()).getTo();
	}
	

	@Override
	public List<IRepositoryLink> getLinksIn(IRepositoryObject obj,
			RepositoryLinkType type) {
		List<IRepositoryLink> linksIn = Lists.newArrayList();
		for(IRepositoryLink link: links.values()){
			if((RepositoryLinkType.ALL.equals(type) || link.getType().equals(type)) 
					&& link.getTo().equals(obj)){
				linksIn.add(link);
			}
		}
		return linksIn;
	}

	@Override
	public List<IRepositoryLink> getLinksOut(IRepositoryObject obj,
			RepositoryLinkType type) {
		List<IRepositoryLink> linksOut = Lists.newArrayList();
		for(IRepositoryLink link: links.values()){
			if((RepositoryLinkType.ALL.equals(type) || link.getType().equals(type)) 
					&& link.getFrom().equals(obj)){
				linksOut.add(link);
			}
		}
		return linksOut;
	}

	@Override
	public void deleteElement(IRepositoryElement element)
			throws DoesNotExistsException {
		// Remove all links that point to this element if it is object
		if(element instanceof IRepositoryObject){
			IRepositoryObject obj = (IRepositoryObject) element;
			
			for(Iterator<Map.Entry<String, IRepositoryLink>> 
					it = links.entrySet().iterator(); it.hasNext();){
				
				IRepositoryLink link = it.next().getValue();
				if(obj.equals(link.getFrom()) 
						|| obj.equals(link.getTo())){
					it.remove();
				}
				
			}
			objects.remove(element.getUUID());
		} else {
			links.remove(element.getUUID());
		}
	}

	@Override
	public void txBegin() {
		// Not implemented
	}

	@Override
	public void txSuccess() {
		// Not implemented
	}

	@Override
	public void txEnd() {
		// Not implemented
	}

}
