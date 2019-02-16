package com.Lab1.TestSort;

import com.Lab1.My_Sort.ISort;
import com.Lab1.TestSort.CreateArray.IEmptyCreateArray;
import com.Lab1.TestSort.MethodSort.IMethodFill;
import com.Lab1.TestSort.ObjectTestSort.IObjectTestSort;

public interface ITestSort {



    IObjectTestSort[] test(ISort [] sorts , IMethodFill [] methodFills , IEmptyCreateArray emptyCreateArray);


}
