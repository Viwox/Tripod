package tripod;

import org.apache.zookeeper.WatchedEvent;

public interface ZooKeeperListener {
	public void notify(WatchedEvent event);
}
