package com.Lab1.TestSort.MethodSort;

import java.util.Random;

/**
 * 1 2 3 ... to max + random number
 */
public class MethodTwo implements IMethodFill {


    @Override
    public void methodFill(int[]... integers) {

        int i = -1;
        Random random = new Random();
        for (int[] ints : integers) {
            for (int number = 0; number < ints.length; number++) {
                ints[number] = ++i;
            }
            ints[ints.length - 1] = random.nextInt();
            i--;
        }
    }

    @Override
    public String getMethodName() {
        return "Ascending numbers plus random element";
    }
}
