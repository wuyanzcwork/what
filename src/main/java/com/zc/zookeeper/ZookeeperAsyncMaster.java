package com.zc.zookeeper;

import java.io.IOException;
import java.util.Random;

import org.apache.zookeeper.AsyncCallback.StringCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author zhangchao7
 * 使用原生zookeeper api实现
 * 异步方式调用zookeeper的create来模拟竞争
 */
public class ZookeeperAsyncMaster implements Watcher {

	private static final String address = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
	
	private ZooKeeper zk;
	
	private String serverid = String.valueOf( new Random().nextLong() );
	
	private boolean isLeader = false;
	
	private MyStringCallback callback = new MyStringCallback();
	
	public ZookeeperAsyncMaster() {
	}
	
	public void startZk() throws IOException {
		zk = new ZooKeeper(address, 15000, this);
	}
	
	public void stopZk() throws InterruptedException {
		zk.close();
	}
	
	@Override
	public void process(WatchedEvent watcher) {
		//System.out.println( watcher );
	}
	
	/**
	 * 因为是异步的不需要抛出异常
	 * 提交任务后等待call回调判断是否创建成功
	 */
	public void runForMaster(byte[] data) {
		zk.create("/master", serverid.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
				CreateMode.EPHEMERAL, callback, data);
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
					}
				}
			}).start();
		}

	}
	
	public static void test() throws IOException, InterruptedException {
		ZookeeperAsyncMaster m = new ZookeeperAsyncMaster();
		m.startZk();
		
		m.runForMaster(new byte[]{0});

		Thread.sleep(10000);
		
		m.stopZk();
	}

	class MyStringCallback implements StringCallback {
		
		@SuppressWarnings("deprecation")
		@Override
		public void processResult(int resultCode, String path, Object context, String name) {
			
			System.out.println( "callback:resultCode:"+resultCode+",path:"+path+",context:"+context+",name:"+name+",isLeader:"+isLeader ); 
			switch (resultCode) {
			case Code.ConnectionLoss:
				runForMaster((byte[])context);
				break;
			case Code.Ok:
				isLeader = true;
				break;
			case Code.NodeExists:
				isLeader = false;
				break;
			default:
				isLeader = true;
				break;
			}
			if( isLeader ) {
				System.out.println(" i m leader. ");
			} else {
				System.out.println(" i m not leader. ");
			}
		}
	}
}

