/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sleworks.morp.exceptions.DoesNotExistsException;
import net.sleworks.morp.repository.IBackend;
import net.sleworks.morp.repository.IBackendObject;
import net.sleworks.morp.repository.NotValidBackendObject;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * In-memory repository backend based on HashMap implementation.
 */
public class HashMapBackend implements IBackend {
	
	private static final String PROPNAME_TYPE = "__type";
	private static final String PROPNAME_UUID = "__uuid";
	
	class BackendObject implements IBackendObject {
		private Map<String, Object> properties = new HashMap<>();
		public Object getProperty(String name){
			return properties.get(name);
		}
		public void setProperty(String name, Object value){
			properties.put(name, value);
		}
	}
	
	class BackendLink extends BackendObject {
		private BackendObject from;
		private BackendObject to;
		public BackendObject getFrom() {
			return from;
		}
		public void setFrom(BackendObject from) {
			this.from = from;
		}
		public BackendObject getTo() {
			return to;
		}
		public void setTo(BackendObject to) {
			this.to = to;
		}
	}
	
	/**
	 * UUID -> backend object map.
	 */
	private Map<String, BackendObject> objects = new HashMap<>();

	@Override
	public Object getProperty(IBackendObject obj, String name) {
		return ((BackendObject)obj).getProperty(name);
	}

	@Override
	public void setProperty(IBackendObject obj, String name, Object value) {
		((BackendObject)obj).setProperty(name, value);
	}

	@Override
	public IBackendObject createBackendObject(String type, String uuid) {
		BackendObject obj =  new BackendObject();
		obj.setProperty(PROPNAME_TYPE, type);
		obj.setProperty(PROPNAME_UUID, uuid);
		objects.put(uuid, obj);
		return obj;
	}

	private List<BackendObject> getBackendObjects(Predicate<BackendObject> p){
		return Lists.newArrayList(
				Iterables.filter(this.objects.values(), p));
	}	
	
	@Override
	public List<? extends IBackendObject> getBackendObjects(final String type) {
		return getBackendObjects(new Predicate<HashMapBackend.BackendObject>() {
			public boolean apply(BackendObject obj){
				return obj.getProperty(PROPNAME_TYPE).equals(type);
			}
		});
	}

	@Override
	public IBackendObject getBackendObject(String uuid) throws DoesNotExistsException {
		return objects.get(uuid);
	}

	@Override
	public void deleteBackendObject(String uuid) throws DoesNotExistsException {
		objects.remove(uuid);
	}

	@Override
	public void createBackendFromLink(IBackendObject linkObj, IBackendObject obj) {
		((BackendLink)linkObj).setFrom((BackendObject)obj);
	}

	@Override
	public void createBackendToLink(IBackendObject linkObj, IBackendObject obj) {
		((BackendLink)linkObj).setTo((BackendObject)obj);
	}

	@Override
	public List<? extends IBackendObject> getBackendLinkObjectsForFromLink(final IBackendObject fromLinkObj) {
		return getBackendObjects(new Predicate<HashMapBackend.BackendObject>() {
			public boolean apply(BackendObject obj){
				return (obj instanceof BackendLink) && 
						(fromLinkObj.equals(((BackendLink)obj).getFrom()));
			}
		});
	}

	@Override
	public List<? extends IBackendObject> getBackendLinkObjectsForToLink(final IBackendObject toLinkObj) {
		return getBackendObjects(new Predicate<HashMapBackend.BackendObject>() {
			public boolean apply(BackendObject obj){
				return (obj instanceof BackendLink) && 
						(toLinkObj.equals(((BackendLink)obj).getFrom()));
			}
		});
	}

	@Override
	public IBackendObject getBackendObjectForFromLink(IBackendObject linkObj) {
		if(linkObj instanceof BackendLink){
			return ((BackendLink)linkObj).getFrom();
		}
		throw new NotValidBackendObject();
	}

	@Override
	public IBackendObject getBackendObjectForToLink(IBackendObject linkObj) {
		if(linkObj instanceof BackendLink){
			return ((BackendLink)linkObj).getTo();
		}
		throw new NotValidBackendObject();
	}

	@Override
	public void deleteBackendLink(IBackendObject from, IBackendObject to) {
		// TODO Auto-generated method stub

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
