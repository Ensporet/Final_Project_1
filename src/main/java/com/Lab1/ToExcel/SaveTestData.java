package com.Lab1.ToExcel;

import com.Ens_Library.File.Excel.ElementСonstructors.Chart.ExcelChartLine;
import com.Ens_Library.File.Excel.ElementСonstructors.Table.ExcelTable;
import com.Ens_Library.File.Excel.Excel;
import com.Lab1.TestSort.ObjectTestSort.IObjectTestSort;

import java.util.Arrays;


public class SaveTestData implements ISaveTestData {

    @Override
    public void save(Excel excel, IObjectTestSort[]... objectTestSorts) {

        if (objectTestSorts == null || objectTestSorts.length < 1) {
            return;
        }

        int posRowTwoChart = 0;
        int posCulTwoChart = 0;
        int lengthChart = 20;
        int heightChart = 25;
        for (int i = 0; i < objectTestSorts[0].length; i++) {

            new ExcelTable().addTable(
                    excel.getSheet(objectTestSorts[0][i].getName()),
                    0,
                    0,
                    true,
                    true,
                    createObjectsForTable(objectTestSorts, i)
            );

            new ExcelChartLine().addToSheet(
                    excel.getSheet(), 0, 0, -1, -1,
                    null, -1, -1, lengthChart, heightChart,
                    "Array length", "Time of nano seconds", null
            );

            new ExcelChartLine().addToSheet(
                    excel.getSheet(), 0, 0, -1, -1,

                    excel.getSheet("All charts"),
                    posRowTwoChart + (heightChart * i),
                    posCulTwoChart,
                    lengthChart,
                    heightChart,

                    "Array length", "Time of nano seconds", objectTestSorts[0][i].getName()
            );
        }

        excel.writeAndCloseWrite();
        excel.close();

    }


    //------------------------------------------------------------------------------------------------------------------

    /**
     * Converting objects for writing to Excel table
     * [Rows][cells]
     *
     * @param objectTestSorts
     * @param indexOfSort
     * @return
     */
    protected Object[][] createObjectsForTable(IObjectTestSort[][] objectTestSorts, int indexOfSort) {

        Object[][] rows = new Object[objectTestSorts[0][0].getTime().size() + 1]         //row
                [objectTestSorts.length + 1];                     //cell

        rows[0] = createNameTitleRow(objectTestSorts, indexOfSort);                      //nameCells


        String nameRow[] = new String[objectTestSorts[0][indexOfSort].getTime().size()];
        objectTestSorts[0][indexOfSort].getTime().keySet().toArray(nameRow);
        Arrays.sort(nameRow);

        {
            int index = 1;
            for (String key : nameRow) {       //nameRows
                rows[index++][0] = key;
            }
        }


        for (int i = 0; i < objectTestSorts.length; i++) {                              //values

            int indexRow = 1;
            for (int l = 0; l < nameRow.length; l++) {

                rows[indexRow++][i + 1] = objectTestSorts[i][indexOfSort].getTime().get(nameRow[l]);
            }

        }

        return rows;
    }


    /**
     * Create row for name
     *
     * @param objectTestSorts
     * @param indexOfSort
     * @return
     */
    protected Object[] createNameTitleRow(IObjectTestSort[][] objectTestSorts, int indexOfSort) {

        Object[] strings = new Object[objectTestSorts.length + 1];
        strings[0] = "Method name \\ Size of elements";


        for (int i = 0; i < objectTestSorts.length; i++) {

            strings[i + 1] = objectTestSorts[i][indexOfSort].getLengthArray();

        }

        return strings;
    }


}
