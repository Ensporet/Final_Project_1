package com.Lab1;


import java.lang.reflect.Array;

public class test0  {


    public static void main(String[] args) {

      String [] strings = {
              "one" ,
              "two" ,
              "three"
      };


        System.out.println(new test0().method(strings));
    }




    public  <T> T[] method(T [] array) {

        T [] newArray = (T[]) Array.newInstance(array.getClass().getComponentType() , array.length);

        return newArray;
    }

}
