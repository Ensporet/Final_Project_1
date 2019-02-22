package com.Lab1.TestSort;

import com.Lab1.My_Sort.ISort;
import com.Lab1.TestSort.CreateArray.IEmptyCreateArray;
import com.Lab1.TestSort.MethodSort.IMethodFill;
import com.Lab1.TestSort.ObjectTestSort.IObjectTestSort;

/**
 * class for creating arrays of objects with tests
 * [n][x]
 * x one step . With an equal number of array elements
 */
public interface ITestSort {


    IObjectTestSort[] test(ISort[] sorts, IMethodFill[] methodFills, IEmptyCreateArray emptyCreateArray);
}
