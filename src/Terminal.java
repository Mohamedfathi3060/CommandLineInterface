import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
// Import the IOException class to handle errors

public class Terminal {

    Parser parser;
    //Implement each command in a method, for example:
//    public String pwd(){}
//    public void cd(String[] args){}
    // ...
    //This method will choose the suitable command method to be called
    public void chooseCommandAction(){}
    public static void main(String[] args){

//        try{
//
//        }
//        catch (){
//            e.printStackTrace();
//        }
        File x = new File("M:\\java library\\CommanLineInterface\\file.txt");
//            String[] ls = x.list();
//            for (int i = 0; i < ls.length; i++) {
//                System.out.println(ls[i]);
//            }
        String y = x.toString() ;
        System.out.println(y);


        



    }
}