package com.zc.algorithm;

import java.util.Random;

/**
 * Created by zhangchao7 on 16/11/19.
 */
public class InsertSort {

    public static void insertSort(int[] arr) {
	//asdasda
        int temp = 0;
        int index = 0;
        for( int i=1;i<arr.length;i++ ) {
            temp = arr[i];
            index = i-1;
            while( index>=0 && temp<arr[index] ) {
                arr[index+1] = arr[index];
                index--;
            }
            arr[index+1] = temp;
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
