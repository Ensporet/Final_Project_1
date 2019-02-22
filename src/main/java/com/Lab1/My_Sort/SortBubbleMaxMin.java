package com.Lab1.My_Sort;

/**
 * Bubble sorting with building from maximum to minimum
 */
public class SortBubbleMaxMin implements ISort {


    @Override
    public void sort(int[]... integerArrases) {

        if (integerArrases == null) {
            return;
        }

        for (int integerArray[] : integerArrases) {

            if (integerArrases != null) {

                for (int iterations = 0; iterations < (integerArray.length - 1); iterations++) {

                    sortIterateMaxMin(integerArray, iterations);

                }
            }
        }
    }

    @Override
    public String getName() {
        return "Bubble sorting, from maximum to minimum";
    }


    private void sortIterateMaxMin(int[] integerArray, final int indexFinalist) {

        for (int index = (integerArray.length - 1); index > indexFinalist; index--) {

            max_min(integerArray, index, (index - 1));
        }
    }


    private void max_min(int[] integerArray, int indexOne, int indexTwo) {

        if (integerArray[indexOne] > integerArray[indexTwo]) {

            int i = integerArray[indexOne];
            integerArray[indexOne] = integerArray[indexTwo];
            integerArray[indexTwo] = i;
        }
    }
}
