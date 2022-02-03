package org.infinispan.tutorial.client;

import java.util.concurrent.CompletableFuture;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.annotation.ClientCacheEntryCreated;
import org.infinispan.client.hotrod.annotation.ClientCacheEntryModified;
import org.infinispan.client.hotrod.annotation.ClientListener;
import org.infinispan.client.hotrod.event.ClientCacheEntryCreatedEvent;
import org.infinispan.client.hotrod.event.ClientCacheEntryModifiedEvent;

@ClientListener
public class DataChangeMonitor 
{
	private RemoteCache<String, Double> remoteCache;
	
	public DataChangeMonitor() {}
	
	@ClientCacheEntryCreated
	public void entryCreated(ClientCacheEntryCreatedEvent<String> event) {
		String key = event.getKey();
		remoteCache.getAsync(key).whenComplete((value, ex) ->
        System.out.printf(">> Created: key=%s Value=%s%n", key, value));
	}

	@ClientCacheEntryModified
	public void entryModified(ClientCacheEntryModifiedEvent<String> event) {
		String key = event.getKey();
		remoteCache.getAsync(key).whenComplete((value, ex) ->
        System.out.printf(">> Modified: key=%s Value=%s%n", key, value));
	}

	public RemoteCache<String, Double> getRemoteCache() {
		return remoteCache;
	}

	public void setRemoteCache(RemoteCache<String, Double> remoteCache) {
		this.remoteCache = remoteCache;
	}
}
