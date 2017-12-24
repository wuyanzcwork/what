package com.zc.algorithm;

import java.util.Random;

/**
 * 选择排序
 * Created by zhangchao7 on 16/11/19.
 */
public class SelectSort {

    public static void selectSort(int[] arr) {

        int index = 0;
        int temp = 0;

        for( int i=0;i<arr.length-1;i++ ) {
            index = i;
            for( int j=i+1;j<arr.length;j++ ) {
                if( arr[j]<arr[index] ) {
                    index = j;
                }
            }
            if( i!=index ) {
                temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
            }
        }
    }

    public static void main(String[] args) {

        Random r = new Random();

        int[] arr = new int[100];
        for( int i=0;i<100;i++ ) {
            arr[i] = r.nextInt(200);
        }

        selectSort(arr);

        for( int data : arr ) {
            System.out.println( data );
        }
    }
}
