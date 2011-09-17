package tripod;

import java.util.Map;

import org.apache.zookeeper.WatchedEvent;

public class ManagerProxy implements ZooKeeperListener{
	private Map<String, Object> managers;
	private ZooKeeperHelper helper;
	public ManagerProxy(ZooKeeperHelper helper){
		managers=null;
		this.helper=helper;
	}
	@Override
	public void notify(WatchedEvent event) {
		
	}

}
