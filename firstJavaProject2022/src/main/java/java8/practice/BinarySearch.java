package java8.practice;

import java.util.Arrays;

public class BinarySearch {

    static void main() {
        Integer[] arr= {72,23,8,2,91,56,38,16};
        int element=56;

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length;j++){
                if(arr[i]<arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j]=temp;
                }
            }
        }
        System.out.println("sorted array is :: " + Arrays.toString(arr));


        int start=0;
        int end=arr.length-1;
        while(start<=end){
            int mid = (start+end)/2;
            if(arr[mid]<element){
                start=mid+1;
            } else if (arr[mid]>element) {
                end=mid-1;
            } else {
                System.out.println("Element found at index :: " + mid);
                return;
            }

        }



    }
}
