package com.Ens_Library.File.Excel;

import java.io.File;

/**
 * Analogue class Excel, with a modifier - read and write automatically
 */
public class ExcelAuto extends Excel {
    public ExcelAuto(File file) {
        super(file);
    }


    @Override
    public void setFile(File file) {

        close();

        super.setFile(file);

        readAndCloseRead();

    }


    @Override
    public void close() {

        if (getWorkbook() != null) {
            writeAndCloseWrite();
        }
        super.close();


    }
}
