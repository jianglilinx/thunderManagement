package org.classsix.ofms.common;

import java.util.Arrays;

/**
 * Created by Jiang on 2018/7/9.
 * Coding
 */
public class Test {
    public static void main(String[] args) {
        int[] arr = {1,200,19,55,10,22,3,7};
//        int temp;
//        for(int i =0;i<arr.length-1;i++){
//            for(int j = 0;j<arr.length-1-i;j++){
//                if(arr[j]>arr[j+1]){
//                    temp = arr[j];
//                    arr[j]=arr[j+1];
//                    arr[j+1]=temp;
//                }
//            }
//        }
        Test.quick(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quick(int[] arr , int left ,int right) {
        if (left > right)
            return;
        int temp = arr[left];
        int i = left;
        int j = right;
        while (i != j) {
            while (arr[j] >= temp && i < j) {
                j --;
            }
            while (arr[i] <= temp && i < j) {
                i ++;
            }

            if (i < j) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        arr[left] = arr[i];
        arr[i] = temp;
        quick(arr,left,i-1);
        quick(arr,i+1,right);
    }
}
