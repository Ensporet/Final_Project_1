package com.Lab1.TestSort.ObjectTestSort;

import java.util.HashMap;

public interface IObjectTestSort {


            HashMap<String ,Long> getTime();

            String getName();





            public static void printlnClass(IObjectTestSort iObjectTestSort){

                System.out.println(iObjectTestSort.toString());
            }
    public static void printlnClass(IObjectTestSort []iObjectTestSort){

                for(IObjectTestSort objectTestSort : iObjectTestSort) {
                    printlnClass(objectTestSort);

                }

    }


}
