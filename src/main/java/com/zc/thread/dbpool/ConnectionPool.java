package com.zc.thread.dbpool;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhangchao7 on 16/10/22.
 */
public class ConnectionPool {

    private LinkedList<Connection> pool = new LinkedList<Connection>();

    public ConnectionPool(int initSize) {
        if (initSize > 0) {
            for (int i=0; i<initSize; i++) {
                Connection driver = ConnectionDriver.createConnection();
                pool.add(driver);
            }
        }
    }

    public void releaseConnection(Connection conn) {
        if( conn==null ) return;

        synchronized (pool) {
            pool.addLast(conn);
            pool.notifyAll();
        }
    }

    public Connection fetchConnection(long mills) throws Exception {
        synchronized ( pool ) {
            if( mills<=0 ) {
                while( pool.isEmpty() ) {
                    wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remain = mills;
                while ( pool.isEmpty() && remain>0 ) {
                    wait(remain);
                    remain = future - System.currentTimeMillis();
                }
                Connection conn = null;
                if( !pool.isEmpty() ) {
                    conn = pool.removeFirst();
                }
                return conn;
            }
        }
    }
}
