package com.Lab1.TestSort.ObjectTestSort;

import java.util.HashMap;

/**
 * Object for save test sort
 */
public interface IObjectTestSort {

    /**
     * key : name of method fill
     * long : nanosecond of run method sort
     *
     * @return
     */
    HashMap<String, Long> getTime();

    /**
     * name sort
     *
     * @return
     */
    String getName();

    /**
     * Length size all element (arrays or array) which used for test
     *
     * @return
     */
    int getLengthArray();

}
