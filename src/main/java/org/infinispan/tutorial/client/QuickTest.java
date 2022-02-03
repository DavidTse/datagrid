package org.infinispan.tutorial.client;

import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.annotation.ClientCacheEntryCreated;
import org.infinispan.client.hotrod.annotation.ClientCacheEntryModified;
import org.infinispan.client.hotrod.annotation.ClientListener;
//import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.event.ClientCacheEntryCreatedEvent;
import org.infinispan.client.hotrod.event.ClientCacheEntryModifiedEvent;

public class QuickTest 
{
	public static void main(String[] args) 
	{
		try {
			/* Multiple cluster
			Properties prop = new Properties();
			ConfigurationBuilder builder = new ConfigurationBuilder();
			builder.withProperties(prop);
			*/
			System.out.println("Start");
			RemoteCacheManager remoteCacheManager = new RemoteCacheManager();
			String cacheName = "async-cache";
			RemoteCache<String, Double> remoteCache = remoteCacheManager.getCache(cacheName);
			//
			Set<String> names = remoteCacheManager.getCacheNames();
			Iterator<String> it = names.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
			//
			for (int j=1; j<11; j++) {  
				String key = Integer.toString(j);
				Double value = remoteCache.get(key);
				System.out.printf(">> key=%s Value=%s%n", key, value);
			}

			remoteCacheManager.close();
			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
