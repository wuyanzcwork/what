package com.zc.algorithm;

import java.util.Random;

/**
 * Created by zhangchao7 on 16/11/19.
 */
public class ShellSort {

    public static void shellSort(int[] a) {

        for( int r=a.length/2;r>=1;r/=2 ) {

            int temp = 0;
            int index = 0;
            for( int i=r;i<a.length;i++ ) {
                temp = a[i];
                index = i-r;
                while( index>=0 && temp<a[index] ) {
                    a[index+r] = a[index];
                    index-=r;
                }
                a[index+r] = temp;
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
