package com.Lab1.My_Sort;

public class SortUnion implements ISortUnion {


    @Override
    public int[] sort(ISort sort , int[]... integerArray) {

        if (integerArray == null || integerArray.length == 0) {
            return new int[0];
        }


        if (sort == null) {
            sort = new SortBubble();
        }

        SortUnion.SortClass [] sortClasses = new SortClass[integerArray.length];
        int size = 0;
        for (int iteration = 0 ; iteration < integerArray.length ; iteration++) {
            int [] i = integerArray[iteration];
            sort.sort(i);
            sortClasses[iteration] = new SortClass(i);
            size += i.length;
        }


        int [] ret = new int[size];
        for (int iteration = 0 ; iteration < size ; iteration++ ) {
            ret[iteration] = sethMin(sortClasses);
        }


        return ret;
    }





    private int sethMin(SortClass[] sortClass) {


        int posMin = -1;
        Integer min = null ;

        for (int iteration = 0; iteration < sortClass.length ; iteration++) {


            if (sortClass[iteration].isEnd()) {
                continue;
            }

            if ( (min == null || min > sortClass[iteration].getInt())) {
                min = sortClass[iteration].getInt();
                posMin = iteration;
            }

        }



     return sortClass[posMin].getIntIteration();
    }








    private class SortClass {

        private final int[] intArray ;
        private int pos = 0;


        private SortClass(int[] intArray) {
            this.intArray = intArray;
        }


        int getInt() {
            return intArray[pos];
        }

        int getIntIteration() {


            return intArray[pos++];
        }


        boolean isEnd () {
            return (pos >= intArray.length) ;
        }




    }


}
