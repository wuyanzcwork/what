package com.zc.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import java.io.IOException;
import java.util.Random;

/**
 * Created by zhangchao7 on 16/11/27.
 */
public class Master implements Watcher {

    private ZooKeeper zooKeeper;
    private static final String port = "59.110.45.89:2181,59.110.45.89:2182,59.110.45.89:2183";
    private boolean isLeader = false;
    private String serverId = String.valueOf(new Random().nextLong());

    public void startZookeeper() throws IOException {
        zooKeeper = new ZooKeeper(port, 15000, this);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
    }

    public void runForMaster() throws InterruptedException {
        while (true) {
            try {
                zooKeeper.create("/master", serverId.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                isLeader = true;
                break;
            } catch (NodeExistsException e) {
                e.printStackTrace();
                isLeader = false;
                break;
            } catch (ConnectionLossException e) {
                e.printStackTrace();
            }
            checkForMaster();
        }
    }

    private void checkForMaster() {
        Stat stat = new Stat();
        while (true) {
            zooKeeper.getData("/master", false, stat);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        Master master = new Master();
        master.startZookeeper();
        Thread.sleep(60000);

    }
}
