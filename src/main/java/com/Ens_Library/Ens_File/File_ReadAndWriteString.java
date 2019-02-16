package com.Ens_Library.Ens_File;

import java.io.*;

public class File_ReadAndWriteString extends File_default {



    public File_ReadAndWriteString(){
        super();
    }

    public File_ReadAndWriteString(File f) {

        super(f);


    }

    public File_ReadAndWriteString(String pach) {

        super(pach);


    }


    //Variables
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    private BufferedWriter bufferedWriter;

    protected BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    protected void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    //--------------------------------------------------
    private FileWriter fileWriter;

    protected FileWriter getFileWriter() {
        return fileWriter;
    }

    protected void setFileWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    //-------------------------------------------------------


    public void setFile(File file) {

        close();
        super.setFile(file);
        buld();
    }

    //------------------------------------------------------------

    private FileReader fileReader;

    protected FileReader getFileReader() {
        return fileReader;
    }

    protected void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }
    //-----------------------------------------------------------

    private BufferedReader bufferedReader;

    protected BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    protected void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    ///Functions
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    public void printlnAllFile() {

        while (true) {

            String s = readLine();
            if (s == null) {
                return;
            } else {
                System.out.println(s);
            }
        }


    }
//--------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------
    public void writeLine(String s) {

        if (s == null || getBufferedWriter() == null || getFileWriter() == null) return;


        try {

            getBufferedWriter().write(s);
            getBufferedWriter().newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    //-----------------------------------------------------------
    public String readLine() {

        if (getBufferedReader() == null || getFileReader() == null) {
            return null;
        }
        try {
            return getBufferedReader().readLine();
        } catch (IOException io) {
            return null;
        }
    }


    //------------------------------------------------------------------

    protected boolean buld() {


        close();

        try {


            if (getFile() == null) {
                this.setFile(this.newFile(null, null));
            } else {
                if (!getFile().exists()) {
                    this.setFile(this.newFile(getFile().getParent(), getFile().getName()));

                }
            }

            setFileReader(new FileReader(getFile()));
            setBufferedReader(new BufferedReader(getFileReader()));
            setFileWriter(new FileWriter(getFile(), true));
            setBufferedWriter(new BufferedWriter(getFileWriter()));

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }
    //----------------------------------


    //-------------------------------------------------------------
    public void close() {

        if (getBufferedReader() != null) {
            try {
                getBufferedReader().close();
            } catch (IOException e) {
            } finally {
                setBufferedReader(null);
            }
        }

        if (getFileReader() != null) {
            try {
                getFileReader().close();
            } catch (IOException e) {
            } finally {
                setFileReader(null);
            }
        }


        if (getBufferedWriter() != null) {
            try {
                getBufferedWriter().close();
            } catch (IOException io) {
            } finally {
                setBufferedWriter(null);
            }
        }

        if (getFileWriter() != null) {
            try {
                getFileWriter().close();
            } catch (IOException io) {
            } finally {
                setFileWriter(null);
            }
        }




    }


}
