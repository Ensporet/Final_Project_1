package com.Lab1.TestSort.CreateArray;

import java.util.Random;

public interface IEmptyCreateArray {

    int MAX_ARRASES = 100;
    int MIN_ELEMENTS_OF_ARRAY = 2;
    int MAX_ELEMENT_OF_ARRASES = 1000;


    default int correctMAX_ELEMENT(){
      return   ((IEmptyCreateArray.MAX_ARRASES  * IEmptyCreateArray.MIN_ELEMENTS_OF_ARRAY) >
                 IEmptyCreateArray.MAX_ELEMENT_OF_ARRASES) ?
                (IEmptyCreateArray.MAX_ARRASES  *
                 IEmptyCreateArray.MIN_ELEMENTS_OF_ARRAY) :
                 IEmptyCreateArray.MAX_ELEMENT_OF_ARRASES ;
    }



    default int[] createArray() {

    int length ;

        return new int[((length = new Random().nextInt(correctMAX_ELEMENT())) < IEmptyCreateArray.MIN_ELEMENTS_OF_ARRAY) ?
                IEmptyCreateArray.MIN_ELEMENTS_OF_ARRAY : length];

    }


    default int[][] createArrases() {

        Random random = new Random();


        int size = random.nextInt(IEmptyCreateArray.MAX_ARRASES);
        int[][] ret = new int[(size == 0) ? 1 : size][];
        int maxElement = correctMAX_ELEMENT() - (size * IEmptyCreateArray.MIN_ELEMENTS_OF_ARRAY);

        for (int i = 0; i < size; i++) {

            int ran = random.nextInt(maxElement) / size;
            if (ran < IEmptyCreateArray.MIN_ELEMENTS_OF_ARRAY) {
                ran = IEmptyCreateArray.MIN_ELEMENTS_OF_ARRAY;
            } else {
                maxElement -= ran;
            }

            ret[i] = new int[ran];
        }

        return ret;
    }


}
