package com.Lab1.TestSort.CreateArray;

/**
 * Class for creating empty arrays
 */
public interface IEmptyCreateArray {

    int MAX_ARRASES = 100;              //Maximum number of possible arrays
    int MIN_ELEMENTS_OF_ARRAY = 2;      //The minimum size of a one array
    int MAX_ELEMENT_OF_ARRASES = 10000;  //Total maximum number of array elements


    /**
     * Corrects adequate value.
     *
     * @return
     */
    default int correctMAX_ELEMENT() {
        int max = IEmptyCreateArray.MAX_ARRASES * IEmptyCreateArray.MIN_ELEMENTS_OF_ARRAY;

        return (max > IEmptyCreateArray.MAX_ELEMENT_OF_ARRASES) ? max : IEmptyCreateArray.MAX_ELEMENT_OF_ARRASES;
    }

    /**
     * Creates a random array
     *
     * @return
     */
    default int[] createArray() {
        return createArray(-1);
    }


    int[] createArray(int length);


    /**
     * Creates random arrays with a random number of cells.
     *
     * @return
     */
    default int[][] createArrases() {
        return createArrases(-1, -1);
    }

    /**
     * Creates random arrays with a random number of cells.
     *
     * @param sizeArrases    -1 random
     * @param sizeAllElement -1 random
     * @return
     */
    int[][] createArrases(int sizeArrases, int sizeAllElement);

}
