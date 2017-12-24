package com.zc.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.KeeperException.ConnectionLossException;
import org.apache.zookeeper.KeeperException.NoNodeException;
import org.apache.zookeeper.KeeperException.NodeExistsException;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Random;

/**
 * @author zhangchao7
 * 使用原生zookeeper api实现
 * 同步方式调用zookeeper的create来模拟竞争
 */
public class ZookeeperSyncMaster implements Watcher {

	private static final String address = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
	
	private ZooKeeper zk;
	
	private String serverid = String.valueOf( new Random().nextLong() );
	
	private static boolean isLeader = false;
	
	public ZookeeperSyncMaster() {
	}
	
	public void startZk() throws IOException {
		zk = new ZooKeeper(address, 15000, this);
	}
	
	public void stopZk() throws InterruptedException {
		zk.close();
	}
	
	@Override
	public void process(WatchedEvent watcher) {
		System.out.println( watcher );
	}
	
	public void runForMaster() throws InterruptedException, KeeperException {
		
		while (true) {
			try {
				zk.create("/master", serverid.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
				isLeader = true;
				break;
			} catch (NodeExistsException e) {
				e.printStackTrace();
				isLeader = false;
				break;
			} catch (ConnectionLossException e) {
				e.printStackTrace();
			}
			if ( checkForMaster() )
				break;
		}
	}
	
	public boolean checkForMaster() throws KeeperException, InterruptedException {
		
		Stat stat = new Stat();
		while( true ) {
			try {
				byte[] data = zk.getData("/master", false, stat);
				isLeader = new String(data).equals(serverid);
				break;
			} catch (NoNodeException e) {
				e.printStackTrace();
				isLeader = false;
				break;
			} catch (ConnectionLossException e) {
				e.printStackTrace();
			}
		}
		return isLeader;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		
		for( int i=0;i<10;i++ ) {
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						test();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (KeeperException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

	}
	
	public static void test() throws IOException, InterruptedException, KeeperException {
		ZookeeperSyncMaster m = new ZookeeperSyncMaster();
		m.startZk();
		
		m.runForMaster();
		
		if( isLeader ) {
			System.out.println(" i m leader. ");
			Thread.sleep(2000);
		} else {
			System.out.println(" i m not leader. ");
		}
		m.stopZk();
	}
}
