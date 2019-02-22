package com.Lab1.TestSort;

import com.Lab1.My_Sort.ISort;
import com.Lab1.My_Sort.SortBubbleMaxMin;
import com.Lab1.TestSort.CreateArray.IEmptyCreateArray;
import com.Lab1.TestSort.MethodSort.IMethodFill;
import com.Lab1.TestSort.MethodSort.MethodOne;
import com.Lab1.TestSort.ObjectTestSort.IObjectTestSort;

/**
 * XXX
 * New method more accurate test. Do not use
 */
public class TestSort01 implements ITestSort {


    public static void main(String[] args) {
        TestSort01 testSort01 = new TestSort01();


        MethodOne methodOne = new MethodOne();
        int[] i = new int[1000];
        ISort sort = new SortBubbleMaxMin();

        testSort01.test();

    }


    public void test() {
        TestSort01 testSort01 = new TestSort01();


        MethodOne methodOne = new MethodOne();
        int[] i = new int[1000];
        ISort sort = new SortBubbleMaxMin();
        System.out.println();
        System.out.println(testSort01.testMethodFill(i, methodOne, sort, 100));
        System.out.println(testSort01.testMethodFill(i, methodOne, sort, 100));
        System.out.println(testSort01.testMethodFill(i, methodOne, sort, 100));
        System.out.println();
    }


    @Override
    public IObjectTestSort[] test(ISort[] sorts, IMethodFill[] methodFills, IEmptyCreateArray emptyCreateArray) {


        return new IObjectTestSort[0];
    }


    protected long testMethodFill(int[] i, IMethodFill methodFill, ISort sort, int iteration) {

        if (methodFill == null || sort == null || i == null) {
            return -1;
        }

        long result = 0;
        while (iteration-- > 0) {

            methodFill.methodFill(i);
            System.gc();
            long l = System.nanoTime();
            if (l < 0) {
                l *= -1;
            }

            sort.sort(i);

            long l0 = System.nanoTime();
            if (l0 < 0) {
                l0 *= -1;
            }

            long lon = (l0 - l);
            if (result < lon) {
                result = lon;
            }

        }
        return result;
    }


}
