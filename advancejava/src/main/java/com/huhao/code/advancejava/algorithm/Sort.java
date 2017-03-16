package com.huhao.code.advancejava.algorithm;

/**
 * Created by huhao on 2017/3/3.
 */
public class Sort {


    public  void quickSort(int start, int end,int[] data){
        if(data == null || data.length <= 0){
            return;
        }

        int length = data.length;
        int r = length-1;

        int partition = partition(0,r,data);
        quickSort(start,partition-1,data);
        quickSort(partition+1,end,data);


    }

    private int partition(int left, int right, int[] data){
        if(left >= right){
            return left;
        }

        int leftCursor = left -1;
        while (left < right){
            if(data[left] <= data[right]){
                leftCursor++;
                swap(leftCursor,left,data);
            }
            left++;
        }
        swap(++leftCursor,right,data);

        return leftCursor;
    }

    private void swap(int left, int right,int[] data){
        data[left] = data[left] ^ data[right];
        data[right] = data[right] ^ data[left];
        data[left] = data[left] ^ data[right];
    }

    public static void main(String[] args){
        Sort sort = new Sort();
        int[] data = new int[]{5,2,1,4};

        sort.quickSort(0,3,data);
        for(int i = 0; i<data.length;i++){
            System.out.println(data[i]);
        }


    }
}
