package com.zc.algorithm;

import java.util.Random;

/**
 * 冒泡排序
 * Created by zhangchao7 on 16/11/19.
 */
public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        System.out.println("testbranch1");
        int length = arr.length;
        System.out.println("testbranch1");
        for( int i=1;i<length;i++ ) {
            int temp = 0;
            for( int j=0;j<length-i;j++ ) {
                if( arr[j]>arr[j+1] ) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println("testbranch1");
    }

    public static void main(String[] args) {

        Random r = new Random();

        int[] arr = new int[100];
        for( int i=0;i<100;i++ ) {
            arr[i] = r.nextInt(200);
        }

        bubbleSort(arr);

        for( int data : arr ) {
            System.out.println( data );
        }
    }

}
