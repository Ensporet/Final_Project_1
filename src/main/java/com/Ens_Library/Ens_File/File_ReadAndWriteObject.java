package com.Ens_Library.Ens_File;

import java.io.*;

public class File_ReadAndWriteObject extends File_default {


    public File_ReadAndWriteObject(String pach) {
       super(pach);



    }
    public File_ReadAndWriteObject() {
        super();



    }
    public File_ReadAndWriteObject(File f) {
        super(f);



    }


    //Variables
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //--------------------------------------------------------------------------
    private FileInputStream fileInputStream;

    protected FileInputStream getFileInputStream() {
        return fileInputStream;
    }

    protected void setFileInputStream(FileInputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    //------------------------------------------------------------------------------
    private ObjectInputStream objectInputStream;

    protected ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    protected void setObjectInputStream(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }

    //-----------------------------------------------------------------
    private ObjectOutputStream objectOutputStream;

    protected ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    protected void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }

    //--------------------------------------------------------------------
    private FileOutputStream fileOutputStream;

    protected FileOutputStream getFileOutputStream() {
        return fileOutputStream;
    }

    protected void setFileOutputStream(FileOutputStream fileOutputStream) {
        this.fileOutputStream = fileOutputStream;
    }

    //-----------------------------------------------------------------------------------

    @Override
    public void setFile(File file) {
        close();
        super.setFile(file);
    }

    //-------------------------------------------------


    ///Functions
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public boolean saveAndClosse(Object obj){

       boolean bool =  save(obj);


       close();
       return bool;
    }

    //---------------------------------------------------------------------------------------
    public Object loadAndClose(){
        Object obj = load();

        close();
        return obj;
    }
//------------------------------------------------------------------------------------------------------------
    public Object load() {

        if (!buldLoad()) {
            return null;
        }

        try {
            return getObjectInputStream().readObject();
        } catch (IOException io) {
           //
        } catch (ClassNotFoundException cnf) {
            //
        }

        return null;
    }

    //----------------------------------------------------------------
    public boolean save(Object obj) {


        if (!buldSave() || obj == null) {
            return false;
        }
        ;

        try {
            getObjectOutputStream().writeObject(obj);
        } catch (IOException io) {
            io.printStackTrace();
            return false;
        }

        return true;
    }


    //---------------------------------------------------------------------------------------------------------------

    protected boolean buldLoad() {

        if (getFile() == null) {
            return false;
        }

        if (getObjectInputStream() == null || getFileInputStream() == null) {

            try {
                setFileInputStream(new FileInputStream(getFile()));
                setObjectInputStream(new ObjectInputStream(getFileInputStream()));
            } catch (IOException io) {
                //io.printStackTrace();
                //File is not Serializable

                return false;
            }

        }
        return true;
    }

    //---------------------------------------------------------------------------------------------------------
    protected boolean buldSave() {

        if (getFile() == null) {
            return false;
        }

        if (getObjectOutputStream() == null || getFileOutputStream() == null) {

            try {
                setFileOutputStream(new FileOutputStream(getFile()));
                setObjectOutputStream(new ObjectOutputStream(getFileOutputStream()));
            } catch (IOException io) {
                io.printStackTrace();
                return false;
            }
        }

        return true;
    }


    //----------------------------------------------------------------------------------------------------------------

    public void close() {

        try {

            if (getObjectInputStream() != null) {
                getObjectInputStream().close();
                setObjectInputStream(null);
            }


            if (getFileInputStream() != null) {
                getFileInputStream().close();
                setFileInputStream(null);
            }

            if (getObjectOutputStream() != null) {
                getObjectOutputStream().close();
                setObjectOutputStream(null);
            }
            if (getFileOutputStream() != null) {
                getFileOutputStream().close();
                setFileOutputStream(null);
            }


        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
