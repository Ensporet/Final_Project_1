package com.Lab1.My_Sort;

public interface ISort {



    public void sort(int [] ... integerArraies);



    public static void printlnArray(int [] ... integerArraies) {

        for (int []integerArray : integerArraies ) {

        printlnArray(integerArray);
        }

    }

    public static void printlnArray(int []integerArray) {

            if (integerArray == null || integerArray.length == 0) {
                System.out.println("Array is empty");
            } else {
                for (int i : integerArray) {
                    System.out.print("[" + i + "]");
                }
                System.out.println();
            }



    }


}
