package tripod;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperHelper implements Watcher {
	private static final Log logger = LogFactory.getLog(ZooKeeperHelper.class);
	public ZooKeeperHelper() {
		keeper = null;
	}

	ZooKeeper keeper;

	ZooKeeper getZooKeeper() throws InterruptedException, IOException, TripodException {
		if (null == keeper) {
			synchronized(keeper){
				while(true){
					keeper = new ZooKeeper("",0,this);
					keeper.wait();
					if(null==keeper){
						continue;
					}
				}
			}
		}
		return keeper;
	}

	@Override
	public void process(WatchedEvent event) {
		if (event.getType() == Event.EventType.None) {
			switch (event.getState()) {
				case SyncConnected:
					keeper.notify();
					break;
				case Expired:
					try {
						keeper.close();
					} catch (InterruptedException e) {
						logger.warn("ZooKeeperHelper.process: can't close the keeper", e);
					}
					synchronized(keeper){
						keeper=null;
					}
					break;
			}
		} else if(event.getPath().length() != 0){
		}
	}
}
