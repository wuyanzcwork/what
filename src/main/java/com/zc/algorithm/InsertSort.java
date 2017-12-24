package com.zc.algorithm;

import java.util.Random;

/**
 * Created by zhangchao7 on 16/11/19.
 */
public class InsertSort {

    public static void insertSort(int[] arr) {

        int j = 0;
        for( int i=1;i<arr.length;i++ ) {
            int temp = arr[i];
            for( j=i;j>0&&temp<arr[j-1];j--) {
                arr[j]=arr[j-1];
            }
            System.out.println("j=="+j);
            arr[j] = temp;
        }
    }

    public static void main(String[] args) {

        Random r = new Random();

        int[] arr = new int[100];
        for( int i=0;i<100;i++ ) {
            arr[i] = r.nextInt(200);
        }

        insertSort(arr);

        for( int data : arr ) {
            System.out.println( data );
        }
    }
}
