package com.zc.test;

import com.zc.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhangchao on 2017/7/13.
 */
public class TestTDC {

    public static void main(String[] args) throws Exception {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/zhangchao54/Desktop/xxx.txt")));

        ExecutorService pool = Executors.newFixedThreadPool(10);
        int[] i = {0};
        String line = "";
        while( StringUtils.isNotBlank(line = bufferedReader.readLine()) ) {

            pool.submit(new SyncTask(line));

        }
    }

}

class SyncTask implements Runnable {

    private String poiId;

    public SyncTask(String poiId) {

        this.poiId = poiId;
    }


    @Override
    public void run() {
        try {
            String params = "poiId=" + poiId + "&authCode=jfqijJ(*E8jf";
            String synconePoiResult = HttpUtil.post("http://10.32.175.101:8418/test/synonepoi", params);
            String synconeRoomResult = HttpUtil.post("http://10.32.175.101:8418/test/synOnePoiRoomInfo", params);

            System.out.println("synconePoiResult:"+synconePoiResult+",synconeRoomResult:"+synconeRoomResult+",poiId:"+poiId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
