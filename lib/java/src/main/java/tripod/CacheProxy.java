package tripod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.zookeeper.WatchedEvent;

import com.danga.MemCached.MemCachedClient;

public class CacheProxy implements ZooKeeperListener {
	private Map<String, MemCachedClient> caches;
	private ZooKeeperHelper helper;

	public CacheProxy(ZooKeeperHelper helper) {
		caches=null;
		this.helper=helper;
	}
	
	void initialize(){
		
	}

	public byte[] get(String namespace, String business, String key) {
		return (byte[]) caches.get(namespace).get(business + "_" + key);
	}
	
	public Map<String, byte[]> get(String namespace, String business, List<String> keys){
		Map<String, Object> cacheresult = caches.get(namespace).getMulti(keys.toArray(new String[keys.size()]));
		Map<String, byte[]> result=new HashMap<String, byte[]>();
		for(Entry<String, Object> e:cacheresult.entrySet()){
			result.put(e.getKey(), (byte[])e.getValue());
		}
		return result;
	}

	@Override
	public void notify(WatchedEvent event) {
		
	}
}
