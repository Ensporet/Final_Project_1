package com.Ens_Library.File;


import java.io.File;

public interface IFile {


    //File file


    //Function
    //..................................................................................................................

    default File newFile(String path) {
       return newFile( (path == null) ? null : new File(path));
    };

    File newFile(File path);
    boolean deleteFile(File f);

    //..................................................................................................................

    File getFile();

    void setFile(File file);

    default void setFile(String file) {
        setFile((file == null) ? null : new File(file));
    }




   default File notCopuName(String name) {


        if (name == null) {
            return null;
        }
        if (!new File(name).exists()) {
            return new File(name);
        }


        String st;
        String mod = "";
        int number = 0;


        { // get name ****(***)
            int pos = name.lastIndexOf(File.separator); // pack
            int pos1 = name.lastIndexOf('.'); // .mod
            if (pos1 < 0) {
                pos1 = name.length();
            }
            String n;
            { // get mod

                if (pos1 > pos) {
                    mod = ")" + name.substring(pos1);
                    n = name.substring(pos + 1, pos1);
                } else {
                    n = name.substring(pos + 1);
                }

            }


            // get st and int number
            int pos0 = n.lastIndexOf('(');
            //System.out.println(pos0);


            try {

                number = Integer.valueOf((n.charAt(n.length() - 1) == ')' && pos0 > -1) ? n.substring(pos0 + 1, n.length() - 1) : "");
                st = name.substring(0, (pos + 1) + (pos0 + 1));

            } catch (NumberFormatException e) {


                st = name.substring(0, pos1) + "(";
            }


        }


        while (true) {

            number++;
            File file = new File(st + number + mod);

            if (!file.exists()) {
                return file;
            }
        }


    }

}
