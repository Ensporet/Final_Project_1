package com.Lab1.TestSort;

import com.Lab1.My_Sort.ISort;
import com.Lab1.TestSort.CreateArray.IEmptyCreateArray;
import com.Lab1.TestSort.MethodSort.IMethodFill;
import com.Lab1.TestSort.ObjectTestSort.IObjectTestSort;
import com.Lab1.TestSort.ObjectTestSort.ObjectTestSort;

public class TestSort implements ITestSort {


    @Override
    public IObjectTestSort[] test(ISort[] sorts, IMethodFill[] methodFills, IEmptyCreateArray emptyCreateArray) {

        IObjectTestSort []  objectTestSorts = new IObjectTestSort[sorts.length];
        int[][] integerArrases = emptyCreateArray.createArrases();

        for (int i = 0 ; i < sorts.length ; i++) {
           ObjectTestSort objectTestSort = new ObjectTestSort(sorts[i].getClass().getSimpleName());

            testTime(objectTestSort,
                    sorts[i],
                    methodFills,
                    integerArrases);

            objectTestSorts[i] = objectTestSort;
        }

        return objectTestSorts;
    }






    private long testTime(IObjectTestSort objectTestSort ,ISort sort , IMethodFill []  methodFill , int[] ... ints) {

        long time = 0;

        for ( IMethodFill method : methodFill) {

            time += testTime(sort,method,ints);
            objectTestSort.getTime().put(method.getClass().getSimpleName() , time);
        }
     return time;
    }




    private long testTime(ISort sort , IMethodFill  methodFill , int[] ... ints) {

        methodFill.methodFill(ints);

      long time = System.nanoTime();

      sort.sort(ints);


      return (System.nanoTime() - time) ;
    }




}
