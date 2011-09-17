package tripod;

import java.util.List;
import java.util.Map;

public class TripodClient {
	private CacheProxy cache;
	private ManagerProxy manager;
	private ZooKeeperHelper helper;
	public TripodClient() {
		helper=new ZooKeeperHelper();
		cache=new CacheProxy(helper);
		manager=new ManagerProxy(helper);
	}

	byte[] get(String namespace, String business, String key) {
		return cache.get(namespace, business, key);
	}
	
	Map<String, byte[]> get(String namespace, String business, List<String> keys){
		return cache.get(namespace, business, keys);
	}
	
	void remove(String namespace, String business, String key){
		
	}
}
