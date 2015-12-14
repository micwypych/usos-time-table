package com.github.micwypych.usos_time_table.usos_gateways;

import static java.net.URLEncoder.encode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionType;

import com.google.common.base.Joiner;

public class UsosWebJsonReader<E> {

	//private final TypeToken<E> typeToken;
	private Class<E> subclass = null;

	private String webservice = null;
	private CollectionType collectionType = null;
	private ObjectMapper mapper = new ObjectMapper();

	public UsosWebJsonReader(String webservice,Class<E> clasz) {
		this.webservice = webservice;
		this.subclass = clasz;
	}
	
	public UsosWebJsonReader(String webservice,Class<? extends Collection> collectionClass,Class<?> elementClass) {
		this.webservice = webservice;
		this.collectionType =	mapper.getTypeFactory().constructCollectionType((Class<? extends Collection>) collectionClass, elementClass);
	}
	
	public static class Queries implements Map<String, String>{
		
		private HashMap<String,String> q;
		
		public Queries() {
			q = new HashMap<String, String>();
		}
		

		public static Queries encodeHashMap(HashMap<String,String> map) throws UnsupportedEncodingException {
			Queries q = new Queries();
			for( Entry<String,String> entry :map.entrySet() ) {
				q.put(encode(entry.getKey(),"utf-8"), encode(entry.getValue(),"utf-8"));
			}
			return q;
		}
		
		@Override
		public int size() {
			return q.size();
		}

		@Override
		public boolean isEmpty() {
			return q.isEmpty();
		}

		@Override
		public boolean containsKey(Object key) {
			return q.containsKey(key);
		}

		@Override
		public boolean containsValue(Object value) {
			return q.containsKey(value);
		}

		@Override
		public String get(Object key) {
			return q.get(key);
		}

		@Override
		public String put(String key, String value) {
			return q.put(key, value);
		}

		@Override
		public String remove(Object key) {
			return q.remove(key);
		}

		@Override
		public void putAll(Map<? extends String, ? extends String> m) {
			q.putAll(m);
		}

		@Override
		public void clear() {
			q.clear();
		}

		@Override
		public Set<String> keySet() {
			return q.keySet();
		}

		@Override
		public Collection<String> values() {
			return q.values();
		}

		@Override
		public Set<java.util.Map.Entry<String, String>> entrySet() {
			return q.entrySet();
		}		
	}

	public E readJsonFromService(String service,
			Queries queries) throws IOException {
		return readJsonFromServiceInternal(service, queries);
	}
	
	public E readJsonFromService(String service,
			HashMap<String, String> notEncodedQueries) throws IOException {
		Queries queries = Queries.encodeHashMap(notEncodedQueries);
		return readJsonFromServiceInternal(service, queries);
	}
	
	private E readJsonFromServiceInternal(String service,
			Queries queries) throws IOException {
		String url = formatURL(service, queries);
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			E e = readValue(rd);
			return e;
		} finally {
			is.close();
		}
	}

	@SuppressWarnings("unchecked")
	private E readValue(BufferedReader rd) throws IOException,
			JsonParseException, JsonMappingException {
		if ( subclass != null ) {
			return (E) mapper.readValue(rd, subclass);
		} else if (collectionType != null) {
			return (E) mapper.readValue(rd, collectionType);
		} else {
			throw new IllegalStateException("Exaclty one of subclass or collectionType should be intialized");
		}
	}

	private String formatURL(String service, Queries queries) {
		String queriesStr = Joiner.on("&").withKeyValueSeparator("=")
				.join(queries);
		return webservice + "/" + service + "?" + queriesStr;
	}
}
