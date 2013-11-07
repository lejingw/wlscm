package com.jatools.oscache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.oscache.base.events.CacheEntryEvent;
import com.opensymphony.oscache.plugins.clustersupport.ClusterNotification;
import com.opensymphony.oscache.plugins.clustersupport.JavaGroupsBroadcastingListener;
/**
 * 缓存组播事件侦听器实现类
 *
 */
public class JavaGroupsBroadcastingListenerImpl extends
		JavaGroupsBroadcastingListener {
	    
    private final static Log log = LogFactory.getLog(JavaGroupsBroadcastingListenerImpl.class);
	public void handleClusterNotification(ClusterNotification message) {

		switch (message.getType()) {
		case CacheConstants.CLUSTER_ENTRY_ADD:
			log.debug("集群新增:" + message.getData());
			if (message.getData() instanceof QflagCacheEvent) {
				QflagCacheEvent event = (QflagCacheEvent) message.getData();
				cache.putInCache(event.getKey(), event.getEntry().getContent(),
						null, null, CLUSTER_ORIGIN);
			}
			break;
			
		case CacheConstants.CLUSTER_ENTRY_UPDATE:
		    log.debug("集群更新:" + message.getData());
			if (message.getData() instanceof QflagCacheEvent) {
				QflagCacheEvent event = (QflagCacheEvent) message.getData();
				cache.putInCache(event.getKey(), event.getEntry().getContent(),
						null, null, CLUSTER_ORIGIN);
			}
			break;
			
		case CacheConstants.CLUSTER_ENTRY_DELETE:
		    log.debug("集群删除:" + message.getData());
			if (message.getData() instanceof QflagCacheEvent) {
				QflagCacheEvent event = (QflagCacheEvent) message.getData();
				cache.removeEntry(event.getKey());
			}
			break;
			
		default:
            super.handleClusterNotification(message);
			
		}

	}

	public void cacheEntryAdded(CacheEntryEvent event) {
		super.cacheEntryAdded(event);
		if (!CLUSTER_ORIGIN.equals(event.getOrigin())) {
			sendNotification(new ClusterNotification(
					CacheConstants.CLUSTER_ENTRY_ADD, new QflagCacheEvent(event
							.getMap(), event.getEntry(), CLUSTER_ORIGIN)));
		}
	}

//    @Override
//    public void cacheEntryFlushed(CacheEntryEvent event) {
//        super.cacheEntryFlushed(event);
//        if (!CLUSTER_ORIGIN.equals(event.getOrigin())) {
//            sendNotification(new ClusterNotification(CacheConstants.CLUSTER_ENTRY_ADD,
//                new QflagCacheEvent(event.getMap(), event.getEntry(), CLUSTER_ORIGIN)));
//        }
//    }

	@Override
	public void cacheEntryRemoved(CacheEntryEvent event) {
		super.cacheEntryRemoved(event);
		if (!CLUSTER_ORIGIN.equals(event.getOrigin())) {
			sendNotification(new ClusterNotification(
					CacheConstants.CLUSTER_ENTRY_DELETE, new QflagCacheEvent(
							event.getMap(), event.getEntry(), CLUSTER_ORIGIN)));
		}
	}

	@Override
	public void cacheEntryUpdated(CacheEntryEvent event) {
		super.cacheEntryUpdated(event);
		if (!CLUSTER_ORIGIN.equals(event.getOrigin())) {
			sendNotification(new ClusterNotification(
					CacheConstants.CLUSTER_ENTRY_UPDATE, 
					new QflagCacheEvent(event.getMap(), event.getEntry(), CLUSTER_ORIGIN)));
		}
	}

}
