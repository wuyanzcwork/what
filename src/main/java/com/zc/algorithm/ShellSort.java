package com.zc.algorithm;

import java.util.Random;

/**
 * Created by zhangchao7 on 16/11/19.
 */
public class ShellSort {

    public static void shellSort(int[] a) {
        for( int gap=a.length/2;gap>0;gap/=2 ) {

            for( int i=gap;i<a.length;i++ ) {
                int j=0;
                int temp = a[i];
                for( j=i;j>=gap&&temp<a[j-gap];j-=gap ) {
                    a[j] = a[j-gap];
                }
                a[j] = temp;
            }
        }
    }

    public static void main(String[] args) {

        Random r = new Random();

        int[] arr = new int[100];
        for( int i=0;i<100;i++ ) {
            arr[i] = r.nextInt(200);
        }

        shellSort(arr);

        for( int data : arr ) {
            System.out.println( data );
        }
    }
}
