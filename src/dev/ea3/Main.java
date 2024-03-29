package dev.ea3;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int [] radixArray = {4725, 4586, 1330, 8792, 1594, 5729};

        System.out.println("Unsorted Array: " + Arrays.toString(radixArray));
        
        radixSort(radixArray, 10, 4);
        System.out.println("Sorted Array: " + Arrays.toString(radixArray));

    }

    public static void radixSort(int [] input, int radix, int width){
        for (int i = 0; i < width; i++){
            radixSingleSort(input, i, radix);
        }
    }


    public static void radixSingleSort(int [] input, int position, int radix){

        int numItems = input.length;
        int [] countArray = new int[radix];

        for(int value: input){
            countArray[getDigit(position, value, radix)]++;
        }

        //adjusting the count Array in the position we are working with.
        for(int j = 1; j < radix; j ++){
            countArray[j] += countArray[j - 1];
        }

        int [] temp = new int[numItems];
        for (int tempIndex = numItems - 1; tempIndex >= 0; tempIndex--){
            temp[--countArray[getDigit(position, input[tempIndex], radix )]] = input[tempIndex];
        }

        //copying the unsorted items into the array.
        //we could use Arraycopy, instead we use a for loop .

        for (int tempIndex = 0; tempIndex < numItems; tempIndex++){
            input[tempIndex] = temp[tempIndex];
        }

    }



    public static int getDigit(int position, int value, int radix){
        return value / ((int) Math.pow(radix, position) ) % radix;
    }

}
