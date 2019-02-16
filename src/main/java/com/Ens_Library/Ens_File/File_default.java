package com.Ens_Library.Ens_File;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.Objects;

public class File_default implements File_null {


    public File_default(){}

    public File_default(String pach){
        setFile((pach== null)? null : new File(pach));

    }

    public File_default(File f){

        setFile(f);
    }




    //Variables
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //-----------------------------------------------------------------------------------------------------------------
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {



        this.file = file;



        if (file == null || !file.exists()) {

            try {


                if (getFile() == null) {
                    this.setFile(this.newFile(null, null));
                } else {
                    if (!getFile().exists()) {
                        this.setFile(this.newFile(getFile().getParent(), getFile().getName()));

                    }
                }
            } catch (IOException io) {
                io.printStackTrace();
                file = null;
            }


        }


    }




    //...........................................................................

    //Functions
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public LinkedList<Path> getAllFileTree(){return getAllFileTree(getFile());}
    public LinkedList<Path> getAllFileTree(File directory){

        if(directory == null){return null;}



        final LinkedList<Path> linkedList = new LinkedList<>();


        try {
            Path p = Files.walkFileTree(directory.toPath(), new SimpleFileVisitor<Path>(){


                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    linkedList.add(file);


                    return super.visitFile(file, attrs);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        return linkedList;
    }


    //----------------------------------------------------------------------------------------------------------------
    @Override
    public boolean deleteFile(File f){

        if(f == null || !f.exists()){return false;}

        if(f.isFile()){return f.delete();}else {

            for(File fi : Objects.requireNonNull(f.listFiles())){

                deleteFile(fi);
            }
            return f.delete();
        }

    }



    //......................................
    public File newFile(boolean isFile , String patch) throws IOException{
        return newFile(isFile,patch,null,null);
    }

    //......................................
    public File newFile(String patch) throws IOException{
        return newFile(false,patch,null,null);
    }
    //.................
    @Override
    public File newFile(String patch,String name ) throws IOException{
        return newFile(true,patch,name,null);
    }
    //...................
    public File newFile(boolean isFile , String patch,String name ) throws IOException{
        return newFile(isFile,patch,name,null);
    }

//.................
    public File newFile(boolean isFile ,String patch , String name , String mod) throws IOException {


        File p ;
            if(patch == null){
                p = new File("");
            }else {
                p = new File(patch);
                    if(p.isFile()){
                        p = new File(p.getParent());
                    }
            }

       File f = notCopuName(

              p.getAbsolutePath()
               +
                    File.separator +
                       ((name == null || name.isEmpty()) ? "New File" : name)      +
                            ((mod == null || mod.isEmpty()) ? "" : "." + mod )
       );


       if(isFile){
           File parent = new File(f.getParent());
            if(!parent.exists()){parent.mkdirs();}

          return (f.createNewFile()) ? f : null;
       }else {

           f.mkdirs();

       }


     return f;
    }


    //....................................................................
    //---------------------------------------------------------------------------------------------------------------
    protected File notCopuName(String name ){


        if(name == null ){return null;}
        if(!new File(name).exists()){return new File(name);}


        String st ;
        String mod = "";
        int number = 0;





        { // get name ****(***)
            int pos= name.lastIndexOf(File.separator); // pack
            int pos1 = name.lastIndexOf('.'); // .mod
                if(pos1 < 0){pos1 = name.length();}
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
                st = name.substring( 0,(pos + 1) +( pos0 + 1 ) );

            }catch (NumberFormatException e){


                st = name.substring(0,pos1) + "(";
            }



        }





while ( true){

    number++;
    File file = new File(st + number + mod);

    if(!file.exists()){return file;}
}










    }


    //--------------------------------------------------------------------------------------
    public void printlnInfoOfFile(File f){
        if(f == null){System.out.println("File is empty !");}else {

        System.out.println("//-----------------------------------------------------------------------------------\\\\");
       String arr [] = getInfoOfFile(f);
       if(arr == null){System.out.println("Null");}
       else {

           for(String s : arr){
               System.out.print(s);
           }
       }

        System.out.println("\n//-----------------------------------------------------------------------------------\\\\"); }}
    //...............................................
    public String []getInfoOfFile(File f){

        String ret[] = new String[]{

                "AbsolutePath", // 0
                "Name", // 1
                "Exists", // 2
                "List", // 3
                "Is file", // 4
                "Is directory", //5
                "Is absolute", // 6
                "Is hidden", // 7
                "Can write", // 8
                "Can read", // 9
                "Can execute", // 10
                "Space", // 11
                "Last modified" // 12

        };

        int max = SpaseAndSepar(ret," : ")  / 3;
            if(max == 0){max = 1;}
        String newSace = "";
        while (max > 0){newSace += " "; max--;}

        ret[0] +=  f.getAbsolutePath() +"\n";
        ret[1] +=  f.getName()+"\n";
        ret[2] +=  f.exists()+"\n";
        ret[3] +=  "\n";
         {

            String list[] = f.list();
            if(list == null){ ret[3] +=newSace + "-List is empty !\n";}else {
                for (String s : list) {
                    ret[3] += newSace + s + "\n";
                }
            }}
        ret[4] += f.isFile()+"\n";
        ret[5] += f.isDirectory()+"\n";
        ret[6] += f.isAbsolute()+"\n";
        ret[7] += f.isHidden()+"\n";
        ret[8] += f.canWrite()+"\n";
        ret[9] += f.canRead()+"\n";
        ret[10]+= f.canExecute()+"\n";
        ret[11]+= "\n";
        {
            ret[11] += newSace + "-Total :" + f.getTotalSpace() + "\n" +
                       newSace + "-Usable:" + f.getUsableSpace()+ "\n" +
                       newSace + "-Free  :" + f.getFreeSpace()  + "\n" ;
        }
        ret[12] += f.lastModified();



        return ret;
    }
    //..........................................................................................................
    protected int SpaseAndSepar(String [] array , String separ){
        int max = 0;
    if(array != null){

        for(String s : array){
            if(s.length() > max){max = s.length();}
        }

        for (int i = 0 ; i < array.length ; i ++){

            for(int it = max - array[i].length() ; it > 0 ; it--){
                array[i] += " ";

            }
            array[i] += separ;

        }

    }


return max;

    }
    //--------------------------------------------------------------------------------------------------------------


}
