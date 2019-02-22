package com.Lab1.TestSort;

import com.Lab1.My_Sort.ISort;
import com.Lab1.My_Sort.ISortUnion;
import com.Lab1.TestSort.CreateArray.IEmptyCreateArray;
import com.Lab1.TestSort.MethodSort.IMethodFill;
import com.Lab1.TestSort.ObjectTestSort.IObjectTestSort;
import com.Lab1.TestSort.ObjectTestSort.ObjectTestSort;

public class TestSort implements ITestSort {


    @Override
    public IObjectTestSort[] test(ISort[] sorts, IMethodFill[] methodFills, IEmptyCreateArray emptyCreateArray) {

        IObjectTestSort[] objectTestSorts = new IObjectTestSort[sorts.length];
        int[] integerArrases = emptyCreateArray.createArray();


        for (int i = 0; i < sorts.length; i++) {
            ObjectTestSort objectTestSort = new ObjectTestSort(sorts[i].getName(), integerArrases.length);


            if (sorts[i] instanceof ISortUnion) {

                testTime(objectTestSort,
                        sorts[i],
                        methodFills,
                        emptyCreateArray.createArrases(-1, integerArrases.length));
            } else {

                testTime(objectTestSort,
                        sorts[i],
                        methodFills,
                        integerArrases);
            }
            objectTestSorts[i] = objectTestSort;
        }

        return objectTestSorts;
    }


    /**
     * Test sorter with different methods of array fillings. The result is recorded in order to sort.
     *
     * @param objectTestSort
     * @param sort
     * @param methodFill
     * @param ints
     * @return
     */
    private long testTime(IObjectTestSort objectTestSort, ISort sort, IMethodFill[] methodFill, int[]... ints) {

        long time = 0;

        for (int i = 0; i < methodFill.length; i++) {

            time += testTime(sort, methodFill[i], ints);
            objectTestSort.getTime().put(methodFill[i].getMethodName(), time);
        }
        return time;
    }


    /**
     * One iteration of test
     *
     * @param sort
     * @param methodFill
     * @param ints
     * @return
     */
    private long testTime(ISort sort, IMethodFill methodFill, int[]... ints) {

        methodFill.methodFill(ints);

        long time = System.nanoTime();
        if (time < 0) {
            time *= -1;
        }

        sort.sort(ints);

        long l = System.nanoTime();
        if (l < 0) {
            l *= -1;
        }

        return (l - time);
    }
}
