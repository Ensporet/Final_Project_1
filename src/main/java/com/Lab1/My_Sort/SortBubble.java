package com.Lab1.My_Sort;


public class SortBubble implements ISortBubble{

    @Override
    public void sort(int[] integerArray, boolean isDrown) {

        if (integerArray == null) {
            return;
        }


        if (isDrown) {
            for (int iterations = 0 ; iterations < (integerArray.length - 1); iterations++ ) {
                ISort.printlnArray(integerArray);
                sortIterateMaxMin(integerArray , iterations);

            }
        } else {
            for (int iterations = (integerArray.length - 1) ; iterations > 0 ; iterations--) {
                sortIterateMinMax(integerArray , iterations);
            }
        }


    }



    private void sortIterateMaxMin(int [] integerArray , final int indexFinalist) {

        for (int index = (integerArray.length - 1); index > indexFinalist ; index--) {

            max_min(integerArray , index , (index - 1));

        }
    }


    private void sortIterateMinMax(int [] integerArray , final int indexFinalist) {

        for (int index = 0 ; index < indexFinalist ; index++) {

            min_max(integerArray , index , (index + 1));

        }
    }

    private void max_min(int [] integerArray , int indexOne , int indexTwo) {

        if (integerArray[indexOne] > integerArray[indexTwo]) {

            int i = integerArray[indexOne] ;
            integerArray[indexOne] = integerArray[indexTwo];
            integerArray[indexTwo] = i ;



        }
    }

    private void min_max(int [] integerArray , int indexOne , int indexTwo) {

        if (integerArray[indexOne] > integerArray[indexTwo]) {

            int i = integerArray[indexTwo] ;
            integerArray[indexTwo] = integerArray[indexOne];
            integerArray[indexOne] = i ;
        }
    }


}
